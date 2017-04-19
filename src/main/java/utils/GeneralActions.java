package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static utils.BaseScript.log;


/**
 * Created by Alenka on 16.04.2017.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;
    private static String BASE_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
    private By catalogueLink = By.cssSelector("#subtab-AdminCatalog");
    private By categoriesLink = By.cssSelector("#subtab-AdminCategories");
    private By createCategoriesButton = By.cssSelector("#page-header-desc-category-new_category");
    private By createCategoriesForm = By.xpath("//form[@id='category_form']");
    private By nameCategories = By.xpath("//input[@id='name_1']");
    private By saveCategoriesButton = By.xpath("//button[@id='category_form_submit_btn']");
    private By confirmateMessageCategories = By.xpath("//div[@class='alert alert-success']");

    public GeneralActions(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,30);
    }


    public void loginForm (String login, String password){
        driver.navigate().to(BASE_URL);
        WebElement loginField = driver.findElement(By.xpath("//input[@id='email']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='passwd']"));
        WebElement logibButton = driver.findElement(By.xpath("//button[contains(@class, 'ladda-button')]"));
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        logibButton.click();
    }

    public void createCategory(String categoryName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(catalogueLink));
        WebElement catalogueLink = driver.findElement(this.catalogueLink);
        WebElement categoriesLink = driver.findElement(this.categoriesLink);

        log ("Click the 'Categories' item in main menu");
        Actions actions = new Actions(driver);
        actions.moveToElement(catalogueLink).perform();
        actions.moveToElement(categoriesLink).perform();
        categoriesLink.click();
        //.build().perform();

        //Assert.assertSame();

        //create category
        log("Click the 'Create categories' button");
        driver.findElement(createCategoriesButton).click();
        log("'Create categories' page is displayed");
        wait.until(ExpectedConditions.visibilityOfElementLocated(createCategoriesForm));

        //enter the name of Category
        log("Enter the name of categories");
        driver.findElement(nameCategories).sendKeys(categoryName);

        //click the "Save" button
        log("Click the 'Save' button");
        driver.findElement(saveCategoriesButton).click();

        //check the confirmation message is displayed
        log("Confirmation message is displayed");
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmateMessageCategories));
        WebElement confirmateMessage = driver.findElement(confirmateMessageCategories);
        Assert.assertTrue(confirmateMessage.isDisplayed());
    }

    //


    /*public void createCategoryJS(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(catalogueLink));
        JavaScriptExecutor executor = (JavaScriptExecutor)driver;
        WebElement categoriesLink = driver.findElement(this.categoriesLink);
        executor.executeScript("arguments[0].click", categoriesLink);
    }*/
}


