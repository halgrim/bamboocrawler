package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.hybris.bamboo.core.CustomWebElement;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.core.interfaces.CustomElement;
import pl.hybris.bamboo.or.plan.TaskDetailsBlockOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;


/**
 * Created by i323728 on 11/3/15.
 */
@Component
public class TaskPage implements BasicPage
{

    private CustomDriver driver;
    private WebDriverWait wait;

    private CustomElement pageTag;

    @Autowired
    public TaskPage(CustomDriver driver)
    {
        this.wait = new WebDriverWait(driver, 3);
        this.driver = driver;
    }

    @Override
    public void synchronize()
    {
        for(int i = 0; i < 5 ; i++)
        {
            try
            {
                pageTag = new CustomWebElement(wait.until(ExpectedConditions.elementToBeClickable(TaskDetailsBlockOR.TaskDetailsSection)));
                pageTag.getText();
                break;
            } catch (StaleElementReferenceException e)
            {
                CommonUtil.wait(250);
                CommonUtil.printMessage("++++++++++ TaskPage.synchronize() StaleElementReferenceException TaskDetailsSection");
            }
        }

        for(int i = 0; i < 5 ; i++)
        {
            try
            {
                Select troublesomeSelect = new Select(wait.until(ExpectedConditions.elementToBeClickable(TaskDetailsBlockOR.Select_ExecutableVersion)));
                troublesomeSelect.getFirstSelectedOption();
                break;
            } catch (StaleElementReferenceException e)
            {
                CommonUtil.wait(250);
                CommonUtil.printMessage("++++++++++ TaskPage.synchronize() StaleElementReferenceException Select_ExecutableVersion");
            }
        }

        for(int i = 0; i < 5 ; i++)
        {
            try
            {
                Select troublesomeSelect = new Select(wait.until(ExpectedConditions.elementToBeClickable(TaskDetailsBlockOR.Select_BuildJDK)));
                troublesomeSelect.getFirstSelectedOption();
                break;
            } catch (StaleElementReferenceException e)
            {
                CommonUtil.wait(250);
                CommonUtil.printMessage("++++++++++ TaskPage.synchronize() StaleElementReferenceException Select_BuildJDK");
            }
        }
    }

    @Override
    public WebElement getTag()
    {
        return pageTag;
    }

    @Override
    public String takePageScreenshot()
    {
        return CommonUtil.takeScreenshot(pageTag.getPureWebElement());
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
        Select ele = new Select(pageTag.findElement(TaskDetailsBlockOR.Select_ExecutableVersion));
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
        Select ele = new Select(pageTag.findElement(TaskDetailsBlockOR.Select_BuildJDK));
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

