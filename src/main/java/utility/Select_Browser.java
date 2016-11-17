package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by p.phelan on 11/14/16.
 */


public class Select_Browser {

    public WebDriver driver;

    public  WebDriver Define_Browser(Object Browser) {

        if (Browser.equals("Chrome"))
        {
            File file = new File("src/main/resources/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            driver = new ChromeDriver();
        }
        else if(Browser.equals("FireFox")) {
            if (System.getProperty("os.name").contains("Windows")) {
                File file = new File("src/main/resources/drivers/geckodriver.exe");
                System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
            }
            else{
                File file = new File("src/main/resources/drivers/geckodriver");
                System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
            }

            driver =new FirefoxDriver();
        }


        return driver;
    }

    public void implicitly_Wait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void browser_Maximize() {
        driver.manage().window().maximize();
    }
}


