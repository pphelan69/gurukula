package com.servicenow.gurukula.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pphelan on 11/14/16.
 */
public class GurukulaPage {

    public final WebDriver driver;


    // Home Tab
    By tab_Home    = By.cssSelector("span[translate='global.menu.home']");

    // Entities Dropdown only exists after login
    By dropdown_Entities = By.cssSelector("span[translate='global.menu.entities.main']");

    // Entities Dropdown menuitems after login
    By menuitem_Branch   = By.cssSelector("span[translate='global.menu.entities.branch']");
    By menuitem_Staff    = By.cssSelector("span[translate='global.menu.entities.staff']");

    // Account Dropdown
    By dropdown_Account      = By.cssSelector("span[translate='global.menu.account.main']");

    // Account Dropdown menuitems before login
    By menuitem_Authenticate = By.cssSelector("span[translate='global.menu.account.login']");
    By menuitem_Register     = By.cssSelector("span[translate='global.menu.account.register']");

    // Account Dropdown menuitems after login
    By menuitem_Settings = By.cssSelector("span[translate='global.menu.account.settings']");
    By menuitem_Password = By.cssSelector("span[translate='global.menu.account.password']");
    By menuitem_Sessions = By.cssSelector("span[translate='global.menu.account.sessions']");
    By menuitem_Logout   = By.cssSelector("span[translate='global.menu.account.logout']");




    public GurukulaPage(WebDriver driver) {
        this.driver = driver;

    }

    public GurukulaPage selectAccountDropDownItem(String menuItem) {
        driver.findElement(dropdown_Account).click();

        switch (menuItem) {
            case "Authenticate":
                driver.findElement(menuitem_Authenticate).click();
                return new LoginPage(driver);
            case "Register":
                driver.findElement(menuitem_Register).click();
                return new RegistrationPage(driver);
            case "Settings":
                driver.findElement(menuitem_Settings).click();
                return new SettingsPage(driver);
            case "Password":
                driver.findElement(menuitem_Password).click();
                return new PasswordPage(driver);
            case "Sessions":
                driver.findElement(menuitem_Sessions).click();
                return new SessionsPage(driver);
            case "Log out":
                driver.findElement(menuitem_Logout).click();
                return new LoginPage(driver);
            default:
                System.out.println("Invalid Acount dropdown menu item: " + menuItem);
        }

        return this;

    }

    public GurukulaPage selectEntitiesDropDown(String menuItem) {
        driver.findElement(dropdown_Entities).click();

        switch (menuItem) {
            case "Branch":
                driver.findElement(menuitem_Branch).click();
                return new BranchStaffPage(driver);
            case "Staff":
                driver.findElement(menuitem_Staff).click();
                return new BranchStaffPage(driver);
            default:
                System.out.println("Invalid Entities dropdown menu item: " + menuItem);

        }

        return this;

    }

    public GurukulaPage clickHomeTab(String username) {
        driver.findElement(tab_Home).click();
        return this;
    }





}
