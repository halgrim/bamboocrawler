package pl.hybris.bamboo.pageactions;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.enums.MetadataAntTask;
import pl.hybris.bamboo.pageobjects.JobTaskList;
import pl.hybris.bamboo.pageobjects.PlanSidebar;
import pl.hybris.bamboo.pageobjects.TaskConfiguration;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * Created by i323728 on 11/3/15.
 */
public class PlanActions
{
	private final CustomDriver driver;

	private JobTaskList jobTaskList;
	private PlanSidebar sidebar;

	public PlanActions(final CustomDriver driver)
	{
		this.driver = driver;

	}

	public void navigateToPlanAndSync(final String planLink)
	{
		//driver.navigate().to("https://bamboo.hybris.com/chain/admin/config/defaultStages.action?buildKey=DBTPLA2-RELEASE");
        driver.navigate().to(planLink);
		sidebar = new PlanSidebar(driver);
		sidebar.synchronize();

		jobTaskList = new JobTaskList(driver);
	}

	public List<String> getAllJobs()
	{
		final List<WebElement> jobs = sidebar.getJobs();
		final List<String> jobsUrl = new ArrayList<>();
		for (int i = 0; i < jobs.size(); i++)
		{
			final String url = jobs.get(i).getAttribute("href");
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

	public List<Hashtable<String, String>> getAllAntTasksDetailsFromAJob(final String jobUrl)
	{

		driver.navigate().to(jobUrl);
		jobTaskList.synchronize();

		final List<WebElement> antTasks = jobTaskList.getAllAntTasks();
		final List<Hashtable<String, String>> antResults = new ArrayList<>();
		for (final WebElement antTask : antTasks)
		{
			final TaskConfiguration taskDetails = jobTaskList.navigateToTheTask(antTask);


            final Hashtable<String, String> antTaskMetaData = new Hashtable<>();
			antTaskMetaData.put(MetadataAntTask.HEADER.toString(), taskDetails.getHeader());
			antTaskMetaData.put(MetadataAntTask.TASK_DESCRIPTION.toString(), taskDetails.getDescription());
			antTaskMetaData.put(MetadataAntTask.TASK_DISABLED.toString(), taskDetails.getDisableTaskState().toString());
			antTaskMetaData.put(MetadataAntTask.EXECUTABLE_VERSION.toString(), taskDetails.getExecutableVersion());
			antTaskMetaData.put(MetadataAntTask.BUILD_FILE.toString(), taskDetails.getBuildFile());
			antTaskMetaData.put(MetadataAntTask.TARGET.toString(), taskDetails.getAntTargetAndProperties());
			antTaskMetaData.put(MetadataAntTask.BUILD_SDK.toString(), taskDetails.getBuildJDK());
			antTaskMetaData.put(MetadataAntTask.ENV_VARIABLES.toString(), taskDetails.getEnvironmentVariables());
			antTaskMetaData.put(MetadataAntTask.WORKING_SUB_DIR.toString(), taskDetails.getWorkingSubDirectory());
			final boolean TaskProduceTestResultsCheckboxState = taskDetails.getTaskProduceTestResultsCheckboxState();
			antTaskMetaData.put(MetadataAntTask.BUILD_PRODUCE_TEST_RESULTS.toString(), String.valueOf(TaskProduceTestResultsCheckboxState));

			final String taskProducesTestResultsMessage;
			if (TaskProduceTestResultsCheckboxState)
			{
				taskProducesTestResultsMessage = taskDetails.getSpecifyCustomTestResultsDirectoryValue();
			}
			else
			{
				taskProducesTestResultsMessage = "Task does not produce test results";
			}
			antTaskMetaData.put(MetadataAntTask.CUSTOM_TEST_RESULTS_DIR.toString(), taskProducesTestResultsMessage);

			final String imgName = taskDetails.takePageScreenshot();
			antTaskMetaData.put(MetadataAntTask.SCREENSHOT_FILE_NAME.toString(), imgName);
            antTaskMetaData.put(MetadataAntTask.BUILD_KEY.toString(),getPlanBuildKey());
			antResults.add(antTaskMetaData);
		}

		return antResults;
	}

	public String getPlanBuildKey()
	{
        final String currentUrl = driver.getCurrentUrl();
		final int i = currentUrl.indexOf("?buildKey=");
		final int j = currentUrl.length();
        final String buildKey = currentUrl.substring(i + 10 ,j);
        return buildKey;
	}


}
