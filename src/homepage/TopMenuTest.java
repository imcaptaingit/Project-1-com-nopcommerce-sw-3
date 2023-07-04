package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;


import java.util.List;

public class TopMenuTest extends Utility {

    String baseURL = "https://demo.nopcommerce.com/";
    String menu = "computers";

    @Before
    public void setBaseURL() {
        openBrowser(baseURL);
    }

    //1.1 and 1.2 will click the given menu from the top-menu
    public void selectMenu(String menu) {
        //Top menu elements in a list
        List<WebElement> elements = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']//child::li"));
        //Finding the computers tab and clicking on it.
        for (WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(menu)) {
                element.click();
                break;
            }
        }
    }

    //1.3
    @Test
    public void verifyPageNavigation() {
        selectMenu(menu);
        String expectedMessage = "Computers";
        String actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Computers']"));
        Assert.assertEquals("User is not navigated to the tab", expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        driver.close();
    }


}
