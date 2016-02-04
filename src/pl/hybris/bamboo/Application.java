package pl.hybris.bamboo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.hybris.bamboo.constants.PlansToFixMasterCore;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.enums.MetadataAntTask;
import pl.hybris.bamboo.pageactions.PlanActions;
import pl.hybris.bamboo.pageactions.ProjectAction;
import pl.hybris.bamboo.pageobjects.PlanSidebar;
import pl.hybris.bamboo.pageobjects.PlanTabsMenu;
import pl.hybris.bamboo.pageobjects.PlantTopSection;
import pl.hybris.bamboo.pageobjects.RepositoriesTab;
import pl.hybris.bamboo.pageobjects.TaskConfigurationTab;
import pl.hybris.bamboo.persistence.AntTask;
import pl.hybris.bamboo.persistence.AntTaskRepository;
import pl.hybris.bamboo.persistence.Plan;
import pl.hybris.bamboo.persistence.PlanRepository;
import pl.hybris.bamboo.util.FileSystemUtil;

import java.util.Hashtable;
import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner
{

    //	To enable the @Autowired, you have to register an ‘AutowiredAnnotationBeanPostProcessor’. You can register it in two ways.
    //	To enable @Autowired, I believe there are two ways to do it in the xml file:
    //	But how do you do it if you're not using XML, and Spring 3.0 doesn't have the handy @AnnotationDrivenConfig?
    //
    //	Nevermind.
    //
    //	Simply using the AnnotationConfigApplicationContext class in the @Configuration class is sufficient. I couldn't seem to get it to work with a constructor, but no problem with a setter method.
    //	org.springframework.context.annotation.AnnotationConfigApplicationContext;
    //	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SimpleConfig.class);

    @Autowired
    private CustomDriver driver;

    @Autowired
    private AntTaskRepository antRepository;

    @Autowired
    private PlanRepository planRepository;

    public static void main(final String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(final String... args) throws Exception
    {

        for (final String plan : PlansToFixMasterCore.coreMaster)
        {

            driver.navigate().to("https://bamboo.hybris.com/browse/" + plan);

            final PlantTopSection planTopBar = new PlantTopSection(driver);
            planTopBar.synchronize();
            planTopBar.navigateToPlanConfiguration();

            final PlanTabsMenu tabsMenu = new PlanTabsMenu(driver);
            tabsMenu.synchronize();
            tabsMenu.selectTabRepositories();

            final RepositoriesTab tab = new RepositoriesTab(driver);

            tab.synchronize();
            tab.clickAddRepository();
            tab.syncNewRepository();
            tab.setRepositoryHost("Git");
            tab.setDisplayName("core");
            tab.setRepoURL("ssh://git@stash.hybris.com:7999/platform/core.git");
            tab.setBranch("develop");
            tab.setAuthType("Shared credentials");
            tab.saveRepository();

            final PlanSidebar sidebar = new PlanSidebar(driver);
            sidebar.synchronize();
            sidebar.selectJobByName("Default Job");

            tabsMenu.synchronize();
            tabsMenu.selectTabTasks();

            final TaskConfigurationTab taskConfigurationTab = new TaskConfigurationTab(driver);
            taskConfigurationTab.synchronize();

            taskConfigurationTab.navigateToTaskByDescriptionContainingText("Checkout Platform Repository");

            taskConfigurationTab.setRepositoryByDropdownName("core");
            taskConfigurationTab.save();


            sidebar.synchronize();
            sidebar.navigateToPlanConfiguration();

            tabsMenu.synchronize();
            tabsMenu.selectTabRepositories();

            tab.synchronize();
            tab.removeRepoByName("ECP-platform export only");

        }
    }


    public void runParseTasks(final String... args) throws Exception
    {

        driver.navigate().to("https://bamboo.hybris.com/build/admin/edit/editBuildTasks.action?buildKey=DBTPLA2-RELEASE-ICPERST");

        final PlanSidebar sidebar = new PlanSidebar(driver);
        sidebar.synchronize();

        final TaskConfigurationTab taskP = new TaskConfigurationTab(driver);
        taskP.synchronize();
        taskP.takePageScreenshot();

        if (false)
        {
            final PlanActions plan = new PlanActions(driver);

            antRepository.deleteAll();
            planRepository.deleteAll();
            final FileSystemUtil util = new FileSystemUtil();
            util.dumpAllImageFiles();

            final ProjectAction app = new ProjectAction(driver);
            app.navigateToBamboo();
            final List<String> urls = app.getAllPlanConfigurationLinks();
            for (final String url : urls)
            {
                planRepository.save(new Plan(url, "false"));
            }

            final List<Plan> plansToBeParsed = planRepository.findByParsed("false");

            for (final Plan planEntity : plansToBeParsed)
            {
                final String planUrl = planEntity.getUrl();
                plan.navigateToPlanAndSync(planUrl);
                final List<String> jobsUrlForTheGivenPlan = plan.getAllJobs();
                for (int i = 0; i < jobsUrlForTheGivenPlan.size(); i++)
                {
                    final List<Hashtable<String, String>> antTaskResults = plan
                            .getAllAntTasksDetailsFromAJob(jobsUrlForTheGivenPlan.get(i));

                    for (final Hashtable<String, String> antTaskMetaData : antTaskResults)
                    {
                        antRepository.save(new AntTask(antTaskMetaData.get(MetadataAntTask.HEADER.toString()),
                                antTaskMetaData.get(MetadataAntTask.TASK_DESCRIPTION.toString()),
                                antTaskMetaData.get(MetadataAntTask.TASK_DISABLED.toString()),
                                antTaskMetaData.get(MetadataAntTask.EXECUTABLE_VERSION.toString()),
                                antTaskMetaData.get(MetadataAntTask.BUILD_FILE.toString()),
                                antTaskMetaData.get(MetadataAntTask.TARGET.toString()),
                                antTaskMetaData.get(MetadataAntTask.BUILD_SDK.toString()),
                                antTaskMetaData.get(MetadataAntTask.ENV_VARIABLES.toString()),
                                antTaskMetaData.get(MetadataAntTask.WORKING_SUB_DIR.toString()),
                                antTaskMetaData.get(MetadataAntTask.BUILD_PRODUCE_TEST_RESULTS.toString()),
                                antTaskMetaData.get(MetadataAntTask.CUSTOM_TEST_RESULTS_DIR.toString()),
                                antTaskMetaData.get(MetadataAntTask.SCREENSHOT_FILE_NAME.toString()),
                                antTaskMetaData.get(MetadataAntTask.BUILD_KEY.toString())));
                    }
                }
                planEntity.setParsed("true");
                planRepository.save(planEntity);
            }
        }
    }
}
