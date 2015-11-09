package pl.hybris.bamboo.pageobjects.interfaces;

import org.openqa.selenium.WebElement;

/**
 * Created by i323728 on 10/28/15.
 */
public interface BasicPage
{
    void synchronize();
    WebElement getTag();
    String takePageScreenshot();

}
