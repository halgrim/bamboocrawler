package pl.hybris.bamboo.core;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.util.CommonUtil;
import pl.hybris.bamboo.util.FileSystemUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by i323728 on 10/29/15.
 */

@Component
//@Scope("singleton")
//This scopes the bean definition to a single instance per Spring IoC container (default).
public class SetUpChromeDriver implements CustomDriver, DisposableBean
{

	private final WebDriver driver;
	private final JavascriptExecutor js;

	public SetUpChromeDriver()
	{

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		final DesiredCapabilities caps = DesiredCapabilities.chrome();
		final ChromeOptions options = new ChromeOptions();
		final Map<String, Object> prefs = new HashMap<String, Object>();
		final FileSystemUtil pathU = new FileSystemUtil();
		prefs.put("download.default_directory", pathU.buildImagesDestinationPath());

		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--user-data-dir=chromeprofile");
		caps.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new ChromeDriver(caps);
		driver.manage().window().setSize(new Dimension(1440, 900));
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;

//		Much simpler version if you do not want to download images into specific folder
//		System.setProperty("webdriver.chrome.driver", "chromedriver");
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--user-data-dir=chromeprofile");
//		driver = new ChromeDriver(options);
//		driver.manage().window().setSize(new Dimension(1440, 900));
//		driver.manage().window().setPosition(new Point(0, 0));
//		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//		js = (JavascriptExecutor) driver;

	}

	public WebDriver getDriver()
	{
		return driver;
	}

	public JavascriptExecutor getJSInjector() {return js;}

	public void destroy() {
		driver.quit();
	}

	@Override
	public void get(final String s)
	{
		driver.get(s);
	}

	@Override
	public String getCurrentUrl()
	{
		return driver.getCurrentUrl();
	}

	@Override
	public String getTitle()
	{
		return driver.getTitle();
	}


	@Override
	public WebElement findElement(final By byLocator)
	{
		for (int i = 0; i < 10; i++)
		{
			try
			{
				return new CustomWebElement(driver.findElement(byLocator), byLocator);
			}
			catch (final IllegalStateException | NoSuchElementException e)
			{
				CommonUtil.wait(500);
				CommonUtil.printMessage("++++++++++ SetUpChromeDriver.findElement(By " + byLocator.toString() + ") Exception " + i);
			}
		}
		CommonUtil.printMessage("++++++++++ Failed to execute SetUpChromeDriver.findElement()");
		throw new NoSuchElementException("++++++++++ Failed to execute SetUpChromeDriver.findElement()");
	}

	@Override
	public List<WebElement> findElements(final By by)
	{
		return driver.findElements(by);
	}

	@Override
	public String getPageSource()
	{
		return driver.getPageSource();
	}

	@Override
	public void close()
	{
		driver.close();
	}

	@Override
	public void quit()
	{
		driver.quit();
	}

	@Override
	public Set<String> getWindowHandles()
	{
		return driver.getWindowHandles();
	}

	@Override
	public String getWindowHandle()
	{
		return driver.getWindowHandle();
	}

	@Override
	public TargetLocator switchTo()
	{
		return driver.switchTo();
	}

	@Override
	public Navigation navigate()
	{
		return driver.navigate();
	}

	@Override
	public Options manage()
	{
		return driver.manage();
	}
}
