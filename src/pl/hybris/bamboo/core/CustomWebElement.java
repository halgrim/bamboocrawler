package pl.hybris.bamboo.core;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.hybris.bamboo.core.interfaces.WrapsWebElement;
import pl.hybris.bamboo.util.CommonUtil;

import java.util.List;


/**
 * Created by i323728 on 11/10/15.
 */
public class CustomWebElement implements WebElement, WrapsDriver, WrapsWebElement
{
    private final WebDriver driver;
	private final By by;
    private final WebDriverWait wait;
	private WebElement element;


	public CustomWebElement(WebElement webElement, By findBy)
	{
        this.driver = ((WrapsDriver) webElement).getWrappedDriver();
		this.by = findBy;
        this.element = webElement;
        this.wait = new WebDriverWait(driver, 5);
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
		for (int i = 0; i < 10; i++)
		{
			try
			{
				return element.getTagName();
			}
			catch (StaleElementReferenceException e)
			{
				CommonUtil.wait(500);
				CommonUtil.printMessage("++++++++++ CustomWebElement.getTagName() StaleElementReferenceException " + i);
				element = driver.findElement(by);
			}
		}

        CommonUtil.printMessage("++++++++++ Failed to execute CustomWebElement.getTagName()");
        throw new NoSuchElementException("++++++++++ Failed to execute CustomWebElement.getTagName()");
	}

	@Override
	public String getAttribute(String name)
	{
		for (int i = 0; i < 10; i++)
		{
			try
			{
				return element.getAttribute(name);
			}
			catch (StaleElementReferenceException e)
			{
				CommonUtil.wait(500);
				CommonUtil.printMessage("++++++++++ CustomWebElement.getAttribute() StaleElementReferenceException " + i);
                element = driver.findElement(by);
			}
		}

        CommonUtil.printMessage("++++++++++ Failed to execute CustomWebElement.getAttribute()");
        throw new NoSuchElementException("++++++++++ Failed to execute CustomWebElement.getAttribute()");

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
		for (int i = 0; i < 10; i++)
		{
			try
			{
				return element.getText();
			}
			catch (StaleElementReferenceException e)
			{
				CommonUtil.wait(500);
				CommonUtil.printMessage("++++++++++ CustomWebElement.getText() StaleElementReferenceException "+i);
                element = driver.findElement(by);
			}
		}
        CommonUtil.printMessage("++++++++++ Failed to execute CustomWebElement.getText()");
        throw new NoSuchElementException("++++++++++ Failed to execute CustomWebElement.getText()");
	}

	@Override
	public WebElement findElement(By byLocator)
	{
        for (int i = 0; i < 10; i++)
        {
            try
            {
                return new CustomWebElement(element.findElement(byLocator), byLocator);
            }
            catch (NoSuchElementException e)
            {
                CommonUtil.wait(500);
                CommonUtil.printMessage("++++++++++ CustomWebElement.findElement(By "+ byLocator .toString() +") IllegalStateException "+i);
            }
        }
        CommonUtil.printMessage("++++++++++ Failed to execute CustomWebElement.findElement()");
        throw new NoSuchElementException("++++++++++ Failed to execute CustomWebElement.findElement()");

	}

	@Override
	public List<WebElement> findElements(By by)
	{
        //you will get in a lot of trouble if you use By class to on WebElement from the list of WebElements returned from findElements(by);
//		List<WebElement> tempList = new ArrayList<>();
//		List<WebElement> tempWebElementList = element.findElements(by);
//		for (WebElement ele : tempWebElementList)
//		{
//			tempList.add(new CustomWebElement(ele, by));
//		}
//		return tempList;
        return  element.findElements(by);
	}

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
	public WebDriver getWrappedDriver()
	{
		return ((WrapsDriver) element).getWrappedDriver();
	}

	@Override
	public WebElement getWrappedWebElement()
	{
		return element;
	}

//    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//            .withTimeout( 30, TimeUnit.SECONDS )
//            .pollingEvery( 5, TimeUnit.SECONDS )
//            .ignoring( NoSuchElementException.class, StaleElementReferenceException.class );
//    // using a customized expected condition
//    WebElement foo1 = wait.until(new Function<WebDriver, WebElement>() {
//        public WebElement apply(WebDriver driver) {
//            return driver.findElement(By.id("foo1"));
//        }
//    });
//    // using a built-in expected condition
//    WebElement foo2 = wait.until( ExpectedConditions
//            .presenceOfElementLocated( By.id("foo2") ) );
//    // careful with this next one. it requires visibility attribute on html tag
//    WebElement foo3 = wait.until( ExpectedConditions
//            .visibilityOfElementLocated( By.id("foo3") ) );
}
