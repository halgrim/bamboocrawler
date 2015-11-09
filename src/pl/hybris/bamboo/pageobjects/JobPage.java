package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.or.plan.StageBlockOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;

import java.util.List;

/**
 * Created by i323728 on 11/6/15.
 */
@Component
public class JobPage implements BasicPage
{
    private CustomDriver driver;
    private WebDriverWait wait;

    private WebElement pageTag;

    @Autowired
    public JobPage(CustomDriver driver)
    {
        this.wait = new WebDriverWait(driver, 3);
        this.driver = driver;
    }

    @Override
    public void synchronize()
    {
        pageTag = wait.until(ExpectedConditions.elementToBeClickable(StageBlockOR.TaskListSection));
        wait.until(ExpectedConditions.elementToBeClickable(StageBlockOR.FinalTaskElement));

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
        List<WebElement> bla = pageTag.findElements(StageBlockOR.AntTaskItem);
        return bla;
    }

    public TaskPage navigateToTheTask(WebElement taskPane)
    {
        taskPane.click();
        TaskPage taskDetails = new TaskPage(driver);
        taskDetails.synchronize();
        return taskDetails;
    }

}
