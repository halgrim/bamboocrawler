package pl.hybris.bamboo.core;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
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
@Scope("singleton")
public class SetUpChromeDriver implements CustomDriver
{

	private WebDriver driver;
	private JavascriptExecutor js;

	public SetUpChromeDriver()
	{

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		FileSystemUtil pathU = new FileSystemUtil();
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

	@Override
	public void get(String s)
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
	public List<WebElement> findElements(By by)
	{
		return driver.findElements(by);
	}

	@Override
	public WebElement findElement(By by)
	{
		return driver.findElement(by);
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
