package com.servicenow.gurukula.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by pphelan on 11/14/16.
 */
public class BranchCreateEditDialog {

    private final WebDriver driver;

    public BranchCreateEditDialog(WebDriver driver) {

        this.driver = driver;

        if (!driver.getCurrentUrl().contains("branch")) {
            throw new IllegalStateException("Error: not on the branch view page");
        }

    }
}
