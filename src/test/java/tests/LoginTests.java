package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import java.awt.*;

import static com.codeborne.selenide.Selenide.open;

public class LoginTests {
    LoginPage loginPage = new LoginPage();

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
    }

    @Test
    public void login() {

        loginPage.userNameInput("standard_user");
        loginPage.passwordInput("secret_sauce");
        loginPage.loginSubmit();
    }
}
