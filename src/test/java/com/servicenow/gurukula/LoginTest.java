package com.servicenow.gurukula;

import com.servicenow.gurukula.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utility.Constant;
import utility.Select_Browser;
import java.util.concurrent.TimeUnit;

/**
 * Created by pphelan on 11/14/16.
 */

public class LoginTest {


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


    @DataProvider(name="Bad User Names")
    public Object[][] getBadUserNames() {
        Object x[][] = new Object[][] {{""},{"bogus"},{"$$$%%"}};
        return x;
    }

    @DataProvider(name="Bad Passwords")
    public Object[][] getBadPasswords() {
        Object x[][] = new Object[][] {{""},{"bgfhd"},{"gfg%%"}};
        return x;
    }



    @Test(dataProvider="Bad User Names",description="Testing various bad user names with valid password for admin user.")
    public void inValidLoginUserNameTest(String userName) {
        driver.get(baseUrl);
        HomePage startPage = new HomePage(driver);
        LoginPage mypage = startPage.clickLoginLink();
        mypage.typeUsername(userName);
        mypage.typePassword("admin");
        mypage.checkAutomaticLogin(true);
        mypage.submitInvalidLogin();
        Assert.assertTrue(mypage.loginBad(),"Fail: Login was suppose to fail and it did not.");
    }

    @Test(dataProvider="Bad Passwords",description="Testing various bad passwords with valid user name admin.")
    public void inValidLoginPasswordTest(String password) {
        driver.get(baseUrl);
        HomePage startPage = new HomePage(driver);
        LoginPage mypage = startPage.clickLoginLink();
        mypage.typeUsername("admin");
        mypage.typePassword(password);
        mypage.checkAutomaticLogin(true);
        mypage.submitInvalidLogin();
        Assert.assertTrue(mypage.loginBad(),"Fail: Login was suppose to fail and it did not.");
    }


    @Test
    public void validLoginTest() {
        driver.get(baseUrl);
        HomePage startPage = new HomePage(driver);
        LoginPage mypage = startPage.clickLoginLink();
        mypage.typeUsername("admin");
        mypage.typePassword("admin");
        mypage.checkAutomaticLogin(false);
        HomePage test = mypage.submitValidLogin();

        String actualString   = test.userLoggedin();
        String expectedString = "You are logged in as user \"admin\".";

        Assert.assertEquals(actualString,expectedString,"Fail: Not logged in as user admin.");
    }






}
