package com.servicenow.gurukula.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by pphelan on 11/14/16.
 */
public class BranchViewPage extends GurukulaPage {

    By branchDetails    = By.cssSelector(".input-sm.form-control");
    By backBtn          = By.cssSelector(".btn.btn-info");


    public BranchViewPage(WebDriver driver) {

        super(driver);

        if (!driver.getCurrentUrl().contains("branch")) {
            throw new IllegalStateException("Error: not on the branch view page");
        }

    }


    public String getBranchName() {
        List<WebElement> tr_collection = driver.findElements(branchDetails);
        return tr_collection.get(0).getAttribute("value");
    }

    public String getBranchCode() {
        List<WebElement> tr_collection = driver.findElements(branchDetails);
        return tr_collection.get(1).getAttribute("value");
    }

    public BranchPage clickBack() {
        WebElement backButton = driver.findElement(backBtn);
        backButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BranchPage(driver);
    }
}


