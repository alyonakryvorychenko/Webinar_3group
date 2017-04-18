package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

/**
 * Created by Alenka on 16.04.2017.
 */
public class BaseScript {
    private static WebDriver driver;

    public static WebDriver setupDriver() {
        String resourcesPath = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources" + File.separator;
        System.setProperty("webdriver.chrome.driver", resourcesPath + "chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver", resourcesPath + "geckodriver.exe");
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        //open the browser
        driver.manage().window().maximize();
        return driver;

    }

    public static void log(String message) {
        System.out.println(message);
    }
}
