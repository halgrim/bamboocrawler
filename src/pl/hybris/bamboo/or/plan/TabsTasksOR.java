package pl.hybris.bamboo.or.plan;

import org.openqa.selenium.By;

/**
 * Created by i323728 on 11/3/15.
 */
public class TabsTasksOR
{
    public static final String blockImage ="";

    //
    //Common
    //

    public static final By TaskEditorSection = By.cssSelector("#panel-editor-setup");
    public static final By Button_SaveTask = By.cssSelector("#updateTask_save");
    public static final By SingleTaskItem = By.cssSelector(".item");
    public static final By AntTaskItem    = By.xpath(".//li[a/h3[contains(.,'Ant')]]");
    public static final By SourceCodeCheckoutItem = By.xpath(".//li[a/h3[contains(.,'Source Code Checkout')]]");
    public static final By FinalTaskElement = By.cssSelector(".final-tasks-bar");

    //
    //Ant
    //
    public static final By Header = By.cssSelector(".task-heading > h2");
    public static final By Input_Description = By.cssSelector("#updateTask_userDescription");
    public static final By Checkbox_TaskDisabled = By.cssSelector("#updateTask_taskDisabled"); //if checked has attribute checked="checked"
    public static final By Checkbox_TaskProduceTestResults = By.cssSelector("#testChecked"); //if checked has attribute checked="checked"
    public static final By Input_WorkingSubDirectory = By.cssSelector("#workingSubDirectory");
    public static final By Input_SpecifyCustomTestResultsDirectory = By.cssSelector("#testResultsDirectory");
    public static final By Button_Save = By.cssSelector("#updateTask_save");

    ///// has text: Source Code Checkout configuration
    // Input_Description
    // Checkbox_TaskDisabled
    public static final By Select_Repository = By.cssSelector("#selectedRepository_0");
    public static final By Input_CheckoutDir = By.cssSelector("#checkoutDir_0");
    public static final By Checkbox_ForceCleanBuild = By.cssSelector("#cleanCheckout");

    ///// has text: Script configuration
    // Input_Description
    // Checkbox_TaskDisabled
    // Input_WorkingSubDirectory
    public static final By Select_ScriptLocation = By.cssSelector("#scriptLocation");
    public static final By TextAreaAceEditor_ScriptBody = By.cssSelector("#scriptBody_value");


    ////// has text: Ant configuration
    // Input_Description
    // Checkbox_TaskDisabled
    public static final By Select_ExecutableVersion = By.cssSelector("#label");
    public static final By Input_BuildFile = By.cssSelector("#buildFile");
    public static final By Input_AntTargetAndProperties = By.cssSelector("#target");
    public static final By Select_BuildJDK = By.cssSelector("#buildJdk");
    public static final By Input_EnvironmentVariables = By.cssSelector("#environmentVariables");
    // Input_WorkingSubDirectory
    // Checkbox_TaskProduceTestResults
    // Input_SpecifyCustomTestResultsDirectory

    ////// has text: JUnit Parser configuration
    //Input_Description
    //TestResultsDir_Input

    ////// has text: Maven 3.x configuration
    // Input_Description
    // Checkbox_TaskDisabled
    // Select_ExecutableVersion
    public static final By MavenGoal = By.cssSelector("#goal");
    // Select_BuildJDK
    // Checkbox_TaskProduceTestResults
    public static final By Checkbox_SpecifyCustomTestResultsDirectory= By.cssSelector("#testDirectoryOptioncustomTestDirectory"); //if checked has attribute checked="checked"

    // Input_SpecifyCustomTestResultsDirectory


    //
    //Source Code Checkout
    //

    public static By Dropdown_Repository = By.cssSelector("#selectedRepository_0");





}
