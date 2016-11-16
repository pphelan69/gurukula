package com.servicenow.gurukula.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by pphelan on 11/14/16.
 */
public class BranchViewPage extends GurukulaPage {

    public BranchViewPage(WebDriver driver) {

        super(driver);


        if (!driver.getCurrentUrl().contains("branch")) {
            throw new IllegalStateException("Error: not on the branch view page");
        }

    }
}


