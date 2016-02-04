package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.hybris.bamboo.core.CustomSelect;
import pl.hybris.bamboo.or.plan.TabsRepositoriesOR;
import pl.hybris.bamboo.or.plan.TabsTasksOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;

import java.util.List;


/**
 * Created by i323728 on 11/3/15.
 */
public class TaskConfigurationTab implements BasicPage
{

	private WebDriver driver;

	private WebElement pageTag;

	public TaskConfigurationTab(final WebDriver webDriver)
	{
		this.driver = webDriver;
	}

	@Override
	public void synchronize()
	{
		pageTag = driver.findElement(TabsTasksOR.TaskEditorSection);

		CommonUtil.highlight(pageTag);
	}

	@Override
	public WebElement getTag()
	{
		return pageTag;
	}

	@Override
	public String takePageScreenshot()
	{
		return CommonUtil.takeScreenshot(pageTag);
	}


	public TaskConfigurationTab navigateToTheTask(final WebElement taskPane)
	{
		taskPane.click();
		return this;
	}

	public TaskConfigurationTab navigateToTaskByDescriptionContainingText(final String text)
	{
		final List<WebElement> allTasks = pageTag.findElements(TabsTasksOR.SingleTaskItem);
		for (final WebElement task : allTasks)
		{
			if (task.getText().contains(text))
			{
				task.click();
				return this;
			}
		}
		throw new NoSuchElementException("++++++++++ Failed to execute TaskConfigurationTab.navigateToTaskByDescriptionContainingText() no task with description " + text);
	}


	//Ant

	public List<WebElement> getAllAntTasks()
	{
		final List<WebElement> bla = pageTag.findElements(TabsTasksOR.AntTaskItem);
		return bla;
	}


	public String getHeader()
	{
		final WebElement ele = pageTag.findElement(TabsTasksOR.Header);
		return ele.getText();
	}

	public String getDescription()
	{
		final WebElement ele = pageTag.findElement(TabsTasksOR.Input_Description);
		return ele.getAttribute("value");
	}

	public Boolean getDisableTaskState()
	{
		final WebElement ele = pageTag.findElement(TabsTasksOR.Checkbox_TaskDisabled);
		final String bla = ele.getAttribute("checked");
		return Boolean.valueOf(bla);
	}

	public String getExecutableVersion()
	{
		final CustomSelect ele = new CustomSelect(pageTag.findElement(TabsTasksOR.Select_ExecutableVersion));
		return ele.getFirstSelectedOption().getText();
	}

	public String getBuildFile()
	{
		final WebElement ele = pageTag.findElement(TabsTasksOR.Input_BuildFile);
		return ele.getAttribute("value");
	}

	public String getTarget()
	{
		final WebElement ele = pageTag.findElement(TabsTasksOR.Input_Description);
		return ele.getAttribute("value");
	}

	public String getAntTargetAndProperties()
	{
		final WebElement ele = pageTag.findElement(TabsTasksOR.Input_AntTargetAndProperties);
		return ele.getAttribute("value");
	}

	public String getBuildJDK()
	{
		final CustomSelect ele = new CustomSelect(pageTag.findElement(TabsTasksOR.Select_BuildJDK));
		return ele.getFirstSelectedOption().getText();
	}

	public String getEnvironmentVariables()
	{
		final WebElement ele = pageTag.findElement(TabsTasksOR.Input_EnvironmentVariables);
		return ele.getAttribute("value");
	}

	public String getWorkingSubDirectory()
	{
		final WebElement ele = pageTag.findElement(TabsTasksOR.Input_WorkingSubDirectory);
		return ele.getAttribute("value");
	}

	public Boolean getTaskProduceTestResultsCheckboxState()
	{
		final WebElement ele = pageTag.findElement(TabsTasksOR.Checkbox_TaskProduceTestResults);
		final String bla = ele.getAttribute("checked");
		return Boolean.valueOf(bla);
	}

	public String getSpecifyCustomTestResultsDirectoryValue()
	{

		// Builder pattern ...
		if (getTaskProduceTestResultsCheckboxState())
		{
			final WebElement ele = pageTag.findElement(TabsTasksOR.Input_SpecifyCustomTestResultsDirectory);
			return ele.getText();
		}

		return "Checkbox 'The build will produce test results' is not selected! Skipping the execution of the 'getSpecifyCustomTestResultsDirectoryValue'";

	}

	//Source Code Checkout


	public TaskConfigurationTab setRepositoryByDropdownName(final String repoName)
	{
		final CustomSelect ele = new CustomSelect(pageTag.findElement(TabsTasksOR.Dropdown_Repository));
		ele.selectByVisibleText(repoName);
		return this;
	}

	public TaskConfigurationTab save()
	{
		pageTag.findElement(TabsTasksOR.Button_Save).click();
		pageTag.findElement(TabsRepositoriesOR.divNoItemSelected);
		return this;
	}
}

