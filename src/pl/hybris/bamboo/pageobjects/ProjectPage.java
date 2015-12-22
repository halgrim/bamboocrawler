package pl.hybris.bamboo.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	WebDriver driver;

	WebElement pageTag;
	List<WebElement> planTitles;

	public ProjectPage(final WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void synchronize()
	{

		pageTag = driver.findElement(ProjectOR.planListContainer);
		pageTag.getText();
		final WebElement lastPlan = pageTag.findElement(ProjectOR.lastPlanOnTheList);
		lastPlan.findElement(ProjectOR.editPlanButton);

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
			planTitles = pageTag.findElements(ProjectOR.singlePlanPane);
		}

		if (planTitles.isEmpty())
		{
			throw new NoSuchElementException("getPlanPaneList returned empty list");
		}

		return planTitles;

	}

	public List<String> getPlanConfigurationLinks()
	{
		final List<String> planConfigurationLinks = new ArrayList<>();
		final int planTilesSize = planTitles.size();
		String link;
		String message = "";
		for (int i = 0; i < planTilesSize; i++)
		{
			final WebElement temp = planTitles.get(i);
			final WebElement editPlan;

			try
			{
				editPlan = temp.findElement(ProjectOR.editPlanButton);
				link = editPlan.getAttribute("href");
				if (link != null)
				{
					planConfigurationLinks.add(link);
				}
			}
			catch (final NoSuchElementException e)
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
