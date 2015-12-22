package pl.hybris.bamboo.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;

import pl.hybris.bamboo.core.interfaces.WrapsWebElement;
import pl.hybris.bamboo.js.JSImageRenderer;


/**
 * Created by i323728 on 11/6/15.
 */
public class CommonUtil
{

	public static void printMessage(final String massage)
	{
		System.out.println(massage);
	}


	public static String takeScreenshot(final WebElement element)
	{
		final String filePath;

		final WebElement temp = ((WrapsWebElement) element).getWrappedWebElement();

		final JavascriptExecutor bla = (JavascriptExecutor) ((WrapsDriver) element).getWrappedDriver();
		final JSImageRenderer js = new JSImageRenderer(bla);
		filePath = js.saveWebElementImageToFile(temp);
		final FileSystemUtil path = new FileSystemUtil();
		for (int i = 0; i < 100; i++)
		{
			if (path.checkIfDownloadImageExist(filePath))
			{
                return filePath;
			}
			try
			{
				Thread.sleep(250);
			}
			catch (final InterruptedException e)
			{
				break;
			}
		}

        return "Failed to take a screenshot ";

	}

	public static void wait(final int millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch (final InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
