package pl.hybris.bamboo.or.plan;

import org.openqa.selenium.By;

/**
 * Created by i323728 on 2/4/16.
 */
public class TabsRepositoriesOR
{

    //used in code By.xpath(".//li[a/h3[contains(.,'" + repoDescritpion + "')]]/ancestor::li")
    public static By blockNewRepositoryStrip = By.xpath(".//ul/li[a/h3[contains(.,'New Repository')]]");

    public static By buttonDelete = By.xpath(".delete");

    public static By buttonAddRepo = By.cssSelector("#addRepository");

    public static By divNoItemSelected = By.cssSelector("#no-item-selected");

    public static By dropdownRepoHost = By.cssSelector("#selectedRepository");
    public static By inputRepositoryName = By.cssSelector("#repositoryName");
    public static By inputRepoURL = By.cssSelector("#createRepository_repository_git_repositoryUrl");
    public static By inputBranch = By.cssSelector("#createRepository_repository_git_branch");
    public static By dropdownAuthType = By.cssSelector("#createRepository_repository_git_authenticationType");

    public static By buttonSaveRepo = By.cssSelector("#createRepository_save");

    public static By buttonConfirmDeleteRepository = By.cssSelector("#deleteRepository_save");


}

