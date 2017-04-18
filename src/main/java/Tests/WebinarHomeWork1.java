package Tests;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.BaseScript;

import java.util.List;

/**
 * Created by Alenka on 04.04.2017.
 */
public class WebinarHomeWork1 extends BaseScript {
    private static WebDriver driver;
    private static String login = "webinar.test@gmail.com";
    private static String password = "Xcg7299bnSmMuRLp9ITw";
    private static String BASE_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
    private static By loginFormLocator = By.xpath("//form[@id='login_form']");
    private static By loginFieldLocator = By.xpath("//input[@id='email']");
    private static By passwordFieldLocator = By.xpath("//input[@id='passwd']");
    private static By loginButtonLocator = By.xpath("//button[contains(@class, 'ladda-button')]");
    private static By siteLogoLocator = By.xpath("//a[@id='header_logo']");
    private static By menuItemsLocator = By.xpath("//nav[@id='nav-sidebar']//li//span");
    private static By pageTitleLocator = By.xpath("//ul[@class='breadcrumb page-breadcrumb']");
    private static By pageTitleAlternativeLocator = By.xpath("//ol[@class='breadcrumb']/li[1]");

    public static void main(String[] args) throws InterruptedException  {
        test1();
        test2();
    }

    public static void test1() throws InterruptedException {
       //System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
        // driver = new FirefoxDriver();
        //open the browser
        driver = setupDriver();
        driver.get(BASE_URL);

        //enter the login in "Email" field
        Thread.sleep (3000);
        driver.findElement(loginFieldLocator).sendKeys(login);

        //enter the password in "Email" field
        driver.findElement(passwordFieldLocator).sendKeys(password);

        // click the "Login" button
        driver.findElement(loginButtonLocator).click();

        Thread.sleep (5000);

        //check that user log in
        WebElement logo = driver.findElement(By.xpath("//a[@id='header_logo']"));
        Assert.assertTrue("element not visible", logo.isDisplayed());

        // log out from site
        driver.findElement(By.xpath("//span[@class='employee_avatar_small']")).click();
        //profileLocator.click();
        driver.findElement(By.xpath("//a[@id='header_logout']")).click();
        //logoutLocator.click();

        //check that user is log out from the site
        Thread.sleep (3000);
        Assert.assertTrue("user doesn't log out", driver.findElement(loginFormLocator).isDisplayed());
        // close the browser
        driver.quit();
    }

    public static void test2() throws InterruptedException {
        driver = setupDriver();
        driver.get(BASE_URL);

        //enter the login in "Email" field
        Thread.sleep (3000);
        driver.findElement(loginFieldLocator).sendKeys(login);

        //enter the password in "Email" field
        driver.findElement(passwordFieldLocator).sendKeys(password);

        // click the "Login" button
        driver.findElement(loginButtonLocator).click();

        try {
            Thread.sleep (3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //check that user log in
        Assert.assertTrue("element not visible", driver.findElement(siteLogoLocator).isDisplayed());

        //click each item of left menu:
        List<WebElement> tabsList = driver.findElements(menuItemsLocator);
        int countTabs = tabsList.size();

        //definite new elements
        WebElement menuItem;
        String pageTitleText;
        for (int i = 0; i < countTabs; i++) {
            //click the element of menu
            if (i == 1 || i == 7 || i == 13) {
                System.out.println("Skipping menu title...");
            } else {
                try{
                    menuItem = driver.findElement(By.xpath("//nav[@id='nav-sidebar']//li[" + (i + 1) + "]//span"));
                } catch (Exception e) {
                    menuItem = driver.findElement(By.xpath("//nav//li[" + i + "]//span"));
                }
                menuItem.click();
                Thread.sleep (3000);

                //extract text of element
                pageTitleText = getPageTitle().getText();
                System.out.println("Page title:" + pageTitleText);

                //refresh the page
                driver.navigate().refresh();
                Thread.sleep (3000);

                //Check page title after page refresh
                Assert.assertEquals("Page title has not expected value", getPageTitle().getText(), pageTitleText);
            }
        }
        driver.quit();
    }

    private static WebElement getPageTitle() {
        WebElement pageTitle;
        try{
            pageTitle = driver.findElement(pageTitleLocator);
        }catch (Exception e){
            System.out.println("e.getCause(): " + e.getCause());
            pageTitle = driver.findElement(pageTitleAlternativeLocator);
        }
        return pageTitle;
    }
}
