package com.servicenow.gurukula.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by pphelan on 11/16/2016.
 */
public class BranchStaffDeleteConfirmDialog {

    private final WebDriver driver;

    By deleteBranchConfirmMsg   = By.cssSelector("p[translate='gurukulaApp.branch.delete.question']");
    By deleteStaffConfirmMsg    = By.cssSelector("p[translate='gurukulaApp.staff.delete.question']");

    By cancelButton        = By.cssSelector("span[translate='entity.action.cancel']");
    By deleteButton        = By.cssSelector("span[translate='entity.action.delete']");


    public BranchStaffDeleteConfirmDialog(WebDriver driver) {
        this.driver = driver;
    }


    public String getBranchDeleteConfirmMsg() {
        WebElement codeTextField = driver.findElement(deleteBranchConfirmMsg);
        return codeTextField.getText();
    }

    public String getStaffDeleteConfirmMsg() {
        WebElement codeTextField = driver.findElement(deleteStaffConfirmMsg);
        return codeTextField.getText();
    }

    public BranchStaffPage clickCancel() {
        WebElement cancelBtn = driver.findElement(cancelButton);
        cancelBtn.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BranchStaffPage(driver);
    }

    public BranchStaffPage clickDelete() {
        WebElement deleteBtn = driver.findElement(deleteButton);
        deleteBtn.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BranchStaffPage(driver);
    }



}
