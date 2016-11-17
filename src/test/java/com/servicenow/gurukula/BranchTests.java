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
        driver.manage().deleteAllCookies();
        driver.quit();
    }


    @Test(description="Testing branch creation using a branch name with 1 character to confirm I get error message.")
    public void branchNameTest1() {
        driver.get(baseUrl);
        HomePage startPage = new HomePage(driver);
        LoginPage mypage = startPage.clickLoginLink();
        mypage.typeUsername("admin");
        mypage.typePassword("admin");
        mypage.checkAutomaticLogin(true);
        HomePage x = mypage.submitValidLogin();
        BranchPage branchPage1 = (BranchPage) x.selectEntitiesDropDown("Branch");
        BranchCreateEditDialog branchDialog1 = branchPage1.clickCreateBranchBtn();
        branchDialog1.setBranchName("S");
        branchDialog1.setBranchCode("1001");
        branchDialog1.getFieldLengthError();

        String expected = "This field is required to be at least 5 characters.";
        String actual = branchDialog1.getFieldLengthError();

        Assert.assertEquals(actual,expected, "Error: Branch name length error not displayed.");
    }

    @Test(description="Testing branch creation using a branch name with invalid character to confirm I get error message.")
    public void branchNameTest2() {
        driver.get(baseUrl);
        HomePage startPage = new HomePage(driver);
        LoginPage mypage = startPage.clickLoginLink();
        mypage.typeUsername("admin");
        mypage.typePassword("admin");
        mypage.checkAutomaticLogin(true);
        HomePage x = mypage.submitValidLogin();
        BranchPage branchPage1 = (BranchPage) x.selectEntitiesDropDown("Branch");
        BranchCreateEditDialog branchDialog1 = branchPage1.clickCreateBranchBtn();
        branchDialog1.setBranchName("%");
        branchDialog1.setBranchCode("1001");
        branchDialog1.getFieldLengthError();

        String expected = "This field should follow pattern ^[a-zA-Z\\s]*$";
        String actual = branchDialog1.getFieldPatternError();

        Assert.assertEquals(actual,expected, "Error: Branch name, allowed characters error not displayed.");
    }


    @Test(description="Testing branch creation using a branch code with 1 character to confirm I get error message.")
    public void branchCodeTest1() {
        driver.get(baseUrl);
        HomePage startPage = new HomePage(driver);
        LoginPage mypage = startPage.clickLoginLink();
        mypage.typeUsername("admin");
        mypage.typePassword("admin");
        mypage.checkAutomaticLogin(true);
        HomePage x = mypage.submitValidLogin();
        BranchPage branchPage1 = (BranchPage) x.selectEntitiesDropDown("Branch");
        BranchCreateEditDialog branchDialog1 = branchPage1.clickCreateBranchBtn();
        branchDialog1.setBranchName("San Jose");
        branchDialog1.setBranchCode("1");
        branchDialog1.getFieldLengthError();

        String expected = "This field is required to be at least 2 characters.";
        String actual = branchDialog1.getFieldLengthError();

        Assert.assertEquals(actual,expected, "Error: Branch code length error not displayed.");
    }

    @Test(description="Testing branch creation using a branch name with invalid character to confirm I get error message.")
    public void branchCodeTest2() {
        driver.get(baseUrl);
        HomePage startPage = new HomePage(driver);
        LoginPage mypage = startPage.clickLoginLink();
        mypage.typeUsername("admin");
        mypage.typePassword("admin");
        mypage.checkAutomaticLogin(true);
        HomePage x = mypage.submitValidLogin();
        BranchPage branchPage1 = (BranchPage) x.selectEntitiesDropDown("Branch");
        BranchCreateEditDialog branchDialog1 = branchPage1.clickCreateBranchBtn();
        branchDialog1.setBranchName("%");
        branchDialog1.setBranchCode("1001");
        branchDialog1.getFieldLengthError();

        String expected = "This field should follow pattern ^[a-zA-Z\\s]*$";
        String actual = branchDialog1.getFieldPatternError();

        Assert.assertEquals(actual,expected, "Error: Branch name, allowed characters error not displayed.");
    }


    @Test(description="Testing branch creation.")
    public void createBranchTest2() {
        driver.get(baseUrl);
        HomePage startPage = new HomePage(driver);
        LoginPage mypage = startPage.clickLoginLink();
        mypage.typeUsername("admin");
        mypage.typePassword("admin");
        mypage.checkAutomaticLogin(true);
        HomePage x = mypage.submitValidLogin();
        BranchPage branchPage1 = (BranchPage) x.selectEntitiesDropDown("Branch");
        BranchCreateEditDialog branchDialog1 = branchPage1.clickCreateBranchBtn();
        branchDialog1.setBranchName("Santa Clara");
        branchDialog1.setBranchCode("1002");
        BranchPage branchPage2 = branchDialog1.clickSave();
        Assert.assertEquals(branchPage2.branchExists("Santa Clara"), true, "Error: Branch not created.");
    }

    @Test(description="Testing branch deletion.")
    public void deleteBranchTest() {
        driver.get(baseUrl);
        HomePage startPage = new HomePage(driver);
        LoginPage mypage = startPage.clickLoginLink();
        mypage.typeUsername("admin");
        mypage.typePassword("admin");
        mypage.checkAutomaticLogin(true);
        HomePage x = mypage.submitValidLogin();
        BranchPage branchPage1 = (BranchPage) x.selectEntitiesDropDown("Branch");
        BranchCreateEditDialog branchDialog = branchPage1.clickCreateBranchBtn();
        branchDialog.setBranchName("San Jose");
        branchDialog.setBranchCode("1001");
        BranchPage branchPage2 = branchDialog.clickSave();
        Assert.assertEquals(branchPage2.branchExists("San Jose"), true, "Error: Branch not created.");

    }


    @Test(description="Testing branch deletion.")
    public void sampleTest() {
        driver.get(baseUrl);
        HomePage startPage = new HomePage(driver);
        LoginPage mypage = startPage.clickLoginLink();
        mypage.typeUsername("admin");
        mypage.typePassword("admin");
        mypage.checkAutomaticLogin(true);
        HomePage x = mypage.submitValidLogin();


        // BranchViewPage xx = y.viewBranch("Cupertino");
        // System.out.println(xx.getBranchName());
        // System.out.println(xx.getBranchCode());
        // xx.clickBack();

        // BranchDeleteConfirmationDialog yy = y.deleteBranch("Santa Clara");
        // System.out.println(yy.getBranchDeleteConfirmMsg());
        // yy.clickDelete();
        // BranchCreateEditDialog y2 = y.clickCreateBranchButton();
        //y2.setBranchName("Santa Clara");
        // y2.setBranchCode("7021");
        // y2.clickSave();
        // y.searchBranch("Cupertino");
        // y.clickSearchButton();
        // y.viewBranch("Cupertino");
        // if (x2.branchExists("Cupertino")){
        //      x2.viewBranch("Palo Alto");
        // }
        // Assert.assertTrue(mypage.loginBad(),"Fail: Login was suppose to fail and it did not.");

    }



}
