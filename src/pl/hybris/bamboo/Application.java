package pl.hybris.bamboo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.pageactions.PlanActions;
import pl.hybris.bamboo.pageactions.ProjectAction;

@SpringBootApplication
public class Application implements CommandLineRunner
{

    @Autowired
    private CustomDriver driver;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ProjectAction app = new ProjectAction(driver);
        PlanActions plan = new PlanActions(driver);

//        FileSystemUtil util = new FileSystemUtil();
//        util.dumpAllImgageFiles();

        try{
//
//            plan.navigateToPlanAndSync();
//            List<String> jobsUrlForTheGivenPlan = plan.getAllJobs();
//            for (int i = 0 ; i < jobsUrlForTheGivenPlan.size() ; i ++)
//            {
//                plan.getAllAntTasksDetailsFromAJob(jobsUrlForTheGivenPlan.get(i));
//            }


        } finally
        {
            // Might quit earlier than all screenshot saves to disk.
            app.quit();
        }


    }
}
