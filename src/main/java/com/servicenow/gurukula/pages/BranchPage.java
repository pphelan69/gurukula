package com.servicenow.gurukula.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Created by pphelan on 11/14/16.
 */
public class BranchPage extends GurukulaPage {


    By createBranchBtn        = By.cssSelector("span[translate='gurukulaApp.branch.home.createLabel']");
    By searchBranchTextField  = By.id("searchQuery");
    By searchBranchBtn        = By.cssSelector("button[ng-click='search()']");
    By branchTable            = By.cssSelector("div[class='table-responsive'] table tbody");
    By branchTableViewRows    = By.cssSelector("div[class='table-responsive'] table tbody tr td");


    public BranchPage(WebDriver driver) {
        super(driver);
    }


    public BranchCreateEditDialog clickCreateBranchBtn(){
        WebElement searchButton = driver.findElement(createBranchBtn);
        searchButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new BranchCreateEditDialog(driver);

    }

    public BranchPage searchBranch(String searchText) {
        WebElement searchTextField = driver.findElement(searchBranchTextField);
        searchTextField.sendKeys(searchText);
        return this;
    }

    public BranchPage clickSearchBtn() {
        WebElement searchButton = driver.findElement(searchBranchBtn);
        searchButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BranchPage(driver);
    }


    public boolean branchExists(String branch) {
        WebElement table_element = driver.findElement(branchTable);

        List<WebElement> tr_collection=table_element.findElements(branchTableViewRows);
        for(WebElement trElement : tr_collection) {
            if (branch.equals(trElement.getText())){
                return true;
            }
        }
        return false;
    }

    public BranchViewPage viewBranch(String branch) {

        WebElement table_element = driver.findElement(branchTable);

        List<WebElement> tr_collection=table_element.findElements(branchTableViewRows);

        int count = 0;
        for(WebElement trElement : tr_collection) {
            if (branch.equals(trElement.getText())){
                System.out.println("Branch Exists: " + branch);
                System.out.println("***" + tr_collection.get(count+2).getText() + "*****");
                List<WebElement> td_collection2=tr_collection.get(count+2).findElements(By.xpath("button"));

                td_collection2.get(0).click();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            count = count + 1;
        }

        return new BranchViewPage(driver);


    }

    public BranchCreateEditDialog editBranch(String branch) {


        WebElement table_element = driver.findElement(branchTable);

        List<WebElement> tr_collection=table_element.findElements(branchTableViewRows);

        int count = 0;
        for(WebElement trElement : tr_collection)
        {

            if (branch.equals(trElement.getText())){
                System.out.println("Branch Exists: " + branch);
                System.out.println("***" + tr_collection.get(count+2).getText() + "*****");
                List<WebElement> td_collection2=tr_collection.get(count+2).findElements(By.xpath("button"));


                td_collection2.get(1).click();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;


            }
            count = count + 1;

        }


        return new BranchCreateEditDialog(driver);





    }

    public BranchDeleteConfirmationDialog deleteBranch(String branch) {


        WebElement table_element = driver.findElement(branchTable);

        List<WebElement> tr_collection=table_element.findElements(branchTableViewRows);

        int count = 0;
        for(WebElement trElement : tr_collection)
        {

            if (branch.equals(trElement.getText())){
                System.out.println("Branch Exists: " + branch);
                System.out.println("***" + tr_collection.get(count+2).getText() + "*****");
                List<WebElement> td_collection2=tr_collection.get(count+2).findElements(By.xpath("button"));


                td_collection2.get(2).click();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;


            }
            count = count + 1;

        }


        return new BranchDeleteConfirmationDialog(driver);





    }


}
