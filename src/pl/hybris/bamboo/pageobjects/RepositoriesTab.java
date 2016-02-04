package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.hybris.bamboo.core.CustomSelect;
import pl.hybris.bamboo.or.plan.TabsOR;
import pl.hybris.bamboo.or.plan.TabsRepositoriesOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;

/**
 * Created by i323728 on 2/3/16.
 */
public class RepositoriesTab implements BasicPage
{
    WebDriver driver;

    WebElement pageTag;

    public RepositoriesTab(final WebDriver driver)
    {
        this.driver = driver;
    }

    @Override
    public void synchronize()
    {
        pageTag = driver.findElement(TabsOR.repositoriesTab);
        CommonUtil.highlight(pageTag);

    }

    public RepositoriesTab syncNewRepository()
    {

        pageTag.findElement(TabsRepositoriesOR.blockNewRepositoryStrip);
        pageTag.findElement(TabsRepositoriesOR.buttonSaveRepo);
        return this;
    }

    @Override
    public WebElement getTag()
    {
        return pageTag;
    }

    @Override
    public String takePageScreenshot()
    {
        return CommonUtil.takeScreenshot(pageTag);
    }

    public void removeRepoByName(final String repoDescritpion)
    {

        final WebElement ele1 = pageTag.findElement(By.xpath(".//ul/li[a/h3[contains(.,'" + repoDescritpion + "')]]/a[contains(@class, 'delete')]"));
        CommonUtil.highlight(ele1);
        ele1.click();

        driver.findElement(TabsRepositoriesOR.buttonConfirmDeleteRepository).click();
        pageTag.findElement(TabsRepositoriesOR.divNoItemSelected);
    }

    public void clickAddRepository()
    {
        pageTag.findElement(TabsRepositoriesOR.buttonAddRepo).click();
    }

    public RepositoriesTab setRepositoryHost(final String text)
    {
        final CustomSelect ele = new CustomSelect(pageTag.findElement(TabsRepositoriesOR.dropdownRepoHost));
        ele.selectByVisibleText(text);
        return this;
    }

    public RepositoriesTab setDisplayName(final String text)
    {
        pageTag.findElement(TabsRepositoriesOR.inputRepositoryName).sendKeys(text);
        return this;
    }

    public RepositoriesTab setRepoURL(final String text)
    {
        pageTag.findElement(TabsRepositoriesOR.inputRepoURL).sendKeys(text);
        return this;
    }

    public RepositoriesTab setBranch(final String text)
    {
        pageTag.findElement(TabsRepositoriesOR.inputBranch).sendKeys(text);
        return this;
    }

    public RepositoriesTab setAuthType(final String text)
    {
        final CustomSelect ele = new CustomSelect(pageTag.findElement(TabsRepositoriesOR.dropdownAuthType));
        ele.selectByVisibleText(text);
        return this;
    }


    public void saveRepository()
    {
        pageTag.findElement(TabsRepositoriesOR.buttonSaveRepo).click();
        pageTag.findElement(TabsRepositoriesOR.divNoItemSelected);
    }


}

