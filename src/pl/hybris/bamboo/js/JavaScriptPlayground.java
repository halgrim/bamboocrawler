package pl.hybris.bamboo.js;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import pl.hybris.bamboo.util.CommonUtil;


/**
 * Created by i323728 on 10/30/15.
 */
public class JavaScriptPlayground
{


	public static void elementHighlight(final WebElement element)
	{
		for (int i = 0; i < 2; i++)
		{
			final JavascriptExecutor js = (JavascriptExecutor) ((WrapsDriver) element).getWrappedDriver();
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 3px solid red;");
			CommonUtil.wait(500);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
	}

}
