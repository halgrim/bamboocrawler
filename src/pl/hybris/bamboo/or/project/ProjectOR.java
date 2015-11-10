package pl.hybris.bamboo.or.project;


import org.openqa.selenium.By;

/**
 * Created by i323728 on 10/28/15.
 */
public class ProjectOR
{
    public static final By planListContainer = By.id("dashboard");

    public static final By singlePlanPane = By.cssSelector(".project > tr");
    public static final By editPlanButton = By.cssSelector("td:nth-child(6) > a:nth-child(5)");

    public static final By lastPlanOnTheList = By.cssSelector(":nth-child(66)");
}
