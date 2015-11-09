package pl.hybris.bamboo.or.plan;

import org.openqa.selenium.By;


/**
 * Created by i323728 on 11/3/15.
 */
public class StageBlockOR
{

	public static final By Tabs = By.cssSelector(".tabs-menu");
	public static final By PaneHeader = By.cssSelector(".tabs-pane > h1");

	public static final By TaskListSection = By.cssSelector("#panel-editor-list");

	public static final By SingleTaskItem = By.cssSelector(".item");
	public static final By AntTaskItem    = By.xpath(".//ul/li[a/h3[contains(.,'Ant')]]");
	public static final By FinalTaskElement = By.cssSelector(".final-tasks-bar");

}

