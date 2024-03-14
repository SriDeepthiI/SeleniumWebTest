package SeleniumDemo.project1;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterConfig extends Base {
	String xpathConfiguration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
    String xpathFilterConfig = "//a[contains(text(),'filter-config')]";
    String xpathInitialAdd = "//button[@aria-label='Filter Config Add']//span[@class='oj-button-text']";
    String xpathOkButton = "//span[@data-bind='text: label'][normalize-space()='OK']";
    String xpathAdminMenuDropDown = "//button[@aria-label='admin menu']//span[@class='oj-button-icon oj-end oj-component-icon oj-button-menu-dropdown-icon']";
    String xpathLogOut = "/html[1]/body[1]/div[1]/div[1]/oj-menu[1]/oj-option[3]/a[1]/span[1]";
    String xpathLogoutConfirm = "//oj-button[@id='dialogYesBtn']//span[@class='oj-button-text']";
    String xpathDeleteAll = "//span[@id='deleteAll_filter-config_buttonText']";
    String xpathconfirmDelete = "//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]";

    public void configuration(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathConfiguration))).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathFilterConfig))).click();
        Thread.sleep(2000);
    }
    public boolean addFilterConfig(WebDriver driver, String filterName, String filterUser) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathInitialAdd))).click();
        WebElement name = driver.findElement(By.id("name|input"));
        name.sendKeys(filterName);
        WebElement user = driver.findElement(By.id("user|input"));
        user.sendKeys(filterUser);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOkButton))).click();
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + filterName + "')]"));
        if (element != null) {
            System.out.println(filterName + " " + "added successfully");
            return true;
        } else {
            System.out.println("Error in Adding" + filterName + " ");
            return false;
        }
    }
    public boolean editFilterConfig(WebDriver driver, String nameOfFilterConfig, String new_nameOfFilterConfig) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.xpath("//oj-selector[@id='filter-config1_table_selector_" + nameOfFilterConfig + "']//input[@type='checkbox']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-config1_editAction"))).click();
        WebElement nameField = driver.findElement(By.id("name|input"));
        nameField.clear();
        nameField.sendKeys(new_nameOfFilterConfig);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOkButton))).click();
        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + new_nameOfFilterConfig + "')]"));
        if (element1 == null) {
            System.out.println("Error in Editing" + nameOfFilterConfig + " ");
            return false;
        } else {
            System.out.println(nameOfFilterConfig + " " + "edited successfully");
            return true;
        }
    }
    public boolean deleteFilterConfig(WebDriver driver, String nameOfFilterConfig) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement checkBox = driver.findElement(By.xpath("//oj-selector[@id='filter-config1_table_selector_" + nameOfFilterConfig + "']//input[@type='checkbox']"));
        checkBox.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-config1_deleteAction"))).click();
        WebElement confirm = driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]"));
        confirm.click();
        Thread.sleep(2000);
        System.out.println(nameOfFilterConfig + " is deleted successfully ");
        return true;
    }
    public boolean addMultipleFilterConfig(WebDriver driver, String filterName, String filterUser, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                driver.findElement(By.xpath(xpathInitialAdd)).click();
                Thread.sleep(2000);
            } else {
                driver.findElement(By.id("add_filter-config")).click();
                Thread.sleep(2000);
            }
            WebElement name = driver.findElement(By.id("name|input"));
            name.sendKeys(filterName + i);
            WebElement user = driver.findElement(By.id("user|input"));
            user.sendKeys(filterUser);
            user.sendKeys(Keys.ENTER);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOkButton))).click();
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + filterName + i + "')]"));
            if (element != null) {
                System.out.println(filterName + i + " added successfully");
            } else {
                System.out.println("Error in Adding " + filterName + i);
                return false;
            }
        }
        return true;
    }
    public boolean deleteAllFilterConfig(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement deleteAll = driver.findElement(By.xpath(xpathDeleteAll));
        if (deleteAll != null) {
            deleteAll.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(xpathconfirmDelete)).click();
            Thread.sleep(2000);
            System.out.println("All Filter Config Deleted Successfully ");
            return true;
        } else {
            System.out.println("Error in deleting Filter Config");
            return false;
        }
    }
    public boolean deleteSelectiveFilterConfig(WebDriver driver, String nameOfFilterConfig, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        for (int i = 0; i <= n; i++) {
            String xpathRadio = "//oj-selector[@id='filter-config1_table_selector_" + nameOfFilterConfig + i + "']//input[@type='checkbox']";
            driver.findElement(By.xpath(xpathRadio)).click();
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-config1_deleteAction"))).click();
            Thread.sleep(2000);
            WebElement confirm = driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]"));
            confirm.click();
            String xpathFilterConfig = "//td[contains(text(),'" + nameOfFilterConfig + i + "')]";
            List<WebElement> filterConfigs = driver.findElements(By.xpath(xpathFilterConfig));
            if (!((List<?>) filterConfigs).isEmpty()) {
                System.out.println("Error: Filter configuration " + nameOfFilterConfig + i + " still exists after deletion.");
                return false;
            }
        }
        System.out.println("All selected filter configurations deleted successfully.");
        return true;
    }
    public void cleanUp(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        try {
            driver.findElement(By.xpath("//span[normalize-space()='ldap-config']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[normalize-space()='filter-config']")).click();
            Thread.sleep(2000);
            if (driver.findElement(By.xpath(xpathInitialAdd)).isDisplayed()) {
                System.out.println("CleanUp Done");
            } else {
                deleteAllFilterConfig(driver);
                System.out.println("Cleanup Done.");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    public boolean copySaveWithNewName(WebDriver driver, String nameOfFilterConfig, String new_nameOfFilterConfig) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//oj-selector[@id='filter-config1_table_selector_" + nameOfFilterConfig + "']//input[@type='checkbox']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-config1_copyAction"))).click();
        WebElement nameField = driver.findElement(By.id("name|input"));
        nameField.clear();
        nameField.sendKeys(new_nameOfFilterConfig);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOkButton))).click();
        String xpathNewFilterConfig = "//oj-selector[@id='filter-config1_table_selector_" + new_nameOfFilterConfig + "']//input[@type='checkbox']";
        Thread.sleep(2000);
        WebElement newFilterConfig = driver.findElement(By.xpath(xpathNewFilterConfig));
        if (newFilterConfig != null) {
            System.out.println("Filter configuration '" + new_nameOfFilterConfig + "' copied and saved successfully.");
            return true;
        } else {
            System.out.println("Error: Filter configuration '" + new_nameOfFilterConfig + "' not found after copying and saving.");
            return false;
        }

    }
    public boolean searchPolicy(WebDriver driver, String nameOFFilter) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement search = driver.findElement(By.xpath("//input[@id='searchid|input']"));
        search.sendKeys(nameOFFilter);
        driver.findElement(By.id("tableSearchButton")).click();
        WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='filter-config1_emptyDataIcons']"));
        if (emptyDataIcons.isDisplayed()) {
            System.out.println("Policy '" + nameOFFilter + "' is Not Available");
            return false;
        } else {
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + nameOFFilter + "')]"));
            if (element != null) {
                System.out.println(nameOFFilter + " " + "is found successfully");
                return true;
            } else {
                System.out.println(nameOFFilter + " " + "is not found ");
                return false;
            }

        }
    }
    public boolean enterFilter_ConfigAndPressBack(WebDriver driver,String nameOfFilter) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathInitialAdd)).click();
        Thread.sleep(2000);
        WebElement name = driver.findElement(By.xpath("//input[@id='name|input']"));
        name.sendKeys(nameOfFilter);
        driver.findElement(By.xpath("//span[contains(text(),'Back')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[contains(text(),'Yes')]")).click();
        Thread.sleep(2000);
        WebElement add = driver.findElement(By.xpath(xpathInitialAdd));
        if(add!=null){
            System.out.println("filter Not Added");
            return true;
        }
        else {
            System.out.println("filter is Added");
            return false;
        }
    }
    public boolean filterWithEmptyName(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathInitialAdd)).click();
        driver.findElement(By.xpath("//span[@data-bind='text: label'][normalize-space()='OK']")).click();
        WebElement error = driver.findElement(By.xpath("//span[@id='error_name']"));
        if(error!=null){
            System.out.println("Red Button Displayed");
            return true;
        }
        else {
            System.out.println("Red Button Not Displayed");
            return false;
        }
    }

}
