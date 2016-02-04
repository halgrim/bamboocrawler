package pl.hybris.bamboo.or;

import org.openqa.selenium.By;

/**
 * Created by i323728 on 2/3/16.
 */
public class CommonBambooOR
{
    public static final By buttonActions = By.cssSelector("button[aria-owns=\"buildMenuParent\"");
    public static final By buttonConfigurePlan = By.cssSelector("a[id^=\"editBuild:\"");
    public static final By menuTabsItemList = By.cssSelector(".tabs-menu");
    public static final By buttonRepositories = By.cssSelector("a[id^=\"repository_\"");
    public static final By buttonTasks = By.cssSelector("a[id^=\"tasks_\"");
}
