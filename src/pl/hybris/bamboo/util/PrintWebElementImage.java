package pl.hybris.bamboo.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.hybris.bamboo.js.JavaScriptPlayground;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

//import org.openqa.selenium.Point;


/**
 * Created by i323728 on 10/29/15.
 */
public class PrintWebElementImage
{
	public PrintWebElementImage(WebDriver driver, List<WebElement> ele)
	{

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = null;
		try
		{
			fullImg = ImageIO.read(screenshot);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		for (WebElement element : ele){
			try
			{
				UUID idOne = UUID.randomUUID();
				//Get entire page screenshot

				JavaScriptPlayground.elementHighlight(element);
				//Get the location of element on the page
				Point point = element.getLocation();
				//Get width and height of the element
				int eleWidth = element.getSize().getWidth();
				int eleHeight = element.getSize().getHeight();
				//Crop the entire page screenshot to get only element screenshot
				BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);

				ImageIO.write(eleScreenshot, "png", screenshot);
				//Copy the element screenshot to disk
				FileUtils.copyFile(screenshot, new File("images2/" + idOne + ".png"));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}
}
