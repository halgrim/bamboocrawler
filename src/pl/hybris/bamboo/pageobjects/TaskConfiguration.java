package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.hybris.bamboo.core.CustomSelect;
import pl.hybris.bamboo.or.plan.TaskDetailsBlockOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;


/**
 * Created by i323728 on 11/3/15.
 */
public class TaskConfiguration implements BasicPage
{

	private final WebDriver driver;

	private WebElement pageTag;

	public TaskConfiguration(final WebDriver webDriver)
	{
		this.driver = webDriver;
	}

	@Override
	public void synchronize()
	{
		pageTag = driver.findElement(TaskDetailsBlockOR.TaskDetailsSection);
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

	public String getHeader()
	{
		final WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Header);
		return ele.getText();
	}

	public String getDescription()
	{
		final WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_Description);
		return ele.getAttribute("value");
	}

	public Boolean getDisableTaskState()
	{
		final WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Checkbox_TaskDisabled);
		final String bla = ele.getAttribute("checked");
		return Boolean.valueOf(bla);
	}

	public String getExecutableVersion()
	{
		final CustomSelect ele = new CustomSelect(pageTag.findElement(TaskDetailsBlockOR.Select_ExecutableVersion));
		return ele.getFirstSelectedOption().getText();
	}

	public String getBuildFile()
	{
		final WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_BuildFile);
		return ele.getAttribute("value");
	}

	public String getTarget()
	{
		final WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_Description);
		return ele.getAttribute("value");
	}

	public String getAntTargetAndProperties()
	{
		final WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_AntTargetAndProperties);
		return ele.getAttribute("value");
	}

	public String getBuildJDK()
	{
		final CustomSelect ele = new CustomSelect(pageTag.findElement(TaskDetailsBlockOR.Select_BuildJDK));
		return ele.getFirstSelectedOption().getText();
	}

	public String getEnvironmentVariables()
	{
		final WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_EnvironmentVariables);
		return ele.getAttribute("value");
	}

	public String getWorkingSubDirectory()
	{
		final WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_WorkingSubDirectory);
		return ele.getAttribute("value");
	}

	public Boolean getTaskProduceTestResultsCheckboxState()
	{
		final WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Checkbox_TaskProduceTestResults);
		final String bla = ele.getAttribute("checked");
		return Boolean.valueOf(bla);
	}

	public String getSpecifyCustomTestResultsDirectoryValue()
	{

		// Builder pattern ...
		if (getTaskProduceTestResultsCheckboxState())
		{
			final WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_SpecifyCustomTestResultsDirectory);
			return ele.getText();
		}

		return "Checkbox 'The build will produce test results' is not selected! Skipping the execution of the 'getSpecifyCustomTestResultsDirectoryValue'";

	}

}

