package com.servicenow.gurukula.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by pphelan on 11/14/16.
 */
public class PasswordPage extends GurukulaPage {

    public PasswordPage(WebDriver driver) {

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
}
