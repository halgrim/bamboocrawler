package pl.hybris.bamboo.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import pl.hybris.bamboo.js.JSImageRenderer;

import java.io.IOException;

/**
 * Created by i323728 on 11/6/15.
 */
public class CommonUtil
{

    public static void printMessage(String massage)
    {
        System.out.println(massage);
    }


    public static String takeScreenshot(WebElement element)
    {
        String filePath;
        try
        {
            JavascriptExecutor bla = (JavascriptExecutor) ((WrapsDriver) element).getWrappedDriver();
            JSImageRenderer js = new JSImageRenderer(bla);
            filePath = js.saveWebElementImageToFile(element);
            FileSystemUtil path = new FileSystemUtil();
            for (int i = 0; i < 100; i++)
            {
                if (path.checkIfDownloadImageExist(filePath))
                {
                    break;
                }
                try
                {
                    Thread.sleep(100);
                    CommonUtil.printMessage("Waiting!");
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        } catch (IOException e)
        {
            filePath = "Failed to take a screenshot " + e.getLocalizedMessage();
        }

        return filePath;
    }
}
