package com.servicenow.gurukula;

import com.servicenow.gurukula.pages.BranchPage;
import com.servicenow.gurukula.pages.GurukulaPage;
import com.servicenow.gurukula.pages.HomePage;
import com.servicenow.gurukula.pages.LoginPage;
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


    @Test(description="Testing branch creation.")
    public void branchCreationTests() {
        driver.get(baseUrl);
        HomePage startPage = new HomePage(driver);
        LoginPage mypage = startPage.clickLoginLink();
        mypage.typeUsername("admin");
        mypage.typePassword("admin");
        mypage.checkAutomaticLogin(true);
        HomePage x = mypage.submitValidLogin();
        BranchPage y = (BranchPage)x.selectEntitiesDropDown("Branch");

        // Assert.assertTrue(mypage.loginBad(),"Fail: Login was suppose to fail and it did not.");
    }
}
