package pl.hybris.bamboo.core;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pl.hybris.bamboo.util.CommonUtil;


/**
 * Created by i323728 on 11/18/15.
 */
public class CustomSelect
{
	private Select selectElement;
	private WebElement webElement;
	private WebElement currentlySelectedOption;

	public CustomSelect(final WebElement element)
	{
		this.selectElement = new Select(element);
		this.webElement = element;
	}

	/**
	 * @return The first selected option in this select tag (or the currently selected option in a normal select)
	 * @throws NoSuchElementException
	 *            If no option is selected
	 */

	public CustomSelect getFirstSelectedOption()
	{
		for (int i = 0; i < 10; i++)
		{
			try
			{
				for (final WebElement option : selectElement.getOptions())
				{
					if (option.isSelected())
					{
						currentlySelectedOption = option;
						return this;
					}
				}

				throw new NoSuchElementException("No options are selected");

			}
			catch (StaleElementReferenceException | IllegalStateException e)
			{
				CommonUtil.wait(500);
				CommonUtil.printMessage("++++++++++ CustomSelect.getFirstSelectedOption() " + e.getClass().getSimpleName() +" "+ i);
				selectElement = new Select(webElement);
			}
		}
		CommonUtil.printMessage("++++++++++ Failed to execute CustomSelect.getFirstSelectedOption()");
		throw new NoSuchElementException("++++++++++ Failed to execute CustomSelect.getFirstSelectedOption()");
	}

	public String getText()
	{
		if (currentlySelectedOption != null)
		{
			for (int i = 0; i < 5; i++)
			{
				try
				{
					return currentlySelectedOption.getText();
				} catch (StaleElementReferenceException | IllegalStateException e)
				{
					CommonUtil.wait(250);
					CommonUtil.printMessage("++++++++++ CustomSelect.getText() Looping Loops Be Careful." + e.getClass().getSimpleName() + " " +i);
					selectElement = new Select(webElement);
					this.getFirstSelectedOption();
				}
			}
		}
		CommonUtil.printMessage("++++++++++ Failed to execute CustomSelect.getText() element was null");
		throw new NoSuchElementException("++++++++++ Failed to execute CustomSelect.getText() element was null");

	}

	public CustomSelect selectByVisibleText(final String text)
	{
		for (int i = 0; i < 10; i++)
		{
			try
			{
				selectElement.selectByVisibleText(text);
				return this;

			}
			catch (StaleElementReferenceException | IllegalStateException e)
			{
				CommonUtil.wait(500);
				CommonUtil.printMessage("++++++++++ CustomSelect.selectByVisibleText() " + e.getClass().getSimpleName() +" "+ i);
				selectElement = new Select(webElement);
			}
		}
		CommonUtil.printMessage("++++++++++ Failed to execute CustomSelect.selectByVisibleText()");
		throw new NoSuchElementException("++++++++++ Failed to execute CustomSelect.selectByVisibleText() with text: " + text);
	}

}
