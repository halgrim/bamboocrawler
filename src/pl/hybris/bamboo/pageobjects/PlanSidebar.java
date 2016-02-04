package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.hybris.bamboo.or.plan.PlanSidebarOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;

import java.util.List;

/**
 * Created by i323728 on 11/6/15.
 */

public class PlanSidebar implements BasicPage
{
    WebDriver driver;

    WebElement pageTag;

    public PlanSidebar(final WebDriver driver)
    {
        this.driver = driver;
    }

    @Override
    public void synchronize()
    {
        pageTag = driver.findElement(PlanSidebarOR.Section_ConfigSidebar);
        pageTag.getText();

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

    public List<WebElement> getStages()
    {

        return getElements(PlanSidebarOR.StageElement);

    }

    public List<WebElement> getJobs()
    {
        return getElements(PlanSidebarOR.JobElement);
    }

    private List<WebElement> getElements(final By by)
    {
        return pageTag.findElements(by);
    }

    public PlanSidebar selectJobByName(final String jobName)
    {
        final List<WebElement> jobs = getJobs();
        for(final WebElement ele : jobs)
        {
            if(ele.getText().contains(jobName))
            {
                ele.click();
                return this;
            }
        }
        throw new NoSuchElementException("++++++++++ Failed to execute PlanSidebar.selectJobByName() with value " + jobName);
    }

    public PlanSidebar navigateToPlanConfiguration()
    {
        pageTag.findElement(PlanSidebarOR.ElementPlanConfiguration).click();
        return this;
    }
}

