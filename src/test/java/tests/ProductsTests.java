package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ProductsPage;

import java.awt.*;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class ProductsTests {

    ProductsPage productsPage = new ProductsPage();
    LoginTests loginTest = new LoginTests();

    @BeforeAll
    public static void setUpAll() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        Configuration.browserSize = gd.getDisplayMode().getWidth() + "x" + gd.getDisplayMode().getHeight();
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("https://www.saucedemo.com/");
        loginTest.login();
    }

    @AfterEach
    public void cleanUp() {
        closeWebDriver();
    }

    @Test
    public void addProductToCart() {
        productsPage.addProduct("Sauce Labs Backpack").contentEquals("1");
    }

    @Test
    public void removeProductFromCart() {
        addProductToCart();
        productsPage.removeProduct("Sauce Labs Backpack").contentEquals("0");
    }

    @Test
    public void addThreeProductsToCart() {
        productsPage.addProduct("Sauce Labs Backpack");
        productsPage.addProduct("Sauce Labs Bike Light");
        productsPage.addProduct("Sauce Labs Bolt T-Shirt").contentEquals("3");
    }

    @Test
    public void goToCart() {
        productsPage.openCart();
    }

    @Test
    public void getAllProducts() {
        try {
            productsPage.getProducts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}