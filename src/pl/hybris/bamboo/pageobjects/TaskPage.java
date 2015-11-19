package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.hybris.bamboo.core.CustomSelect;
import pl.hybris.bamboo.or.plan.TaskDetailsBlockOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;


/**
 * Created by i323728 on 11/3/15.
 */
public class TaskPage implements BasicPage
{

	private WebDriver driver;

	private WebElement pageTag;

	public TaskPage(WebDriver webDriver)
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
		pageTag.getText();
        for (int i = 0; i < 5; i++)
        {
            try
            {
                return CommonUtil.takeScreenshot(pageTag);
            } catch (TimeoutException e)
            {
                CommonUtil.printMessage("++++++++++ TaskPage.takePageScreenshot() " + e.getClass().getSimpleName() + " " + i);
            }
        }
        CommonUtil.printMessage("++++++++++ Failed to execute TaskPage.takePageScreenshot()");
        throw new NoSuchElementException("++++++++++ Failed to execute TaskPage.takePageScreenshot()");
	}

	public String getHeader()
	{
		WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Header);
		return ele.getText();
	}

	public String getDescription()
	{
		WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_Description);
		return ele.getAttribute("value");
	}

	public Boolean getDisableTaskState()
	{
		WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Checkbox_TaskDisabled);
		String bla = ele.getAttribute("checked");
		return Boolean.valueOf(bla);
	}

	public String getExecutableVersion()
	{
		CustomSelect ele = new CustomSelect(pageTag.findElement(TaskDetailsBlockOR.Select_ExecutableVersion));
		return ele.getFirstSelectedOption().getText();
	}

	public String getBuildFile()
	{
		WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_BuildFile);
		return ele.getAttribute("value");
	}

	public String getTarget()
	{
		WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_Description);
		return ele.getAttribute("value");
	}

	public String getAntTargetAndProperties()
	{
		WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_AntTargetAndProperties);
		return ele.getAttribute("value");
	}

	public String getBuildJDK()
	{
		CustomSelect ele = new CustomSelect(pageTag.findElement(TaskDetailsBlockOR.Select_BuildJDK));
		return ele.getFirstSelectedOption().getText();
	}

	public String getEnvironmentVariables()
	{
		WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_EnvironmentVariables);
		return ele.getAttribute("value");
	}

	public String getWorkingSubDirectory()
	{
		WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_WorkingSubDirectory);
		return ele.getAttribute("value");
	}

	public Boolean getTaskProduceTestResultsCheckboxState()
	{
		WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Checkbox_TaskProduceTestResults);
		String bla = ele.getAttribute("checked");
		return Boolean.valueOf(bla);
	}

	public String getSpecifyCustomTestResultsDirectoryValue()
	{

		// Builder pattern ...
		if (getTaskProduceTestResultsCheckboxState())
		{
			WebElement ele = pageTag.findElement(TaskDetailsBlockOR.Input_SpecifyCustomTestResultsDirectory);
			return ele.getText();
		}

		return "Checkbox 'The build will produce test results' is not selected! Skipping the execution of the 'getSpecifyCustomTestResultsDirectoryValue'";

	}

}

