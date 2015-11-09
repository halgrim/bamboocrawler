package pl.hybris.bamboo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.persistence.AntTask;
import pl.hybris.bamboo.persistence.AntTaskRepository;

@SpringBootApplication
public class Application implements CommandLineRunner
{

    @Autowired
    private CustomDriver driver;

    @Autowired
    private AntTaskRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        for (AntTask ant : repository.findAll()) {
            String target = ant.getTarget();
            System.out.println(target);
            System.out.println("");
            if (target == null)
            {
                repository.delete(ant);
            }

        }


  /*      ProjectAction app = new ProjectAction(driver);
        PlanActions plan = new PlanActions(driver, repository);

//        FileSystemUtil util = new FileSystemUtil();
//        util.dumpAllImgageFiles();

        try{

            plan.navigateToPlanAndSync();
            List<String> jobsUrlForTheGivenPlan = plan.getAllJobs();
            for (int i = 0 ; i < jobsUrlForTheGivenPlan.size() ; i ++)
            {
                plan.getAllAntTasksDetailsFromAJob(jobsUrlForTheGivenPlan.get(i));
            }


        } finally
        {
            // Might quit earlier than all screenshot saves to disk.
            app.quit();
        }*/


    }
}
