package pl.hybris.bamboo.pageactions;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.js.JSImageRenderer;
import pl.hybris.bamboo.or.project.ProjectOR;
import pl.hybris.bamboo.pageobjects.ProjectPage;

import java.io.IOException;
import java.util.List;

/**
 * Created by i323728 on 10/29/15.
 */

@Component
public class ProjectAction
{

    CustomDriver driver;

    @Autowired
    public ProjectAction(CustomDriver driver){
        this.driver = driver;
    }

    public void navigateToBamboo(){
        driver.navigate().to("http://bamboo.hybris.com/browse/DBTPLA2");

    }

    public void quit(){
        driver.quit();
    }

    public void jsPlayWithPlanPanes() throws IOException
    {
        ProjectPage projectPage = new ProjectPage(driver);
        projectPage.synchronize();
        List<WebElement> list = projectPage.getPlanPaneList();
        JSImageRenderer jsInj = new JSImageRenderer(driver.getJSInjector());
        jsInj.saveWebElementImageToFile(list.get(0));


        //projectPage.getPlanPaneList().get(0).clickEditPlan();
    }


    public void navigateToFirstPlanConfiguration() throws IOException
    {
        ProjectPage projectPage = new ProjectPage(driver);
        projectPage.synchronize();
        List<WebElement> list = projectPage.getPlanPaneList();
        list.get(0).findElement(ProjectOR.editPlanButton).click();

    }


}
