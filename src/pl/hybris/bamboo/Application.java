package pl.hybris.bamboo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.enums.MetadataAntTask;
import pl.hybris.bamboo.pageactions.PlanActions;
import pl.hybris.bamboo.pageactions.ProjectAction;
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

	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{

		PlanActions plan = new PlanActions(driver);

		antRepository.deleteAll();
		planRepository.deleteAll();
		FileSystemUtil util = new FileSystemUtil();
		util.dumpAllImageFiles();

		ProjectAction app = new ProjectAction(driver);
		app.navigateToBamboo();
		List<String> urls = app.getAllPlanConfigurationLinks();
		for (String url : urls)
		{
			planRepository.save(new Plan(url, "false"));
		}

		List<Plan> plansToBeParsed = planRepository.findByParsed("false");

		for (Plan planEntity : plansToBeParsed)
		{
			String planUrl = planEntity.getUrl();
			plan.navigateToPlanAndSync(planUrl);
			List<String> jobsUrlForTheGivenPlan = plan.getAllJobs();
			for (int i = 0; i < jobsUrlForTheGivenPlan.size(); i++)
			{
				List<Hashtable<String, String>> antTaskResults = plan.getAllAntTasksDetailsFromAJob(jobsUrlForTheGivenPlan.get(i));

				for (Hashtable<String, String> antTaskMetaData : antTaskResults)
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
