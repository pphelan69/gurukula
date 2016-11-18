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
public class BranchTests {

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

        // Remove all branches
        driver.getCurrentUrl();
        BranchStaffPage branchstaffpage = new BranchStaffPage(driver);
        for(String branch : branchstaffpage.getBranchOrStaffList()) {
            System.out.println(branch);
            BranchStaffDeleteConfirmDialog deleteconfirmpage = branchstaffpage.deleteBranchOrStaff(branch);
            deleteconfirmpage.clickDelete();
        }

        // Clear cookies and shutdown browser
        driver.manage().deleteAllCookies();
        driver.quit();
    }



    @Test(description="Testing branch creation using a branch name with 1 character to confirm we get error message.")
    public void branchNameTest1() {
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.clickLoginLink();
        loginpage.typeUsername("admin");
        loginpage.typePassword("admin");
        loginpage.checkAutomaticLogin(true);
        HomePage homepage2 = loginpage.submitValidLogin();
        BranchStaffPage branchstaffpage = (BranchStaffPage) homepage2.selectEntitiesDropDown("Branch");
        BranchStaffCreateEditDialog branchstaffdialog = branchstaffpage.clickCreateBranchBtn();
        branchstaffdialog.setBranchName("S");
        branchstaffdialog.setBranchCode("1001");

        String actual = branchstaffdialog.getFieldLengthError();
        String expected = "This field is required to be at least 5 characters.";
        branchstaffdialog.clickCancel();
        Assert.assertEquals(actual,expected, "Error: Branch name length error not displayed.");
    }

    @Test(description="Testing branch creation using a branch name with invalid character to confirm we get error message.")
    public void branchNameTest2() {
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.clickLoginLink();
        loginpage.typeUsername("admin");
        loginpage.typePassword("admin");
        loginpage.checkAutomaticLogin(true);
        HomePage homepage2 = loginpage.submitValidLogin();
        BranchStaffPage branchstaffpage = (BranchStaffPage) homepage2.selectEntitiesDropDown("Branch");
        BranchStaffCreateEditDialog branchstaffdialog = branchstaffpage.clickCreateBranchBtn();
        branchstaffdialog.setBranchName("%");
        branchstaffdialog.setBranchCode("1001");

        String actual = branchstaffdialog.getFieldPatternError();
        String expected = "This field should follow pattern ^[a-zA-Z\\s]*$.";

        Assert.assertEquals(actual,expected, "Error: Branch name, allowed characters error not displayed.");
    }




    @Test(description="Testing branch creation.")
    public void createBranchTest1() {
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.clickLoginLink();
        loginpage.typeUsername("admin");
        loginpage.typePassword("admin");
        loginpage.checkAutomaticLogin(true);
        HomePage homepage2 = loginpage.submitValidLogin();
        BranchStaffPage branchstaffpage = (BranchStaffPage) homepage2.selectEntitiesDropDown("Branch");
        BranchStaffCreateEditDialog branchstaffdialog = branchstaffpage.clickCreateBranchBtn();
        branchstaffdialog.setBranchName("Santa Clara");
        branchstaffdialog.setBranchCode("1000");
        BranchStaffPage branchstaffpage2 = branchstaffdialog.clickSave();
        Assert.assertEquals(branchstaffpage2.branchOrStaffExists("Santa Clara"), true, "Error: Branch not created.");
    }

    @Test(description="Testing branch creation and deletion.")
    public void deleteBranchTest1() {
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.clickLoginLink();
        loginpage.typeUsername("admin");
        loginpage.typePassword("admin");
        loginpage.checkAutomaticLogin(true);
        HomePage homepage2 = loginpage.submitValidLogin();
        BranchStaffPage branchstaffpage = (BranchStaffPage) homepage2.selectEntitiesDropDown("Branch");
        BranchStaffCreateEditDialog branchstaffdialog = branchstaffpage.clickCreateBranchBtn();
        branchstaffdialog.setBranchName("Santa Clara");
        branchstaffdialog.setBranchCode("1001");
        BranchStaffPage branchstaffpage2 = branchstaffdialog.clickSave();
        BranchStaffDeleteConfirmDialog deleteconfirmpage = branchstaffpage2.deleteBranchOrStaff("Santa Clara");
        BranchStaffPage branchstaffpage3 = deleteconfirmpage.clickDelete();
        Assert.assertEquals(branchstaffpage3.branchOrStaffExists("San Jose"),false, "Error: Branch exists after deletion.");

    }


    @Test(description="Testing branch creation and view.")
    public void viewBranchTest1() {
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.clickLoginLink();
        loginpage.typeUsername("admin");
        loginpage.typePassword("admin");
        loginpage.checkAutomaticLogin(true);
        HomePage homepage2 = loginpage.submitValidLogin();
        BranchStaffPage branchstaffpage = (BranchStaffPage) homepage2.selectEntitiesDropDown("Branch");
        BranchStaffCreateEditDialog branchstaffdialog = branchstaffpage.clickCreateBranchBtn();
        branchstaffdialog.setBranchName("Santa Cruz");
        branchstaffdialog.setBranchCode("1002");
        BranchStaffPage branchstaffpage2 = branchstaffdialog.clickSave();
        BranchStaffViewPage branchstaffviewpage = branchstaffpage2.viewBranchOrStaff("Santa Cruz");
        String actual = branchstaffviewpage.getName();
        String expected = "Santa Cruz";
        branchstaffviewpage.clickBack();
        Assert.assertEquals(actual,expected, "Error: Branch name incorrect.");


    }


    @Test(description="Testing branch search.")
    public void searchBranchTest1() {
        driver.get(baseUrl);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.clickLoginLink();
        loginpage.typeUsername("admin");
        loginpage.typePassword("admin");
        loginpage.checkAutomaticLogin(true);
        HomePage homepage2 = loginpage.submitValidLogin();
        BranchStaffPage branchstaffpage = (BranchStaffPage)homepage2.selectEntitiesDropDown("Branch");
        BranchStaffCreateEditDialog branchstaffdialog = branchstaffpage.clickCreateBranchBtn();
        branchstaffdialog.setBranchName("Santa Cruz");
        branchstaffdialog.setBranchCode("1002");
        BranchStaffPage branchstaffpage2 = branchstaffdialog.clickSave();
        BranchStaffPage branchstaffpage3 = branchstaffpage2.searchBranch("Santa");
        BranchStaffPage branchstaffpage4 = branchstaffpage3.clickSearchBtn();
        boolean actual = branchstaffpage4.branchOrStaffExists("Santa");
        Assert.assertEquals(actual,true,"Search: Did not find branch.");

    }



}
