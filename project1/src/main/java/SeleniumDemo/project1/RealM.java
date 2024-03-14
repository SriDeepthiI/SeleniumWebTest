package SeleniumDemo.project1;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RealM extends Base {
	String xpathconfiguration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
    String xpathRealM = "//a[contains(text(),'realm-config')]";
        String xpathIntialAdd = "//button[@aria-label='Realm Config Add']//span[@class='oj-button-text']";
    String xpathDeleteAll = "//span[@id='deleteAll_realm-config_buttonText']";
    String xpathConfirmDelete = "//oj-button[@id='dialogConfirmBtn']//div[@class='oj-button-label']";
    String xpathOkButton = "//span[@data-bind='text: label'][normalize-space()='OK']";
    String xpathReferCallTransferRadio = "//oj-select-single[@id='refer-call-transfer']//a[@role='presentation']";
    String xpathEnable = "/html[1]/body[1]/div[1]/div[1]/div[1]/oj-list-view[1]/ul[1]/li[3]/div[1]";
    String xpathAdminMenuDropDown ="//button[@aria-label='admin menu']//span[@class='oj-button-icon oj-end oj-component-icon oj-button-menu-dropdown-icon']";
    String xpathLogOut ="/html[1]/body[1]/div[1]/div[1]/oj-menu[1]/oj-option[3]/a[1]/span[1]";
    String xpathLogoutConfirm ="//oj-button[@id='dialogYesBtn']//span[@class='oj-button-text']";


    public void configuration(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathconfiguration)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathRealM)).click();
        Thread.sleep(2000);
    }
    public boolean addRealM(WebDriver driver, String identifier, int thresholdValue, int alarmValue) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathIntialAdd)).click();
        WebElement name = driver.findElement(By.id("identifier|input"));
        name.sendKeys(identifier);
        driver.findElement(By.xpath(xpathReferCallTransferRadio)).click();
        driver.findElement(By.xpath(xpathEnable)).click();
        driver.findElement(By.name("hide-egress-media-update")).click();
        driver.findElement(By.name("steering-pool-lower-threshold")).clear();
        WebElement threshold = driver.findElement(By.name("steering-pool-lower-threshold"));
        threshold.sendKeys("" + thresholdValue);
        driver.findElement(By.name("steering-pool-alarm-monitoring-time")).clear();
        WebElement alarm = driver.findElement(By.name("steering-pool-alarm-monitoring-time"));
        alarm.sendKeys("" + alarmValue);
        driver.findElement(By.xpath(xpathOkButton)).click();
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + identifier + "')]"));
        if (element != null) {
            System.out.println(identifier + " " + "added successfully");
            return true;
        } else {
            System.out.println("Error in Adding" + identifier+ " ");
            return false;
        }
    }
     public boolean editRealM(WebDriver driver, String name, String new_Name) throws InterruptedException {
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//oj-selector[@id='realm-config1_table_selector_" + name + "']//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//span[@id='realm-config1_editAction']")).click();
        WebElement nameField = driver.findElement(By.id("identifier|input"));
        nameField.clear();
        nameField.sendKeys(new_Name);
        driver.findElement(By.xpath(xpathOkButton)).click();
         WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + new_Name + "')]"));
         if (element1 == null) {
             System.out.println("Error in Editing" + new_Name + " ");
             return false;
         } else {
             System.out.println(new_Name + " " + "edited successfully");
             return true;
         }
    }
    public boolean deleteAllRealM(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement delete = driver.findElement(By.xpath(xpathDeleteAll));
        if (delete != null) {
            delete.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(xpathConfirmDelete)).click();
            Thread.sleep(2000);
            System.out.println("All RealM Deleted Successfully ");
            return true;
        } else {
            System.out.println("Error in deleting RealM");
            return false;
        }
    }

    public boolean addMultipleRealM(WebDriver driver, String identifier, int thresholdvalue, int alarmvalue, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                driver.findElement(By.xpath(xpathIntialAdd)).click();
            } else {
                driver.findElement(By.id("add_realm-config")).click();
            }
            WebElement name = driver.findElement(By.id("identifier|input"));
            name.sendKeys(identifier + i);
            driver.findElement(By.name("mm-in-realm")).click();
            driver.findElement(By.xpath(xpathReferCallTransferRadio)).click();
            driver.findElement(By.xpath(xpathEnable)).click();
            driver.findElement(By.name("hide-egress-media-update")).click();
            driver.findElement(By.name("steering-pool-lower-threshold")).clear();
            WebElement threshold = driver.findElement(By.name("steering-pool-lower-threshold"));
            threshold.sendKeys("" + thresholdvalue);
            driver.findElement(By.name("steering-pool-alarm-monitoring-time")).clear();
            WebElement alarm = driver.findElement(By.name("steering-pool-alarm-monitoring-time"));
            alarm.sendKeys("" + alarmvalue);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOkButton))).click();
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + identifier + i + "')]"));
            if (element != null) {
                System.out.println(identifier+ i + " added successfully");
            } else {
                System.out.println("Error in Adding " + identifier + i);
                return false;
            }
        }
        return true;
        }

    public boolean deleteSelective(WebDriver driver, String nameOfIdentifier, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        for (int i = 0; i <= n; i++) {
            String xpathRadio = "//oj-selector[@id='realm-config1_table_selector_" + nameOfIdentifier + i + "']//input[@type='checkbox']";
            driver.findElement(By.xpath(xpathRadio)).click();
            Thread.sleep(2000);
            driver.findElement(By.id("realm-config1_deleteAction")).click();
            WebElement confirmDelete = driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]"));
            confirmDelete.click();
            Thread.sleep(2000);
            String xpathIdentifier = "//td[contains(text(),'" + nameOfIdentifier + i + "')]";
            List<WebElement> identifiers = driver.findElements(By.xpath(xpathIdentifier));
            if (!identifiers.isEmpty()) {
                System.out.println("Error: Identifier " + nameOfIdentifier + i + " still exists after deletion.");
                return false;
            }
        }
            System.out.println("All selected identifiers deleted successfully.");
            return true;
    }



    public void cleanUp(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        try {
            driver.findElement(By.xpath("//a[@title='media-manager']//span[@class='oj-navigationlist-item-label'][normalize-space()='media-manager']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[normalize-space()='realm-config']")).click();
            Thread.sleep(2000);
            if (driver.findElement(By.xpath(xpathIntialAdd)).isDisplayed()) {
                System.out.println("CleanUp Done");
            } else {
                deleteAllRealM(driver);
                System.out.println("Cleanup Done.");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    public boolean copyAndSaveWithNewName(WebDriver driver ,String nameOfRealM,String newNameOfRealM) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement button = driver.findElement(By.xpath("//oj-selector[@id='realm-config1_table_selector_"+ nameOfRealM + "']//input[@type='checkbox']"));
        button.click();
        driver.findElement(By.id("realm-config1_copyAction")).click();
        WebElement name1 = driver.findElement(By.id("identifier|input"));
        name1.clear();
        name1.sendKeys(newNameOfRealM);
        driver.findElement(By.xpath(xpathOkButton)).click();
        Thread.sleep(2000);
        String xpathNewNameOfRealM = "//oj-selector[@id='realm-config1_table_selector_" + newNameOfRealM + "']//input[@type='checkbox']";
        Thread.sleep(2000);
        WebElement newRealM = driver.findElement(By.xpath(xpathNewNameOfRealM));
        if (newRealM!=null) {
            System.out.println("NewRealM '" + newNameOfRealM + "' copied and saved successfully.");
            return true;
        } else {
            System.out.println("Error: RealM '" + newNameOfRealM + "' not found after copying and saving.");
            return false;
        }
    }
    public boolean enterRealMAndPressBack(WebDriver driver,String nameOfIdentifier) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathIntialAdd)).click();
        Thread.sleep(2000);
        WebElement name2 = driver.findElement(By.id("identifier|input"));
        name2.sendKeys(nameOfIdentifier);
        driver.findElement(By.xpath("//span[contains(text(),'Back')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("dialogYesBtn")).click();
        Thread.sleep(2000);
        WebElement add = driver.findElement(By.xpath(xpathIntialAdd));
        if(add!=null){
            System.out.println("Policy Not Added");
            return true;
        }
        else {
            System.out.println("Policy is Added");
            return false;
        }
    }
    public void realMWithEmptyName(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathIntialAdd)).click();
        driver.findElement(By.xpath(xpathOkButton)).click();
        WebElement error = driver.findElement(By.xpath("//span[@id='error_identifier']"));
        if (error != null) {
            System.out.println("Red Button Displayed");
        } else {
            System.out.println("Red Button Not Displayed");
        }
    }

    public boolean searchRealM (WebDriver driver,String identifierName) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement search = driver.findElement(By.id("searchid|input"));
        search.sendKeys(identifierName);
        driver.findElement(By.id("tableSearchButton")).click();
        Thread.sleep(2000);
        WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='realm-config1_emptyDataIcons']"));
        if (emptyDataIcons.isDisplayed()) {
            System.out.println("Identifier '" + identifierName + "' is Not Available");
            return false;
        }
        else{
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + identifierName + "')]"));
            if (element != null) {
                System.out.println(identifierName + " " + "is found successfully");
                return true;
            } else {
                System.out.println(identifierName + " " + "is not found ");
                return false;
            }

        }

    }
}




