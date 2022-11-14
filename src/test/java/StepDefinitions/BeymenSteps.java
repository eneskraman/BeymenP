package StepDefinitions;

import Pages.DialogContent;
import Utilities.ExcelUtilities;
import Utilities.GWD;

import io.cucumber.java.en.*;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BeymenSteps {

    DialogContent dc = new DialogContent();
    Actions actions = new Actions(GWD.driver);
    WebDriverWait wait = new WebDriverWait(GWD.driver,Duration.ofSeconds(20));

    @Given("Navigate to Beymen Website and Verify that you are on the homepage")
    public void navigateToBeymenWebsiteAndVerifyThatYouAreOnTheHomepage() {
        GWD.getDriver().get("https://www.beymen.com/");
        GWD.getDriver().manage().window().maximize();

        dc.findAndClick("acceptCookies");
        Assert.assertTrue(dc.homepageVerify.isDisplayed());
    }

    @When("Search for -sort- with Apache POI and delete the word -sort-")
    public void searchForSortWithApachePOIAndDeleteTheWordSort()  {

        ArrayList<ArrayList<String>> tablo =
                ExcelUtilities.getListData("src/test/java/Resources/ApacheExcel2.xlsx", "testCitizen", 1);

                dc.findAndSend("searchInput", tablo.get(0).get(0));

    }

    @And("Search for -Gomlek- with Apache POI and Press enter on keyboard")
    public void searchForGomlekWithApachePOIAndPressEnterOnKeyboard() throws AWTException {

        Robot robot = new Robot();
        for (int i = 0; i < 5; i++) {
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        }

        ArrayList<ArrayList<String>> tablo =
                ExcelUtilities.getListData("src/test/java/Resources/ApacheExcel2.xlsx", "testCitizen", 2);

        dc.findAndSend("searchInput", tablo.get(0).get(1));

        actions.keyDown(Keys.ENTER).build().perform();
        actions.keyUp(Keys.ENTER).build().perform();


    }
    @And("Choose random product")
    public void chooseRandomProduct() {


        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class='m-productCard__desc']")));

        List<WebElement> shirtList=GWD.getDriver().findElements(By.cssSelector("[class='m-productCard__desc']"));

        int random=(int)(Math.random() * shirtList.size());

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='m-productCard__desc']")));

        shirtList.get(random).click();

    }

    @And("Write the product information and product amount to the text file")
    public void writeTheProductInformationAndProductAmountToTheTextFile(){

        WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='m-price__new']")));
        System.out.println("price.getText() = " + price.getText());

        WebElement info = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='a-productFeature']>span")));
        System.out.println("info.getText() = " + info.getText());

        try{

            File dosya = new File("C:\\Users\\ASUS\\Desktop\\productInfo.txt");
            FileWriter yazici = new FileWriter(dosya,true);
            BufferedWriter yaz = new BufferedWriter(yazici);
            yaz.write("Price  :  " + price.getText());
            yaz.write("  -  Information  :  " + info.getText());
            yaz.close();
            System.out.println("Successfully");
        }
        catch (Exception hata){
            hata.printStackTrace();
        }
    }

    @And("Add product to cart")
    public void addProductToCart() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class*='m-variation__item']")));

        List<WebElement> sizeList =GWD.getDriver().findElements(By.cssSelector("span:not(.disabled)[class='m-variation__item'],[class*='criticalStock']"));

        System.out.println("sizeList.size() = " + sizeList.size());

        int random=(int)(Math.random() * sizeList.size());

        dc.scrollToElement(sizeList.get(random));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span:not(.disabled)[class='m-variation__item'],[class*='criticalStock']")));

        sizeList.get(random).click();

        dc.findAndClick("addToCart");
    }

    @And("Check if the price on the product page is the same as the price in the cart.")
    public void checkIfThePriceOnTheProductPageIsTheSameAsThePriceInTheCart() {

        WebElement price01 = GWD.driver.findElement(By.cssSelector("[class='m-price__new']"));
        System.out.println("price01.getText() = " + price01.getText());

        String price1 = price01.getText();

        System.out.println("price1 = " + price1);

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[title='Sepetim']")));

        dc.findAndClick("basket");

        WebElement price02 = GWD.driver.findElement(By.cssSelector("[class='m-productPrice__salePrice']"));

        String price2 = price02.getText();

        System.out.println("price2 = " + price2);

        Assert.assertEquals(price1,price2);

    }

    @And("Add one more of the product and verify that the quantity is two.")
    public void addOneMoreOfTheProductAndVerifyThatTheQuantityIsTwo() {

        if ( dc.productSize.size() > 1  ){

        Select select = new Select(dc.selectMenu);

        select.selectByValue("2");

            GWD.driver.navigate().back();

            dc.findAndContainsText("quantity","(2)");

            dc.findAndClick("basket");

        } else {
            System.out.println("Product quantity is one");
        }

    }

    @Then("Delete product from cart and verify deletion")
    public void deleteProductFromCartAndVerifyDeletion() {


        dc.findAndClick("deleteBtn");

        dc.findAndContainsText("deleteVerify","Sepetinizde");

    }
}
