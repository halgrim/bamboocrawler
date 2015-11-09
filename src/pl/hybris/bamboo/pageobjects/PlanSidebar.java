package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.or.plan.PlanSidebarOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;

import java.util.List;

/**
 * Created by i323728 on 11/6/15.
 */
@Component
public class PlanSidebar implements BasicPage
{
    CustomDriver driver;
    WebDriverWait wait;

    WebElement tagContainer;

    @Autowired
    public PlanSidebar(CustomDriver driver)
    {
        this.wait = new WebDriverWait(driver, 3);
        this.driver = driver;
    }

    @Override
    public void synchronize()
    {
        tagContainer = wait.until(ExpectedConditions.elementToBeClickable(PlanSidebarOR.Section_ConfigSidebar));
    }

    @Override
    public WebElement getTag()
    {
        return tagContainer;
    }

    @Override
    public String takePageScreenshot()
    {
        return CommonUtil.takeScreenshot(tagContainer);
    }

    public List<WebElement> getStages()
    {

        return getElements(PlanSidebarOR.StageElement);

    }

    public List<WebElement> getJobs()
    {
        return getElements(PlanSidebarOR.JobElement);
    }

    private List<WebElement> getElements(By by)
    {
        return tagContainer.findElements(by);
    }

}

