package com.servicenow.gurukula.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by pphelan on 11/14/16.
 */
public class SessionsPage extends GurukulaPage {


    public SessionsPage(WebDriver driver) {

        super(driver);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
