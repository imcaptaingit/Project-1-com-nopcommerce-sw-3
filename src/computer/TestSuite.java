package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {
    String menu;
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setBaseUrl() {

        openBrowser(baseUrl);

    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        //1.1
        mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        //mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));

        //1.2
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        //1.3 and //1.4

        List<WebElement> beforeSelectElementList = driver.findElements(By.xpath("//div/h2[@class='product-title']"));
        List<String> beforeSelectElementList1 = new ArrayList<>();
        for (WebElement list : beforeSelectElementList) {
            beforeSelectElementList1.add(String.valueOf(list.getText()));
        }

        selectFromDropDownMenu(By.xpath("//select[@id='products-orderby']"), "Name: Z to A");
        List<WebElement> afterSelectElementList = driver.findElements(By.xpath("//h2[@class='product-title']"));
        List<String> afterSelectElementList1 = new ArrayList<>();
        for (WebElement list : afterSelectElementList) {
            afterSelectElementList1.add(String.valueOf(list.getText()));
        }

        Collections.sort(beforeSelectElementList1);
        //  Collections.reverse(afterSelectElementList1);
        Assert.assertEquals("Product is not displayed ", beforeSelectElementList1, afterSelectElementList1);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //2.1
        mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        //2.2
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        //2.3
        selectFromDropDownMenu(By.xpath("//select[@id='products-orderby']"), "Name: A to Z");
        //2.4 click on add to cart
        Thread.sleep(2000);
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        //2.5
        String expectedText = "Build your own computer";
        WebElement actualText = driver.findElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
        String actualText1 = actualText.getText();
        Assert.assertEquals("Error message, Product is not added to the cart", expectedText, actualText1);
        //2.6
        selectFromDropDownMenu(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");
        //2.7
        selectFromDropDownMenu(By.xpath("//select[@id='product_attribute_2']"), "8GB [+$60.00]");
        Thread.sleep(1000);
        //2.8
        selectCheckBox(By.xpath("//input[@id='product_attribute_3_7']"));
        //2.9
        selectCheckBox(By.xpath("//input[@id='product_attribute_4_9']"));
        Thread.sleep(1000);
        //2.10

        selectCheckBox(By.xpath("//input[@id='product_attribute_5_12']"));
        //2.11
        String expectedPrice = "$1,475.00";
        String actualPrice = driver.findElement(By.xpath("//span[text()='$1,475.00']")).getText();
        verifyElementMethod(expectedPrice, actualPrice);
        //
        Thread.sleep(1000);
        //2.12
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
        //2.13
        String expectedNotification = "The product has been added to your shopping cart";
        String actualNotification = driver.findElement(By.xpath("//div[@class='bar-notification success']/p")).getText();
        verifyElementMethod(expectedNotification, actualNotification);
        //
        Thread.sleep(1000);
        clickOnElement(By.xpath("//div[@class='bar-notification success']/span"));
        //2.14
        mouseHoverOnElement(By.xpath("//span[@class='cart-label']"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@class='button-1 cart-button']"));
        //2.15
        String expectedCart = "Shopping cart";
        String actualCart = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
        verifyElementMethod(expectedCart, actualCart);
        Thread.sleep(2000);
        //2.16
        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.xpath("//button[@class='button-2 update-cart-button']"));
        //2.17
        String expectedTotal = "$2,950.00";
        String actualTotal = driver.findElement(By.xpath("//td[@class='subtotal']/span[text()='$2,950.00']")).getText();
        verifyElementMethod(expectedTotal, actualTotal);
        //2.18
        selectCheckBox(By.xpath("//input[@id='termsofservice']"));
        //2.19
        clickOnElement(By.xpath("//button[@id='checkout']"));
        //2.20
        String expectedVerifyWelcome = "Welcome, Please Sign In!";
        String actualVerifyWelcome = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
        verifyElementMethod(expectedVerifyWelcome, actualVerifyWelcome);
        //2.21
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));
        //2.22
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Sarvat");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Shaikh");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "iamboss@gmail.com");
        selectFromDropDownMenu(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        Thread.sleep(1000);
        selectFromDropDownMenu(By.xpath("//select[@id='BillingNewAddress_StateProvinceId']"), "Other");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "283 High Road");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "E11 4HH");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "+449825868472");
        //2.23
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));
        //2.24
        selectCheckBox(By.xpath("//input[@id='shippingoption_1']"));
        //2.25
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[2]/button[1]"));
        //2.26
        selectCheckBox(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]"));
        //2.27
        selectFromDropDownMenu(By.xpath("//select[@id='CreditCardType']"), "Master card");
        //2.28
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "iamsarvat");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "11111111111111111111");
        selectFromDropDownMenu(By.xpath("//select[@id='ExpireMonth']"), "08");
        selectFromDropDownMenu(By.xpath("//select[@id='ExpireYear']"), "2034");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "007");
        Thread.sleep(1000);
        //2.29
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]"));
        //2.30
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        // Verify “Payment Method” is “Credit Card”
        String expectedPaymenMethod = "Credit Card";
        String actualPaymentMethod = driver.findElement(By.xpath("//li[@class='payment-method']/span[@class='value']")).getText();
        Thread.sleep(1000);
        Assert.assertEquals("not credit card", expectedPaymenMethod, actualPaymentMethod);
        // Verify “Shipping Method” is “Next Day Air”
        String expectedShippingMethod = "Next Day Air";
        String actualShippingMethod = getTextFromElement(By.xpath("//li[@class='shipping-method']/span[@class='value']"));
        Thread.sleep(1000);
        Assert.assertEquals("not next day air", expectedShippingMethod, actualShippingMethod);
        // 2.33 Verify Total is “$2,950.00”
        String expectedTotalAmount = "$2,950.00";
        String actualTotalAmount = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        // 2.34 Click on “CONFIRM”
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']"));
        //2.35
        String expectedSuccessfullyProcessed = "Your order has been successfully processed!";
        String actualSuccessfullyProcessed = getTextFromElement(By.xpath("//div[@class='section order-completed']/div[@class='title']/strong"));
        // Verify the message “Your order has been successfully processed!”
        Assert.assertEquals("Not processed", expectedSuccessfullyProcessed, actualSuccessfullyProcessed);
        // 2.36
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));
        // 2.37
        String expectedWelcomeMessage = "Welcome to our store";
        String actualWelcomeMessage = getTextFromElement(By.xpath("//div[@class='topic-block-title']/h2"));

        Thread.sleep(1000);
        Assert.assertEquals("Not successfully processed", expectedWelcomeMessage, actualWelcomeMessage);

    }
    @After
    public void tearDown(){
        driver.close();
    }
}
