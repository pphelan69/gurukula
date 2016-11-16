package com.servicenow.gurukula.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by pphelan on 11/14/16.
 */
public class ResetPasswordPage extends GurukulaPage {

    public ResetPasswordPage(WebDriver driver) {

        super(driver);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!driver.getCurrentUrl().contains("reset")) {
            throw new IllegalStateException("Error: not on the gurukula login page");
        }
    }
}
