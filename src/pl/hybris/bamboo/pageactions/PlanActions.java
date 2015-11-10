package pl.hybris.bamboo.pageactions;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.enums.MetadataAntTask;
import pl.hybris.bamboo.pageobjects.JobPage;
import pl.hybris.bamboo.pageobjects.PlanSidebar;
import pl.hybris.bamboo.pageobjects.TaskPage;
import pl.hybris.bamboo.persistence.AntTask;
import pl.hybris.bamboo.persistence.AntTaskRepository;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * Created by i323728 on 11/3/15.
 */
@Component
public class PlanActions
{
	CustomDriver driver;

	JobPage jobPage;
	PlanSidebar sidebar;

    private AntTaskRepository repository;

	@Autowired
	public PlanActions(CustomDriver driver, AntTaskRepository repository)
	{
		this.driver = driver;
        this.repository = repository;

	}

	public void navigateToPlanAndSync(String planLink)
	{
		//driver.navigate().to("https://bamboo.hybris.com/chain/admin/config/defaultStages.action?buildKey=DBTPLA2-RELEASE");
        driver.navigate().to(planLink);
		sidebar = new PlanSidebar(driver);
		sidebar.synchronize();

		jobPage = new JobPage(driver);
	}

	public List<String> getAllJobs()
	{
		List<WebElement> jobs = sidebar.getJobs();
		List<String> jobsUrl = new ArrayList<>();
		for (int i = 0; i < jobs.size(); i++)
		{
			String url = jobs.get(i).getAttribute("href");
			if (url != null)
			{
				jobsUrl.add(url);
			}
			else
			{
				throw new InvalidElementStateException("Job element did not contain href attribute");
			}

		}

		return jobsUrl;

	}

	public void getAllAntTasksDetailsFromAJob(String jobUrl)
	{

		driver.navigate().to(jobUrl);
		jobPage.synchronize();

		List<WebElement> antTasks = jobPage.getAllAntTasks();
		for (WebElement antTask : antTasks)
		{
			TaskPage taskDetails = jobPage.navigateToTheTask(antTask);


            Hashtable<String, String> antTaskMetaData = new Hashtable<>();
			antTaskMetaData.put(MetadataAntTask.HEADER.toString(), taskDetails.getHeader());
			antTaskMetaData.put(MetadataAntTask.TASK_DESCRIPTION.toString(), taskDetails.getDescription());
			antTaskMetaData.put(MetadataAntTask.TASK_DISABLED.toString(), taskDetails.getDisableTaskState().toString());
			antTaskMetaData.put(MetadataAntTask.EXECUTABLE_VERSION.toString(), taskDetails.getExecutableVersion());
			antTaskMetaData.put(MetadataAntTask.BUILD_FILE.toString(), taskDetails.getBuildFile());
			antTaskMetaData.put(MetadataAntTask.TARGET.toString(), taskDetails.getAntTargetAndProperties());
			antTaskMetaData.put(MetadataAntTask.BUILD_SDK.toString(), taskDetails.getBuildJDK());
			antTaskMetaData.put(MetadataAntTask.ENV_VARIABLES.toString(), taskDetails.getEnvironmentVariables());
			antTaskMetaData.put(MetadataAntTask.WORKING_SUB_DIR.toString(), taskDetails.getWorkingSubDirectory());
			boolean TaskProduceTestResultsCheckboxState = taskDetails.getTaskProduceTestResultsCheckboxState();
			antTaskMetaData.put(MetadataAntTask.BUILD_PRODUCE_TEST_RESULTS.toString(), String.valueOf(TaskProduceTestResultsCheckboxState));

			String taskProducesTestResultsMessage;
			if (TaskProduceTestResultsCheckboxState)
			{
				taskProducesTestResultsMessage = taskDetails.getSpecifyCustomTestResultsDirectoryValue();
			}
			else
			{
				taskProducesTestResultsMessage = "Task does not produce test results";
			}
			antTaskMetaData.put(MetadataAntTask.CUSTOM_TEST_RESULTS_DIR.toString(), taskProducesTestResultsMessage);

            String imgName = "initialValue";
            imgName = taskDetails.takePageScreenshot();
			antTaskMetaData.put(MetadataAntTask.SCREENSHOT_FILE_NAME.toString(), imgName);
            antTaskMetaData.put(MetadataAntTask.BUILD_KEY.toString(),getPlanBuildKey());

			repository.save(new AntTask(
							antTaskMetaData.get(MetadataAntTask.HEADER.toString()),
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
							antTaskMetaData.get(MetadataAntTask.BUILD_KEY.toString())
					)
			);

		}

	}

	public String getPlanBuildKey()
	{
        String currentUrl = driver.getCurrentUrl();
		int i = currentUrl.indexOf("?buildKey=");
		int j = currentUrl.length();
        String buildKey = currentUrl.substring(i + 10 ,j);
        return buildKey;
	}


}
