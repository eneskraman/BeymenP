package Pages;

import Utilities.GWD;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DialogContent extends Parent {

    public DialogContent() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    @FindBy(css = "[id='onetrust-accept-btn-handler']")
    private WebElement acceptCookies;

    @FindBy(css = "img[alt='Beymen']")
    public WebElement homepageVerify;

    @FindBy(css = "[placeholder='Ürün, Marka Arayın']")
    private WebElement searchInput;

    @FindBy(css = "a[href*='gomlek']")
    private List<WebElement> shirts;

    @FindBy(css = "[id='addBasket']")
    private WebElement addToCart;

    @FindBy(css = "[title='Sepetim']")
    private WebElement basket;

    @FindBy(css ="[class='m-price__new']")
    private WebElement price1;

    @FindBy(css ="[class='m-productPrice__salePrice']")
    private WebElement price2;

    @FindBy(id = "quantitySelect0-key-0")
    public WebElement selectMenu;

    @FindBy(xpath = "//span[text()='(2)']")
    private WebElement quantity;

    @FindBy(id = "removeCartItemBtn0-key-0")
    private WebElement deleteBtn;

    @FindBy(xpath = "//strong[text()='Sepetinizde Ürün Bulunmamaktadır']")
    private WebElement deleteVerify;

    @FindBy(xpath = "//select[@id='quantitySelect0-key-0']//*")
    public List<WebElement> productSize;

    WebElement myElement;

    public void findAndSend(String strElement, String value) {  // 2.aşama
        // burda string isimden weblemente ulaşıcam
        switch (strElement) {

            case "searchInput":
                myElement = searchInput;
                break;

        }

        sendKeysFunction(myElement, value);
    }

    public void findAndClick(String strElement) {  // 2.aşama
        // burda string isimden weblemente ulaşıcam
        switch (strElement) {
            case "addToCart":
                myElement = addToCart;
                break;
            case "basket":
                myElement = basket;
                break;
            case "acceptCookies":
                myElement = acceptCookies;
                break;
            case "selectMenu":
                myElement = selectMenu;
                break;
            case "deleteBtn":
                myElement = deleteBtn;
                break;


        }

        clickFunction(myElement);

    }

    public void findAndContainsText(String strElement, String text) {  // 2.aşama
        // burda string isimden weblemente ulaşıcam
        switch (strElement) {
            case "homepageVerify":
                myElement = homepageVerify;
                break;
            case "price1":
                myElement = price1;
                break;
            case "quantity":
                myElement = quantity;
                break;
            case "price2":
                myElement = price2;
                break;
            case "deleteVerify":
                myElement = deleteVerify;
                break;

        }

        verifyContainsText(myElement, text);
    }

}
