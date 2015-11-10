package pl.hybris.bamboo.pageactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.pageobjects.ProjectPage;

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

    public List<String> playWith()
    {
        ProjectPage projectPage = new ProjectPage(driver);
        projectPage.synchronize();
        projectPage.getPlanPaneList();
        return projectPage.getPlanConfigurationLinks();
    }



}
