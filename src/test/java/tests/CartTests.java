package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CartPage;

import java.awt.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class CartTests{
    CartPage cartPage = new CartPage();
    LoginTests loginTests = new LoginTests();
    ProductsTests productsTests = new ProductsTests();

    @BeforeAll
    public static void setUpAll() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        Configuration.browserSize = gd.getDisplayMode().getWidth() + "x" + gd.getDisplayMode().getHeight();
    }

    @BeforeEach
    public void setUp(){
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("https://www.saucedemo.com/");
        loginTests.login();
    }

    @AfterEach
    public void cleanUp(){
        closeWebDriver();
    }

    @Test
    public void verifyCartContents(){
        productsTests.addThreeProductsToCart();
        productsTests.goToCart();
        cartPage.numberOfItemsInCart(3);
    } //Test 2 - Done

    @Test
    public void checkoutCart(){
        productsTests.addThreeProductsToCart();
        productsTests.goToCart();
        cartPage.checkoutCart();
        cartPage.checkoutFirstNameInput("John");
        cartPage.checkoutLastNameInput("Smith");
        cartPage.checkoutZipInput("90001");
        cartPage.continueCheckout();
        cartPage.finishCheckout();
        cartPage.backToHomePage();
    } //Test 4 - Done
}
