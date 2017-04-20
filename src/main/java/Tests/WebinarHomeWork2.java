package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.BaseScript;
import utils.GeneralActions;

/**
 * Created by Alenka on 16.04.2017.
 */
public class WebinarHomeWork2 extends BaseScript {
    private static WebDriver driver;
    private static String login = "webinar.test@gmail.com";
    private static String password = "Xcg7299bnSmMuRLp9ITw";
    private static String categoryName = "Test_1";

    public static void main(String[] args) {

        try {
            driver = setupDriver();

            GeneralActions actions = new GeneralActions(driver);

            //login to the site
            log("Login to the site");
            actions.loginForm(login, password);

            //create category
            log("Creation new category");
            actions.createCategory(categoryName);

            //filter table by name of categories
            log("Filter data in table by categories name");
            WebElement filterCategories = driver.findElement(By.xpath("//tr[contains(@class,'filter row_hover')]/th[3]/input"));
            filterCategories.sendKeys(categoryName);
            filterCategories.submit();

            //check the name of categories in table
            log("The name of created categories is present in table");
            WebElement checkNameCategories = driver.findElement(By.xpath("//table[@id='table-category']//tr[1]/td[3]"));
            Assert.assertEquals(checkNameCategories.getText(),categoryName, "Not contain the categories");
        } finally {
            closeDriver(driver);
        }

    }
}
