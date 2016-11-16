package com.servicenow.gurukula.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pphelan on 11/14/16.
 */
public class HomePage extends GurukulaPage{


    By loginLinkLocator          = By.xpath("//a[text()='login']");
    By registrationLinkLocator   = By.xpath("//a[text()='Register a new account']");
    By text_UserLoggedIn         = By.cssSelector("div[translate='main.logged.message']");


    // You are logged in as user "admin".

    public HomePage(WebDriver driver) {
        super(driver);

    }

    public LoginPage clickLoginLink() {
        driver.findElement(loginLinkLocator).click();
        return new LoginPage(driver);
    }

    public RegistrationPage clickRegistrationLink() {
        driver.findElement(registrationLinkLocator).click();
        return new RegistrationPage(driver);
    }

    public String userLoggedin() {
        if (driver.findElement(text_UserLoggedIn).isDisplayed()){
            return driver.findElement(text_UserLoggedIn).getText();
        }
        else{
            return "";
        }
    }








}
