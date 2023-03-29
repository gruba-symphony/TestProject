package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage {

    public void numberOfItemsInCart(int quantity){
        ElementsCollection selenideElements = $(byClassName("cart_list")).findAll(By.className("cart_item_label"));
        System.out.println(selenideElements.size());
        assertEquals(quantity, selenideElements.size());
    }
    public void checkoutCart(){
        $(byId("checkout")).click();
        webdriver().shouldHave(url("https://www.saucedemo.com/checkout-step-one.html"));
    }
    public void checkoutFirstNameInput(String firstname){
        $(byId("first-name")).setValue(firstname);
        assertEquals($(byId("first-name")).getValue(), firstname);
    }
    public void checkoutLastNameInput(String lastname){
        $(byId("last-name")).setValue(lastname);
        assertEquals($(byId("last-name")).getValue(), lastname);
    }
    public void checkoutZipInput(String zip){
        $(byId("postal-code")).setValue(zip);
        assertEquals($(byId("postal-code")).getValue(), zip);
    }
    public void continueCheckout(){
        $(byId("continue")).click();
        webdriver().shouldHave(url("https://www.saucedemo.com/checkout-step-two.html"));
    }
    public void finishCheckout(){
        $(byId("finish")).click();
        webdriver().shouldHave(url("https://www.saucedemo.com/checkout-complete.html"));
    }
    public void backToHomePage(){
        $(byId("back-to-products")).click();
        webdriver().shouldHave(url("https://www.saucedemo.com/inventory.html"));
    }

}
