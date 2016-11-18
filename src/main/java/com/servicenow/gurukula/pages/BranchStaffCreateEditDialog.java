package com.servicenow.gurukula.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by pphelan on 11/14/16.
 */
public class BranchStaffCreateEditDialog {

    private final WebDriver driver;

    By branchIDTF            = By.cssSelector("input[ng-model='branch.id']");
    By branchNameTF          = By.cssSelector("input[ng-model='branch.name']");
    By branchCodeTF          = By.cssSelector("input[ng-model='branch.code']");

    By staffIDTF             = By.cssSelector("input[ng-model='staff.id']");
    By staffNameTF           = By.cssSelector("input[ng-model='staff.name']");
    By staffCodeTF           = By.cssSelector("input[ng-model='staff.code']");

    By staffBranchDD         = By.cssSelector("select[ng-model='staff.related_branchId']");

    By branchSaveBtn          = By.cssSelector("span[translate='entity.action.save']");
    By branchCancelBtn        = By.cssSelector("span[translate='entity.action.cancel']");
    By minLengthError         = By.cssSelector("p[translate='entity.validation.minlength']");
    By allowCharsError        = By.cssSelector("p[translate='entity.validation.pattern']");



    public BranchStaffCreateEditDialog(WebDriver driver) {
        this.driver = driver;
    }


    public void setBranchID(String branchID) {
        WebElement idTextField = driver.findElement(branchIDTF);
        idTextField.sendKeys(branchID);
    }

    public String getBranchID() {
        WebElement idTextField = driver.findElement(branchIDTF);
        return idTextField.getText();
    }

    public void setBranchName(String branchName) {
        WebElement nameTextField = driver.findElement(branchNameTF);
        nameTextField.sendKeys(branchName);
    }

    public String getBranchName() {
        WebElement codeTextField = driver.findElement(branchNameTF);
        return codeTextField.getText();
    }

    public void setBranchCode(final String branchCode) {
        WebElement codeTextField = driver.findElement(branchCodeTF);
        codeTextField.sendKeys(branchCode);
    }

    public String getBranchCode() {
        WebElement codeTextField = driver.findElement(staffCodeTF);
        return codeTextField.getText();
    }

    public void setStaffID(String branchID) {
        WebElement idTextField = driver.findElement(staffIDTF);
        idTextField.sendKeys(branchID);
    }

    public String getStaffID() {
        WebElement idTextField = driver.findElement(staffIDTF);
        return idTextField.getText();
    }

    public void setStaffName(String branchName) {
        WebElement nameTextField = driver.findElement(staffNameTF);
        nameTextField.sendKeys(branchName);
    }

    public String getStaffName() {
        WebElement codeTextField = driver.findElement(staffNameTF);
        return codeTextField.getText();
    }

    public void setStaffBranch(String branch) {
        Select dropdown = new Select(driver.findElement(staffBranchDD));
        dropdown.selectByVisibleText(branch);

    }

    public String getStaffBranch() {
        Select dropdown = new Select(driver.findElement(staffBranchDD));
        dropdown.getFirstSelectedOption();
        return "";
    }


    public String getFieldLengthError() {
        WebElement errMsg = driver.findElement(minLengthError);
        return errMsg.getText();
    }


    public String getFieldPatternError() {
        WebElement errMsg = driver.findElement(allowCharsError);
        return errMsg.getText();
    }

    public BranchStaffPage clickCancel() {
        WebElement cancelButton = driver.findElement(branchCancelBtn);
        cancelButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BranchStaffPage(driver);
    }

    public BranchStaffPage clickSave() {
        WebElement searchButton = driver.findElement(branchSaveBtn);
        searchButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BranchStaffPage(driver);
    }


}
