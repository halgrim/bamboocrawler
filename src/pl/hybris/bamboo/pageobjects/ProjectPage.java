package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.or.project.ProjectOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;

import java.util.List;

/**
 * Created by i323728 on 10/28/15.
 */
@Component
public class ProjectPage implements BasicPage
{
    CustomDriver driver;
    WebDriverWait wait;

    WebElement pageTag;
    List<WebElement> planTitles;

    @Autowired
    public ProjectPage(CustomDriver driver){
        this.wait = new WebDriverWait(driver, 3);
        this.driver = driver;
    }

    @Override
    public void synchronize()
    {
        pageTag = wait.until(ExpectedConditions.elementToBeClickable(ProjectOR.planListContainer));
        wait.until(ExpectedConditions.elementToBeClickable(ProjectOR.lastPlanOnTheList));
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


    public List<WebElement> getPlanPaneList(){
        if (planTitles ==null)
        {
            planTitles = driver.findElements(ProjectOR.singlePlanPane);
        }

        if (planTitles.isEmpty())
        {
            throw new NoSuchElementException("getPlanPaneList returned empty list");
        }

        return planTitles;

    }

}
