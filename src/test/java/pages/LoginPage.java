package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

// page_url = https://www.saucedemo.com/
public class LoginPage {


    public static void userNameInput(String userName){
        $(byId("user-name")).setValue(userName);
        $(byId("user-name")).should(Condition.value(userName));
    }

    public void passwordInput(String password){
        $(byId("password")).setValue(password);
        $(byId("password")).should(Condition.value(password));
    }
    public void loginSubmit(){
        $(byId("login-button")).click();
        webdriver().shouldHave(url("https://www.saucedemo.com/inventory.html"));
    }
}