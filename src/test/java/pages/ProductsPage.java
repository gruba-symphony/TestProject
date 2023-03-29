package pages;

import java.io.PrintWriter; // Step 1
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class ProductsPage {

    public String addProduct(String productName){
        $x("//div[@class='inventory_item' and contains(string(),'"+productName+"')]//button[@class='btn btn_primary btn_small btn_inventory']")
                .click();
        $x("//div[@class='inventory_item' and contains(string(),'"+productName+"')]//button[@class='btn btn_secondary btn_small btn_inventory']")
                .isDisplayed();
        return $(byClassName("shopping_cart_badge")).getText();
    }

    public String removeProduct(String productName){

        assertNotSame($(byClassName("shopping_cart_badge")).getText(), "0");
        //assertFalse($(byClassName("shopping_cart_badge")).getText() == "0");

        $x("//div[@class='inventory_item' and contains(string(),'"+productName+"')]//button[@class='btn btn_secondary btn_small btn_inventory']")
                .click();
        $x("//div[@class='inventory_item' and contains(string(),'"+productName+"')]//button[@class='btn btn_primary btn_small btn_inventory']")
                .isDisplayed();
        return $(byClassName("shopping_cart_link")).getText();
    }

    public void openCart(){
        $(byClassName("shopping_cart_link")).click();
        webdriver().shouldHave(url("https://www.saucedemo.com/cart.html"));
    }

    public static void getProducts() throws IOException{
        int i = 0;
        PrintWriter out = new PrintWriter("Items.txt");
        do {
            //System.out.println($(byId("item_"+i+"_title_link")).getText());
            out.println($(byId("item_"+i+"_title_link")).getText());
            i++;
        }while ($(byId("item_"+i+"_title_link")).exists());

        out.close();
    }

}
