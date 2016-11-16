package com.servicenow.gurukula.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


/**
 * Created by pphelan on 11/14/16.
 */
public class LoginPage extends GurukulaPage{


    By usernameLocator           = By.id("username");
    By passwordLocator           = By.id("password");
    By rememberMeLocator         = By.id("rememberMe");
    By loginButtonLocator        = By.cssSelector(".btn.btn-primary.ng-scope");
    By registrationLinkLocator   = By.xpath("//a[text()='Register a new account']");
    By forgotPasswordLinkLocator = By.xpath("//a[text()='Did you forget your password?']");
    By loginError                = By.cssSelector("div[translate='login.messages.error.authentication']");



    public LoginPage(WebDriver driver) {

        super(driver);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!driver.getCurrentUrl().contains("login")) {
            throw new IllegalStateException("Error: not on the gurukula login page");
        }
    }

    public LoginPage typeUsername(String username) {
        driver.findElement(usernameLocator).sendKeys(username);
        return this;
    }


    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }


    public LoginPage checkAutomaticLogin(boolean desiredCheckState) {

        boolean actualCheckState = driver.findElement(rememberMeLocator).isSelected();

        if ((actualCheckState) & (desiredCheckState)) {
            return this;
        }
        else if ((actualCheckState) & (!desiredCheckState)){
            driver.findElement(rememberMeLocator).click();
        }
        else if ((!actualCheckState) & (desiredCheckState)){
            driver.findElement(rememberMeLocator).click();
        }


        return this;



    }


    public HomePage submitValidLogin() {

        driver.findElement(loginButtonLocator).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HomePage(driver);
    }

    public LoginPage submitInvalidLogin() {

        driver.findElement(loginButtonLocator).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }


    public ResetPasswordPage clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLinkLocator).click();
        return new ResetPasswordPage(driver);
    }

    public RegistrationPage clickRegistrationLink() {
        driver.findElement(registrationLinkLocator).click();
        return new RegistrationPage(driver);
    }


    public boolean loginBad() {
        if (driver.findElement(loginError).isDisplayed()){
            return true;
        }
        else{
            return false;
        }
    }






}
