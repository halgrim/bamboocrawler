package pl.hybris.bamboo.core.interfaces;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by i323728 on 10/30/15.
 */

public interface CustomDriver extends WebDriver
{
    WebDriver getDriver();
    JavascriptExecutor getJSInjector();
}
