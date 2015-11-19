package pl.hybris.bamboo.core.interfaces;

import org.openqa.selenium.WebElement;

/**
 * This interface indicates that the implementing class knows about the driver that contains it and
 * can export it.
 *
 * @author eran.mes@gmail.com (Eran Mes)
 */
public interface WrapsWebElement {
    /**
     * @return The driver that contains this element.
     */
    WebElement getWrappedWebElement();
}


