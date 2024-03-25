package SeleniumDemo.project1;
import java.time.Duration;
import java.util.List;

import org.checkerframework.checker.units.qual.t;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TLSProfile extends Base {
	String xpathConfiguration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
    String xpathInitialAdd = "//oj-button[@id='add_tls-profile_data']//div[@class='oj-button-label']";
    String xpathOkButton = "//oj-button[@id='apply']//div[@class='oj-button-label']";
    String idDeleteAll = "deleteAll_tls-profile_buttonText";
    String xpathConfirmButton = "//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]";
    public void configuration(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathConfiguration)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'tls-profile')]")).click();
        Thread.sleep(2000);
    }
    public boolean add_TLS_Profile(WebDriver driver, String nAme, String eNtity, String cEr, String lIst) throws InterruptedException {
        driver.findElement(By.xpath(xpathInitialAdd)).click();
        Thread.sleep(2000);
        WebElement name = driver.findElement(By.id("name|input"));
        name.sendKeys(nAme);
        WebElement entity = driver.findElement(By.id("end-entity-certificate|input"));
        entity.sendKeys(eNtity);
        WebElement cer = driver.findElement(By.id("trusted-ca-certificates|input"));
        cer.sendKeys(cEr);
        WebElement list = driver.findElement(By.id("cert-status-profile-list|input"));
        list.sendKeys(lIst);
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathOkButton)).click();
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + nAme + "')]"));
        if (element != null) {
            System.out.println(nAme + " " + " added Successfully");
            return true;
        } else {
            System.out.println("Error in Adding" + nAme + " ");
            return false;
        }
    }
    public boolean edit_TLS_Profile(WebDriver driver, String name, String new_Name) throws InterruptedException {
        driver.findElement(By.xpath("//oj-selector[@id='tls-profile1_table_selector_" + name + "']//input[@type='checkbox']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("tls-profile1_editAction")).click();
        Thread.sleep(2000);
        WebElement nameField = driver.findElement(By.id("name|input"));
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
    public boolean delete_TLS_Profile(WebDriver driver, String name) throws InterruptedException {
        WebElement checkBox = driver.findElement(By.xpath("//oj-selector[@id='tls-profile1_table_selector_" + name + "']//input[@type='checkbox']"));
        checkBox.click();
        Thread.sleep(2000);
        WebElement delete = driver.findElement(By.id("tls-profile1_deleteAction"));
        delete.click();
        Thread.sleep(2000);
        WebElement confirm_Button = driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]"));
        confirm_Button.click();
        Thread.sleep(2000);
        System.out.println(name + " is deleted successfully ");
        return true;
    }
    public boolean deleteAll_TLS_Profile(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement deleteAll = driver.findElement(By.id(idDeleteAll));
        Thread.sleep(3000);
        if (deleteAll.isDisplayed()) {
            deleteAll.click();
            WebElement confirm = driver.findElement(By.xpath(xpathConfirmButton));
            confirm.click();
            Thread.sleep(2000);
            System.out.println("All T_L_S Profile Deleted Successfully ");
            return true;
        } else {
            System.out.println("No T_L_S Profile to be deleted");
            return false;
        }
    }
    public void cleanUp(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        try {
            driver.findElement(By.xpath("//span[normalize-space()='tls-global']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[normalize-space()='tls-profile']")).click();
            Thread.sleep(2000);
            if (driver.findElement(By.xpath(xpathInitialAdd)).isDisplayed()) {
                System.out.println("CleanUp Done");
            } else {
                boolean delete_All = deleteAll_TLS_Profile(driver);
                assert delete_All;
                Thread.sleep(2000);
                System.out.println("Cleanup Done.");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    public boolean add_Multiple_TLS_Profile(WebDriver driver, String nAme, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                driver.findElement(By.xpath(xpathInitialAdd)).click();
                Thread.sleep(2000);
            } else {
                driver.findElement(By.id("add_tls-profile")).click();
                Thread.sleep(2000);
            }
            WebElement name = driver.findElement(By.id("name|input"));
            name.sendKeys(nAme + i);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOkButton))).click();
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + nAme + i + "')]"));
            if (element != null) {
                System.out.println(nAme + i + " added successfully");
            } else {
                System.out.println("Error in Adding " + nAme + i);
                return false;
            }
        }
        return true;
    }
    public boolean deleteSelective(WebDriver driver, String nAme, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        for (int i = 0; i <= n; i++) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.findElement(By.xpath("//oj-selector[@id='tls-profile1_table_selector_" + nAme + i + "']//input[@type='checkbox']")).click();
            Thread.sleep(2000);
            WebElement delete = driver.findElement(By.id("tls-profile1_deleteAction"));
            delete.click();
            Thread.sleep(2000);
            WebElement confirm_Button = driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]"));
            confirm_Button.click();
            Thread.sleep(2000);
            String xpathTLS_Profile = "//td[contains(text(),'" + nAme + i + "')]";
            List<WebElement> mediaNames = driver.findElements(By.xpath(xpathTLS_Profile));
            if (!mediaNames.isEmpty()) {
                System.out.println("Error: Name " + nAme + i + " still exists after deletion.");
                return false;
            }
        }
        System.out.println("All selected names deleted successfully.");
        return true;
    }
    public boolean searchTLS_Profile(WebDriver driver, String nAme) throws InterruptedException {
        WebElement search = driver.findElement(By.id("searchid|input"));
        search.click();
        WebElement tableSearch = driver.findElement(By.id("tableSearchButton"));
        tableSearch.click();
        Thread.sleep(2000);
        WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='tls-profile1_emptyDataIcons']"));
        if (emptyDataIcons.isDisplayed()) {
            System.out.println("Profile '" + nAme + "' is Not Available");
            return false;
        } else {
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + nAme + "')]"));
            if (element != null) {
                System.out.println(nAme + " " + "is found successfully");
                return true;
            } else {
                System.out.println(nAme + " " + "is not found ");
                return false;
            }

        }
    }
    public boolean enterTLS_ProfileAndPressBack(WebDriver driver, String nAme) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathInitialAdd)).click();
        WebElement name = driver.findElement(By.id("name|input"));
        name.sendKeys(nAme);
        driver.findElement(By.xpath("//button[@aria-label='Back']//span[@class='oj-button-text']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[@class='oj-button-text']")).click();
        Thread.sleep(2000);
        WebElement add = driver.findElement(By.xpath(xpathInitialAdd));
        if (add != null) {
            System.out.println("Policy Not Added");
            return true;
        } else {
            System.out.println("Policy is Added");
            return false;
        }
    }

    public boolean TLS_ProfileWithEmptyName(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathInitialAdd)).click();
        driver.findElement(By.xpath(xpathOkButton)).click();
        Thread.sleep(2000);
        WebElement error = driver.findElement(By.id("error_name"));
        if (error != null) {
            System.out.println("Red Button Displayed");
            return true;
        } else {
            System.out.println("Red Button Not Displayed");
            return false;
        }
    }
    public boolean copyAndSave_TLSProfile(WebDriver driver,String name,String newName) throws InterruptedException {
        WebElement button = driver.findElement(By.xpath("//oj-selector[@id='tls-profile1_table_selector_" + name +"']//input[@type='checkbox']"));
        button.click();
        Thread.sleep(2000);
        driver.findElement(By.id("tls-profile1_copyAction")).click();
        Thread.sleep(2000);
        WebElement nAme = driver.findElement(By.id("name|input"));
        nAme.clear();
        nAme.sendKeys(newName);
        driver.findElement(By.xpath(xpathOkButton)).click();
        Thread.sleep(2000);
        String xpathNewProfileName = "//oj-selector[@id='tls-profile1_table_selector_" + newName + "']//input[@type='checkbox']";
        Thread.sleep(2000);
        WebElement new_Profile = driver.findElement(By.xpath(xpathNewProfileName));
        if (new_Profile!=null) {
            System.out.println("TLS profile '" + newName + "' copied and saved successfully.");
            return true;
        } else {
            System.out.println("Error:TLS profile '" + newName + "' not found after copying and saving.");
            return false;
        }

    }
}

