package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.hybris.bamboo.or.plan.StageBlockOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;

import java.util.List;

/**
 * Created by i323728 on 11/6/15.
 */
public class JobTaskList implements BasicPage
{
    private final WebDriver driver;

    private WebElement pageTag;

    public JobTaskList(final WebDriver driver)
    {
       this.driver = driver;
    }

    @Override
    public void synchronize()
    {
        pageTag = driver.findElement(StageBlockOR.TaskListSection);
        pageTag.getText();
        driver.findElement(StageBlockOR.FinalTaskElement).getText();
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


    public List<WebElement> getAllAntTasks()
    {
        final List<WebElement> bla = pageTag.findElements(StageBlockOR.AntTaskItem);
        return bla;
    }

    public TaskConfiguration navigateToTheTask(final WebElement taskPane)
    {
        taskPane.click();
        final TaskConfiguration taskDetails = new TaskConfiguration(driver);
        taskDetails.synchronize();
        return taskDetails;
    }

}
