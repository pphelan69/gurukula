package com.servicenow.gurukula.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Created by pphelan on 11/14/16.
 */
public class BranchPage extends GurukulaPage {


    // Home Tab
    By branchTable           = By.cssSelector("div[class='table-responsive'] table tbody");
    By branchTableViewRows   = By.cssSelector("div[class='table-responsive'] table tbody tr td");
    By viewButton            = By.xpath(".//button[1]");
    By editButton            = By.xpath(".//button[2]");
    By deleteButton          = By.xpath(".//button[3]");



    // class="table table-striped"


    public BranchPage(WebDriver driver) {

        super(driver);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public BranchViewPage viewBranch(String branch) {


        WebElement table_element = driver.findElement(branchTable);

        List<WebElement> tr_collection=table_element.findElements(branchTableViewRows);

        int count = 0;
        for(WebElement trElement : tr_collection)
        {

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

    public BranchPage deleteBranch(String branch) {


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


        return new BranchPage(driver);





    }

}
