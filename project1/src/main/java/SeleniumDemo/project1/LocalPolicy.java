package SeleniumDemo.project1;
import java.time.Duration;
import java.util.List;
import org.checkerframework.checker.units.qual.t;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocalPolicy extends Base {
	String xpathConfiguration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
    String xpathRealM = "//a[contains(text(),'realm-config')]";
    String xpathInitialAdd = "//oj-button[@id='add_local-policy_data']//div[@class='oj-button-label']";
    String xpathOkButton = "//oj-button[@id='apply']//span[@class='oj-button-text']";
    String idDeleteAll = "deleteAll_local-policy_buttonText";
    String xpathConfirm = "//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]";


    public void configuration(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathConfiguration)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathRealM)).click();
        Thread.sleep(2000);

    }

    public boolean addLocalPolicy(WebDriver driver, String fromAddress, String toAddress, String realM) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='local-policy']")).click();
        driver.findElement(By.xpath(xpathInitialAdd)).click();
        Thread.sleep(2000);
        WebElement fromInput = driver.findElement(By.id("from-address|input"));
        fromInput.sendKeys(" " + fromAddress);
        Thread.sleep(2000);
        WebElement toInput = driver.findElement(By.id("to-address|input"));
        toInput.sendKeys(" " + toAddress);
        WebElement realm = driver.findElement(By.id("source-realm|input"));
        realm.click();
        realm.sendKeys(realM);
        realm.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
//        driver.findElement(By.xpath("//div[@class='oj-text-field-container oj-text-field-has-end-slot']//a[@class='oj-searchselect-arrow oj-searchselect-open-icon oj-searchselect-icon oj-component-icon oj-clickable-icon-nocontext']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//span[normalize-space()='emergency']")).click();
//        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathOkButton)).click();
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + fromAddress + "')]"));
        if (element != null) {
            System.out.println(fromAddress + " " + " LocalPolicy " + " added Successfully");
            return true;
        } else {
            System.out.println("Error in Adding" + fromAddress + " LocalPolicy ");
            return false;
        }
    }

    public boolean editLocalPolicy(WebDriver driver, String toAddress, String newToAddress) throws InterruptedException {
        String toAddressString = String.valueOf(toAddress);
        String xpathRadio = "//oj-selector[@id='local-policy1_table_selector_view_realm_from_to/realm1/Hostname-0.0.0.0/Hostname-" + toAddressString + "']//input[@type='checkbox']";
        WebElement checkBoxPath = driver.findElement(By.xpath(xpathRadio));
        checkBoxPath.click();
        driver.findElement(By.id("local-policy1_editAction")).click();
        String xpathExpression = "//span[@aria-label='" + toAddress + " remove']";
        WebElement remove = driver.findElement(By.xpath(xpathExpression));
        remove.click();
        Thread.sleep(2000);
        WebElement toInput = driver.findElement(By.id("to-address|input"));
        toInput.sendKeys(" " + newToAddress);
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathOkButton)).click();
        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + newToAddress + "')]"));
        if (element1 == null) {
            System.out.println("Error in Editing" + newToAddress + " ");
            return false;
        } else {
            System.out.println(newToAddress + " " + "localPolicy " + " edited Successfully");
            return true;
        }
    }

    public boolean deleteLocalPolicy(WebDriver driver, String toAddress) throws InterruptedException {
        String toAddressString = String.valueOf(toAddress);
        String xpathRadio = "//oj-selector[@id='local-policy1_table_selector_view_realm_from_to/realm1/Hostname-0.0.0.0/Hostname-" + toAddressString + "']//input[@type='checkbox']";
        WebElement checkBoxPath = driver.findElement(By.xpath(xpathRadio));
        checkBoxPath.click();
        Thread.sleep(2000);
        driver.findElement(By.id("local-policy1_deleteAction")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathConfirm)).click();
        Thread.sleep(2000);
        WebElement initialAdd = driver.findElement(By.xpath("//div[@id='local-policy1_emptyDataIcon']"));
        if (initialAdd.isDisplayed()) {
            System.out.println("LocalPolicy deleted successfully");
        } else {
            System.out.println("Error in deleting LocalPolicy");
        }
        return true;
    }

    public boolean addMultipleLocalPolicies(WebDriver driver, String[] fromAddress, String[] toAddress, String realM, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='local-policy']")).click();
        Thread.sleep(2000);
        for (int i = 0; i < fromAddress.length; i++) {
            if (i == 0) {
                driver.findElement(By.xpath(xpathInitialAdd)).click();
                Thread.sleep(2000);
            } else {
                driver.findElement(By.id("add_local-policy")).click();
                Thread.sleep(2000);
            }
            WebElement fromInput = driver.findElement(By.id("from-address|input"));
            fromInput.sendKeys(" " + fromAddress[i]);
            Thread.sleep(2000);
            WebElement toInput = driver.findElement(By.id("to-address|input"));
            toInput.sendKeys(" " + toAddress[i]);
            WebElement realm = driver.findElement(By.id("source-realm|input"));
            realm.click();
            realm.sendKeys(realM);
            realm.sendKeys(Keys.ENTER);
            Thread.sleep(2000);
            driver.findElement(By.xpath(xpathOkButton)).click();
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + fromAddress[i] + "')]"));
            if (element != null) {
                System.out.println(fromAddress[i] + " " + "LocalPolicy" + " added successfully");
            } else {
                System.out.println("Error in Adding " + " " + "localPOlicy" + fromAddress[i]);
                return false;
            }
        }
        return true;
    }

    public boolean deleteAllLocalPolicy(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement delete = driver.findElement(By.id(idDeleteAll));
        if (delete.isDisplayed()) {
            delete.click();
            Thread.sleep(3000);
            driver.findElement(By.xpath(xpathConfirm)).click();
            System.out.println("All localPolicies Deleted Successfully ");
            return true;
        } else {
            System.out.println("No localPolicies to be deleted");
            return false;
        }
    }

    public boolean cleanUp(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='ldap-config']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[normalize-space()='local-policy']")).click();
        Thread.sleep(2000);
        if (driver.findElement(By.xpath(xpathInitialAdd)).isDisplayed()) {
            System.out.println("Local Policy CleanUp Done");
            Thread.sleep(2000);
        } else {
            boolean deleteAll = deleteAllLocalPolicy(driver);
            assert deleteAll;
            Thread.sleep(2000);
            System.out.println("Local policy CleanUp Done");
        }
        driver.findElement(By.xpath("//span[normalize-space()='realm-config']")).click();
        Thread.sleep(2000);
        if (driver.findElement(By.xpath("//oj-button[@id='add_realm-config_data']//div[@class='oj-button-label']")).isDisplayed()) {
            System.out.println("RealM CleanUp Done");
            Thread.sleep(2000);
        } else {
            driver.findElement(By.id("deleteAll_realm-config_buttonText")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[@class='oj-button-text']")).click();
            Thread.sleep(2000);
            System.out.println("RealM CleanUp Done");
        }
        return true;
    }

    public boolean deleteSelective(WebDriver driver, String[] fromAddress, String[] toAddress, String realM, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        for (int i = 0; i < fromAddress.length; i++) {
            String toAddressString = String.valueOf(toAddress[i]);
            String fromAddressString = String.valueOf(fromAddress[i]);
            String xpathRadio = "//oj-selector[@id='local-policy1_table_selector_view_realm_from_to/realm1/Hostname-" + fromAddressString + "/Hostname-" + toAddressString + "']//input[@type='checkbox']";
            WebElement checkBoxPath = driver.findElement(By.xpath(xpathRadio));
            checkBoxPath.click();
            Thread.sleep(2000);
            driver.findElement(By.id("local-policy1_deleteAction")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(xpathConfirm)).click();
            Thread.sleep(2000);
            String xpathLocalPolicy = "//td[contains(text(),'" + toAddressString + "')]";
            List<WebElement> iP = driver.findElements(By.xpath(xpathLocalPolicy));
            if (!iP.isEmpty()) {
                System.out.println("Error: LocalPolicy " + toAddressString + " still exists after deletion.");
                return false;
            }
        }
        System.out.println("All the selected LocalPolicies are deleted");
        return true;
    }

    public boolean enterLocalPolicyAndPressBack(WebDriver driver, String fromAddress, String toAddress, String realM) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='local-policy']")).click();
        driver.findElement(By.xpath(xpathInitialAdd)).click();
        Thread.sleep(2000);
        WebElement fromInput = driver.findElement(By.id("from-address|input"));
        fromInput.sendKeys(" " + fromAddress);
        Thread.sleep(2000);
        WebElement toInput = driver.findElement(By.id("to-address|input"));
        toInput.sendKeys(" " + toAddress);
        WebElement realm = driver.findElement(By.id("source-realm|input"));
        realm.click();
        realm.sendKeys(realM);
        realm.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'Back')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[@class='oj-button-text']")).click();
        Thread.sleep(2000);
        WebElement add = driver.findElement(By.xpath(xpathInitialAdd));
        if (add != null) {
            System.out.println("LocalPOlicy Not Added");
            return true;
        } else {
            System.out.println("LocalPolicy is Added");
            return false;
        }

    }

    public void localPolicyWithEmptyName(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='local-policy']")).click();
        driver.findElement(By.xpath(xpathInitialAdd)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathOkButton)).click();
        WebElement error1 = driver.findElement(By.xpath("//span[@id='error_from-address']"));
        WebElement error2 = driver.findElement(By.xpath("//span[@id='error_to-address']"));
        if (error1 != null && error2 != null) {
            System.out.println("Red Button Displayed");
        } else {
            System.out.println("Red Button Not Displayed");
        }
    }

    public boolean searchLocalPolicy(WebDriver driver, String fromAddress, String toAddress) throws InterruptedException {
        WebElement search = driver.findElement(By.xpath("//input[@id='searchid|input']"));
        search.sendKeys(toAddress);
        Thread.sleep(2000);
        WebElement searchButton = driver.findElement(By.id("tableSearchButton"));
        searchButton.click();
        Thread.sleep(2000);
        WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='local-policy1_emptyDataIcons']"));
        if (emptyDataIcons.isDisplayed()) {
            System.out.println("LocalPolicy " + toAddress + " is Not Available");
            return false;
        } else {
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + toAddress + "')]"));
            if (element != null) {
                System.out.println("Local Policy" + toAddress + " " + "is found successfully");
                return true;
            } else {
                System.out.println("Local Policy" + toAddress + " " + "is not found ");
                return false;
            }
        }
    }

    public boolean copyAndSaveLocalPolicy(WebDriver driver, String fromAddress, String newFromAddress, String toAddress, String newToAddress, String realMName) throws InterruptedException {
        String toAddressString = String.valueOf(toAddress);
        String fromAddressString = String.valueOf(fromAddress);
        String xpathRadio = "//oj-selector[@id='local-policy1_table_selector_view_realm_from_to/realm1/Hostname-" + fromAddressString + "/Hostname-" + toAddressString + "']//input[@type='checkbox']";
        WebElement checkBoxPath = driver.findElement(By.xpath(xpathRadio));
        checkBoxPath.click();
        Thread.sleep(2000);
        WebElement copy = driver.findElement(By.id("local-policy1_copyAction"));
        copy.click();
        String xpathExpressionFrom = "//span[@aria-label='" + fromAddress + " remove']";
        WebElement removeFrom = driver.findElement(By.xpath(xpathExpressionFrom));
        removeFrom.click();
        Thread.sleep(2000);
        WebElement toInputFrom = driver.findElement(By.id("from-address|input"));
        toInputFrom.sendKeys(" " + newFromAddress);
        Thread.sleep(2000);
        String xpathExpressionTo = "//span[@aria-label='" + toAddress + " remove']";
        WebElement removeTo = driver.findElement(By.xpath(xpathExpressionTo));
        removeTo.click();
        Thread.sleep(2000);
        WebElement toInputTo = driver.findElement(By.id("to-address|input"));
        toInputTo.sendKeys(" " + newToAddress);
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathOkButton)).click();
        String xpathNewLocalPolicy = "//oj-selector[@id='local-policy1_table_selector_view_realm_from_to/realm1/Hostname-" + newFromAddress + "/Hostname-" + newToAddress + "']//input[@type='checkbox']";
        WebElement newLP = driver.findElement(By.xpath(xpathNewLocalPolicy));
        if (newLP != null) {
            System.out.println("NewLP '" + newFromAddress + newToAddress + "' copied and saved successfully.");
            return true;
        } else {
            System.out.println("Error: LP '" + newFromAddress + newToAddress +  "' not found after copying and saving.");
            return false;
        }
    }
}



