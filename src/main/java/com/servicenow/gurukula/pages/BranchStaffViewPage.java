package com.servicenow.gurukula.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by pphelan on 11/14/16.
 */
public class BranchStaffViewPage extends GurukulaPage {

    By branchDetails    = By.cssSelector(".input-sm.form-control");
    By backBtn          = By.cssSelector(".btn.btn-info");


    public BranchStaffViewPage(WebDriver driver) {
        super(driver);
    }


    public String getName() {
        List<WebElement> tr_collection = driver.findElements(branchDetails);
        return tr_collection.get(0).getAttribute("value");
    }

    public String getCode() {
        List<WebElement> tr_collection = driver.findElements(branchDetails);
        return tr_collection.get(1).getAttribute("value");
    }

    public BranchStaffPage clickBack() {
        WebElement backButton = driver.findElement(backBtn);
        backButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BranchStaffPage(driver);
    }
}


