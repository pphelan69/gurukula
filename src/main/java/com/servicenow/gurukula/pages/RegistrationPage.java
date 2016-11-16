package com.servicenow.gurukula.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pphelan on 11/14/16.
 */
public class RegistrationPage extends GurukulaPage{



    By usernameLocator      = By.id("username");
    By passwordLocator      = By.id("password");
    By loginButtonLocator   = By.cssSelector(".btn.btn-primary.ng-scope");



    public RegistrationPage(WebDriver driver) {

        super(driver);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!driver.getCurrentUrl().contains("register")) {
            throw new IllegalStateException("Error: not on the gurukula registration page");
        }
    }
}
