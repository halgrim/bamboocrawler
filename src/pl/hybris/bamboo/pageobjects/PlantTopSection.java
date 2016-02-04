package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pl.hybris.bamboo.or.CommonBambooOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;

/**
 * Created by i323728 on 2/3/16.
 */
public class PlantTopSection implements BasicPage
{
    WebDriver driver;

    WebElement pageTag;

    public PlantTopSection(final WebDriver driver)
    {
        this.driver = driver;
    }

    @Override
    public void synchronize()
    {
        pageTag = driver.findElement(CommonBambooOR.buttonActions);
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

    public void navigateToPlanConfiguration()
    {

        pageTag.click();

        WebElement buttonConfigurePlan = driver.findElement(CommonBambooOR.buttonConfigurePlan);
        buttonConfigurePlan.click();
    }
}
