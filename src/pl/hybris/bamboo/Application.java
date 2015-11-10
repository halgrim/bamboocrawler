package pl.hybris.bamboo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.pageactions.PlanActions;
import pl.hybris.bamboo.pageactions.ProjectAction;
import pl.hybris.bamboo.persistence.AntTaskRepository;
import pl.hybris.bamboo.persistence.Plan;
import pl.hybris.bamboo.persistence.PlanRepository;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner
{

    @Autowired
    private CustomDriver driver;

    @Autowired
    private AntTaskRepository antRepository;

    @Autowired
    private PlanRepository planRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


//        for (AntTask ant : antRepository.findAll()) {
//            String target = ant.getTarget();
//            System.out.println(target);
//            System.out.println("");
//            if (target == null)
//            {
//                antRepository.delete(ant);
//            }
//
//        }

        ProjectAction app = new ProjectAction(driver);
        PlanActions plan = new PlanActions(driver, antRepository);

//        antRepository.deleteAll();
//        planRepository.deleteAll();
//        FileSystemUtil util = new FileSystemUtil();
//        util.dumpAllImageFiles();

        try{
//            app.navigateToBamboo();
//            List<String> urls = app.playWith();
//            for(String url : urls)
//            {
//                planRepository.save(new Plan(url, "false"));
//            }

            List<Plan> plansToBeParsed = planRepository.findByParsed("false");


            for(Plan planEntity : plansToBeParsed){
                String planUrl = planEntity.getUrl();
                plan.navigateToPlanAndSync(planUrl);
                List<String> jobsUrlForTheGivenPlan = plan.getAllJobs();
                for (int i = 0 ; i < jobsUrlForTheGivenPlan.size() ; i ++)
                {
                    plan.getAllAntTasksDetailsFromAJob(jobsUrlForTheGivenPlan.get(i));
                }
                planEntity.setParsed("true");
                planRepository.save(planEntity);
            }

        } finally
        {
            app.quit();
        }


    }
}
