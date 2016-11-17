package com.servicenow.gurukula.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by pphelan on 11/16/2016.
 */
public class BranchDeleteConfirmationDialog {

    private final WebDriver driver;

    By deleteConfirmMsg       = By.cssSelector("p[translate='gurukulaApp.branch.delete.question']");
    By deleteCancelBtn        = By.cssSelector("span[translate='entity.action.cancel']");
    By deleteDeleteBtn        = By.cssSelector("span[translate='entity.action.delete']");


    public BranchDeleteConfirmationDialog(WebDriver driver) {
        this.driver = driver;
    }


    public String getBranchDeleteConfirmMsg() {
        WebElement codeTextField = driver.findElement(deleteConfirmMsg);
        return codeTextField.getText();
    }

    public BranchPage clickCancel() {
        WebElement cancelButton = driver.findElement(deleteCancelBtn);
        cancelButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BranchPage(driver);
    }

    public BranchPage clickDelete() {
        WebElement deleteBtn = driver.findElement(deleteDeleteBtn);
        deleteBtn.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BranchPage(driver);
    }



}
