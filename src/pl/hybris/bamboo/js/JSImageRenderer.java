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

    public String saveWebElementImageToFile(WebElement element) throws IOException
    {
        injectLibs();
        String saveElementToFile = path.readSaveWebElementToFileJS();
        String imageFileName = path.generateImageFileName();
        js.executeAsyncScript(saveElementToFile, element, imageFileName );
        return imageFileName;
    }

    private void injectLibs() throws IOException
    {
        String loadLibs = path.readLoadLibrariesJS();
        js.executeAsyncScript(loadLibs);

    }

}
