package com.servicenow.gurukula.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by pphelan on 11/14/16.
 */
public class BranchStaffPage extends GurukulaPage {


    By createBranchBtn  = By.cssSelector("span[translate='gurukulaApp.branch.home.createLabel']");
    By createStaffBtn   = By.cssSelector("span[translate='gurukulaApp.staff.home.createLabel']");
    By searchTextField  = By.id("searchQuery");
    By searchButton     = By.cssSelector("button[ng-click='search()']");
    By itemTableRows    = By.cssSelector("div[class='table-responsive'] table tbody tr td");


    public BranchStaffPage(WebDriver driver) {
        super(driver);
    }



    public BranchStaffCreateEditDialog clickCreateBranchBtn(){
        WebElement searchButton = driver.findElement(createBranchBtn);
        searchButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new BranchStaffCreateEditDialog(driver);

    }

    public BranchStaffCreateEditDialog clickCreateStaffBtn(){
        WebElement searchButton = driver.findElement(createStaffBtn);
        searchButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new BranchStaffCreateEditDialog(driver);

    }



    public BranchStaffPage searchBranch(String searchText) {
        WebElement searchTF = driver.findElement(searchTextField);
        searchTF.sendKeys(searchText);
        return this;
    }

    public BranchStaffPage clickSearchBtn() {
        WebElement searchBtn = driver.findElement(searchButton);
        searchBtn.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BranchStaffPage(driver);
    }


    public List<String> getBranchOrStaffList() {

        List<String> branchList = new ArrayList();

        List<WebElement> tr_collection=driver.findElements(itemTableRows);
        int count = 1;
        for(WebElement trElement : tr_collection) {
            if (count == 2) {
                branchList.add(trElement.getText());
            }
            else if ((count-2) % 4 == 0){
                branchList.add(trElement.getText());
            }
            count = count + 1;
        }


        return branchList;
    }


    public boolean branchOrStaffExists(String branch) {

        List<WebElement> tr_collection=driver.findElements(itemTableRows);
        for(WebElement trElement : tr_collection) {
            if (trElement.getText().contains(branch)){
                return true;
            }
        }
        return false;
    }

    public BranchStaffViewPage viewBranchOrStaff(String branch) {

        List<WebElement> tr_collection=driver.findElements(itemTableRows);

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

        return new BranchStaffViewPage(driver);


    }

    public BranchStaffCreateEditDialog editBranch(String branch) {


        List<WebElement> tr_collection=driver.findElements(itemTableRows);

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


        return new BranchStaffCreateEditDialog(driver);





    }

    public BranchStaffDeleteConfirmDialog deleteBranchOrStaff(String branchorstaff) {

        List<WebElement> tr_collection=driver.findElements(itemTableRows);

        int count = 0;
        for(WebElement trElement : tr_collection)
        {

            if (branchorstaff.equals(trElement.getText())){
                System.out.println("Branch Exists: " + branchorstaff);
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


        return new BranchStaffDeleteConfirmDialog(driver);





    }


}
