package pl.hybris.bamboo.core;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import pl.hybris.bamboo.core.interfaces.CustomElement;

import java.util.List;

/**
 * Created by i323728 on 11/10/15.
 */
public class CustomWebElement implements CustomElement
{

    private WebElement element;

    public CustomWebElement(WebElement element)
    {
        this.element = element;
    }

    @Override
    public void click()
    {
        element.click();
    }

    @Override
    public void submit()
    {
        element.click();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend)
    {
        element.sendKeys();
    }

    @Override
    public void clear()
    {
        element.clear();
    }

    @Override
    public String getTagName()
    {
        return element.getTagName();
    }

    @Override
    public String getAttribute(String name)
    {
        return element.getAttribute(name);
    }

    @Override
    public boolean isSelected()
    {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled()
    {
        return element.isEnabled();
    }

    @Override
    public String getText()
    {
        return element.getText();
    }

    @Override
    public List<WebElement> findElements(By by)
    {
        return element.findElements(by);
    }

//    @Override
//    public WebElement findElement(By by)
//    {
//        return element;
//    }

    @Override
    public boolean isDisplayed()
    {
        return element.isDisplayed();
    }

    @Override
    public Point getLocation()
    {
        return element.getLocation();
    }

    @Override
    public Dimension getSize()
    {
        return element.getSize();
    }

    @Override
    public String getCssValue(String propertyName)
    {
        return element.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException
    {
        return element.getScreenshotAs(target);
    }

    @Override
    public WebElement getPureWebElement()
    {
        return element;
    }

    @Override
    public CustomElement findElement(By by)
    {
        return new CustomWebElement(element.findElement(by));
    }
}
