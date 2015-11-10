package pl.hybris.bamboo.js;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pl.hybris.bamboo.util.FileSystemUtil;

import java.io.IOException;

/**
 * Created by i323728 on 10/30/15.
 */
public class JSImageRenderer
{

    private JavascriptExecutor js;
    private FileSystemUtil path;

    public JSImageRenderer(JavascriptExecutor jsExecutor){
        this.js = jsExecutor;
        this.path = new FileSystemUtil();
    }

    public String saveWebElementImageToFile(WebElement element)
    {
        injectLibs();
        String saveElementToFile = null;
        try
        {
            saveElementToFile = path.readSaveWebElementToFileJS();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        String imageFileName = path.generateImageFileName();
        js.executeAsyncScript(saveElementToFile, element, imageFileName);

        return imageFileName;
    }

    private void injectLibs()
    {

        String loadLibs = null;
        try
        {
            loadLibs = path.readLoadLibrariesJS();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        js.executeAsyncScript(loadLibs);
        //Object ret = js.executeAsyncScript(loadLibs);
        //CommonUtil.printMessage("--------- " +ret.toString());

    }

}
