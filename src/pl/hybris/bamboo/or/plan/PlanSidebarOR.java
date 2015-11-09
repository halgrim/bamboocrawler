package pl.hybris.bamboo.or.plan;

import org.openqa.selenium.By;

/**
 * Created by i323728 on 11/6/15.
 */
public class PlanSidebarOR
{
    public static final By Section_ConfigSidebar = By.cssSelector("#config-sidebar");

    public static final By StageElement = By.cssSelector("ol > li");
    public static final By JobElement = By.cssSelector("ol > li > ul > li > a");
}
