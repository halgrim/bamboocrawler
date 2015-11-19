package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.hybris.bamboo.core.interfaces.CustomDriver;
import pl.hybris.bamboo.or.project.ProjectOR;
import pl.hybris.bamboo.pageobjects.interfaces.BasicPage;
import pl.hybris.bamboo.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by i323728 on 10/28/15.
 */
public class ProjectPage implements BasicPage
{
	CustomDriver driver;
	WebDriverWait wait;

	WebElement pageTag;
	List<WebElement> planTitles;

	public ProjectPage(CustomDriver driver)
	{
		this.wait = new WebDriverWait(driver, 3);
		this.driver = driver;
	}

	@Override
	public void synchronize()
	{

		pageTag = driver.findElement(ProjectOR.planListContainer);

		for (int i = 0; i < 5; i++)
		{
			try
			{
				WebElement lastPlan = pageTag.findElement(ProjectOR.lastPlanOnTheList);
				lastPlan.findElement(ProjectOR.editPlanButton);
				break;
			}
			catch (NoSuchElementException e)
			{
				CommonUtil.wait(250);
				CommonUtil.printMessage("++++++++++ ProjectPage.synchronize() NoSuchElementException lastPlanOnTheList");
			}
		}

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


	public List<WebElement> getPlanPaneList()
	{
		if (planTitles == null)
		{
			planTitles = driver.findElements(ProjectOR.singlePlanPane);
		}

		if (planTitles.isEmpty())
		{
			throw new NoSuchElementException("getPlanPaneList returned empty list");
		}

		return planTitles;

	}

	public List<String> getPlanConfigurationLinks()
	{
		List<String> planConfigurationLinks = new ArrayList<>();
		int planTilesSize = planTitles.size();
		String link;
		String message = "";
		for (int i = 0; i < planTilesSize; i++)
		{
			WebElement temp = planTitles.get(i);
			WebElement editPlan;

			try
			{
				editPlan = temp.findElement(ProjectOR.editPlanButton);
				link = editPlan.getAttribute("href");
				if (link != null)
				{
					planConfigurationLinks.add(link);
				}
			}
			catch (NoSuchElementException e)
			{
				message = message + " Probably permission to edit plan is not set, image file of the plan pane: "
						+ CommonUtil.takeScreenshot(temp);
			}

		}

        if (planConfigurationLinks.size() != planTilesSize) // waiting for one plan permission
		{
			throw new NoSuchElementException("There should be equal number of plan tiles and plan configuration links. " + message);
		}

		return planConfigurationLinks;
	}

}
