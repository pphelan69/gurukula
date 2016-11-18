package com.servicenow.gurukula;

import com.servicenow.gurukula.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.Constant;
import utility.Select_Browser;

import java.util.concurrent.TimeUnit;

/**
 * Created by pphelan on 11/15/16.
 */
public class StaffTest {

    private WebDriver driver;
    private String baseUrl;


    @BeforeMethod
    public void setup(){
        System.out.println("Start of test....");
        Select_Browser Browser = new Select_Browser() ;
        System.out.println("Browser Selection: " + Constant.Browser);
        driver = Browser.Define_Browser(Constant.Browser);
        baseUrl = Constant.http_protocol + "://" + Constant.hostNameOrIP + ":" + Constant.hostPort + Constant.startpath;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        System.out.println("In the teardown.");

        // Remove all staff
        driver.getCurrentUrl();
        BranchStaffPage branchstaffpage = new BranchStaffPage(driver);
        BranchStaffPage branchstaffpage2 = (BranchStaffPage) branchstaffpage.selectEntitiesDropDown("Staff");
        for(String branch : branchstaffpage2.getBranchOrStaffList()) {
            System.out.println(branch);
            BranchStaffDeleteConfirmDialog deleteconfirmpage = branchstaffpage.deleteBranchOrStaff(branch);
            deleteconfirmpage.clickDelete();
        }

        // Remove all branches
        BranchStaffPage branchstaffpage3 = (BranchStaffPage) branchstaffpage.selectEntitiesDropDown("Branch");
        for(String branch : branchstaffpage3.getBranchOrStaffList()) {
            System.out.println(branch);
            BranchStaffDeleteConfirmDialog deleteconfirmpage = branchstaffpage.deleteBranchOrStaff(branch);
            deleteconfirmpage.clickDelete();
        }

        // Clear cookies and shutdown browser
        driver.manage().deleteAllCookies();
        driver.quit();
    }



    @Test(description="Testing staff creation using a staff name with 1 character to confirm we get error message.")
    public void staffNameTest1() {
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.clickLoginLink();
        loginpage.typeUsername("admin");
        loginpage.typePassword("admin");
        loginpage.checkAutomaticLogin(true);
        HomePage homepage2 = loginpage.submitValidLogin();
        BranchStaffPage branchstaffpage = (BranchStaffPage) homepage2.selectEntitiesDropDown("Branch");
        BranchStaffCreateEditDialog branchstaffdialog = branchstaffpage.clickCreateBranchBtn();
        branchstaffdialog.setBranchName("Cupertino");
        branchstaffdialog.setBranchCode("1001");
        BranchStaffPage branchstaffpage2 = branchstaffdialog.clickSave();
        BranchStaffPage branchstaffpage3 = (BranchStaffPage) branchstaffpage2.selectEntitiesDropDown("Staff");
        BranchStaffCreateEditDialog branchstaffdialog4 = branchstaffpage3.clickCreateStaffBtn();
        branchstaffdialog4.setStaffName("%");
        branchstaffdialog4.setStaffBranch("Cupertino");
        String actual = branchstaffdialog.getFieldPatternError();
        String expected = "This field should follow pattern ^[a-zA-Z\\s]*$.";
        branchstaffdialog.clickCancel();
        Assert.assertEquals(actual,expected, "Error: Staff name, allowed characters error not displayed.");
    }



    @Test(description="Testing staff creation.")
    public void createStaffTest1() {
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.clickLoginLink();
        loginpage.typeUsername("admin");
        loginpage.typePassword("admin");
        loginpage.checkAutomaticLogin(true);
        HomePage homepage2 = loginpage.submitValidLogin();
        BranchStaffPage branchstaffpage = (BranchStaffPage) homepage2.selectEntitiesDropDown("Branch");
        BranchStaffCreateEditDialog branchstaffdialog = branchstaffpage.clickCreateBranchBtn();
        branchstaffdialog.setBranchName("San Jose");
        branchstaffdialog.setBranchCode("909");
        BranchStaffPage branchstaffpage2 = branchstaffdialog.clickSave();
        BranchStaffPage branchstaffpage3 = (BranchStaffPage) branchstaffpage2.selectEntitiesDropDown("Staff");
        BranchStaffCreateEditDialog branchstaffdialog4 = branchstaffpage3.clickCreateStaffBtn();
        branchstaffdialog4.setStaffName("Peter Phelan");
        branchstaffdialog4.setStaffBranch("San Jose");
        BranchStaffPage branchstaffpage4 = branchstaffdialog4.clickSave();
        Assert.assertEquals(branchstaffpage4.branchOrStaffExists("Peter Phelan"), true, "Error: Branch not created.");
    }

    @Test(description="Testing staff creation and deletion.")
    public void deleteStaffTest1() {
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.clickLoginLink();
        loginpage.typeUsername("admin");
        loginpage.typePassword("admin");
        loginpage.checkAutomaticLogin(true);
        HomePage homepage2 = loginpage.submitValidLogin();
        BranchStaffPage branchstaffpage = (BranchStaffPage) homepage2.selectEntitiesDropDown("Branch");
        BranchStaffCreateEditDialog branchstaffdialog = branchstaffpage.clickCreateBranchBtn();
        branchstaffdialog.setBranchName("San Jose");
        branchstaffdialog.setBranchCode("909");
        BranchStaffPage branchstaffpage2 = branchstaffdialog.clickSave();
        BranchStaffPage branchstaffpage3 = (BranchStaffPage) branchstaffpage2.selectEntitiesDropDown("Staff");
        BranchStaffCreateEditDialog branchstaffdialog4 = branchstaffpage3.clickCreateStaffBtn();
        branchstaffdialog4.setStaffName("Peter Phelan");
        branchstaffdialog4.setStaffBranch("San Jose");
        BranchStaffPage branchstaffpage4 = branchstaffdialog4.clickSave();

        BranchStaffDeleteConfirmDialog deleteconfirmpage = branchstaffpage4.deleteBranchOrStaff("Peter Phelan");
        BranchStaffPage branchstaffpage5 = deleteconfirmpage.clickDelete();
        Assert.assertEquals(branchstaffpage5.branchOrStaffExists("Peter Phelan"),false, "Error: Staff exists after deletion.");

    }


    @Test(description="Testing staff creation and view.")
    public void viewStaffTest1() {
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.clickLoginLink();
        loginpage.typeUsername("admin");
        loginpage.typePassword("admin");
        loginpage.checkAutomaticLogin(true);
        HomePage homepage2 = loginpage.submitValidLogin();
        BranchStaffPage branchstaffpage = (BranchStaffPage) homepage2.selectEntitiesDropDown("Branch");
        BranchStaffCreateEditDialog branchstaffdialog = branchstaffpage.clickCreateBranchBtn();
        branchstaffdialog.setBranchName("San Jose");
        branchstaffdialog.setBranchCode("909");
        BranchStaffPage branchstaffpage2 = branchstaffdialog.clickSave();
        BranchStaffPage branchstaffpage3 = (BranchStaffPage) branchstaffpage2.selectEntitiesDropDown("Staff");
        BranchStaffCreateEditDialog branchstaffdialog4 = branchstaffpage3.clickCreateStaffBtn();
        branchstaffdialog4.setStaffName("Peter Phelan");
        branchstaffdialog4.setStaffBranch("San Jose");
        BranchStaffPage branchstaffpage4 = branchstaffdialog4.clickSave();

        BranchStaffViewPage branchstaffviewpage = branchstaffpage4.viewBranchOrStaff("Peter Phelan");
        String actual = branchstaffviewpage.getName();
        String expected = "Peter Phelan";
        branchstaffviewpage.clickBack();
        Assert.assertEquals(actual,expected, "Error: Staff name incorrect.");


    }


    @Test(description="Testing staff search.")
    public void searchStaffTest1() {
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.clickLoginLink();
        loginpage.typeUsername("admin");
        loginpage.typePassword("admin");
        loginpage.checkAutomaticLogin(true);
        HomePage homepage2 = loginpage.submitValidLogin();
        BranchStaffPage branchstaffpage = (BranchStaffPage) homepage2.selectEntitiesDropDown("Branch");
        BranchStaffCreateEditDialog branchstaffdialog = branchstaffpage.clickCreateBranchBtn();
        branchstaffdialog.setBranchName("San Jose");
        branchstaffdialog.setBranchCode("909");
        BranchStaffPage branchstaffpage2 = branchstaffdialog.clickSave();
        BranchStaffPage branchstaffpage3 = (BranchStaffPage) branchstaffpage2.selectEntitiesDropDown("Staff");
        BranchStaffCreateEditDialog branchstaffdialog4 = branchstaffpage3.clickCreateStaffBtn();
        branchstaffdialog4.setStaffName("Peter Phelan");
        branchstaffdialog4.setStaffBranch("San Jose");
        BranchStaffPage branchstaffpage4 = branchstaffdialog4.clickSave();

        BranchStaffPage branchstaffpage5 = branchstaffpage4.searchBranch("Peter");
        BranchStaffPage branchstaffpage6 = branchstaffpage5.clickSearchBtn();
        boolean actual = branchstaffpage6.branchOrStaffExists("Peter");
        Assert.assertEquals(actual,true,"Search: Did not find staff.");

    }






}
