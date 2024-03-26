package SeleniumDemo.project1;
import java.time.Duration;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.*;

public class SIPInterface extends Base{
	String xpathConfiguration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
    String xpathRealM = "//a[contains(text(),'realm-config')]";
    String xpathIntialAdd = "//oj-button[@id='add_sip-interface_data']//div[@class='oj-button-label']";
    String xpathOKButton = "//oj-button[@id='apply']//span[@class='oj-button-text']";
    String idDeleteAll = "deleteAll_sip-interface_buttonText";
    String xpathConfirm = "//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]";


    public void configuration(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathConfiguration)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathRealM)).click();
    }

    public boolean addSipInterface(WebDriver driver, String SIPInterface) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'sip-interface')]")).click();
        driver.findElement(By.xpath(xpathIntialAdd)).click();
        WebElement input = driver.findElement(By.xpath("//input[@id='realm-id|input']"));
        input.sendKeys(SIPInterface);
//        driver.findElement(By.xpath("//oj-select-single[@id='nat-traversal']//a[@role='presentation']")).click();
//        Thread.sleep(2000);
//        WebElement always = driver.findElement(By.xpath("//li[@id='ui-id-457']"));
//        always.click();
//        driver.findElement(By.xpath("//input[@id='ui-id-442|cb']")).click();
        driver.findElement(By.xpath(xpathOKButton)).click();
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + SIPInterface + "')]"));
        if (element != null) {
            System.out.println(SIPInterface + "" + "SIP Interface " + " added Successfully");
            return true;
        } else {
            System.out.println("Error in Adding" + SIPInterface + "SIPInterface ");
            return false;
        }
    }

    public boolean cleanup(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        try {
            driver.findElement(By.xpath("//span[normalize-space()='sip-feature']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[normalize-space()='sip-interface']")).click();
            if (driver.findElement(By.xpath(xpathIntialAdd)).isDisplayed()) {
                System.out.println("SIP CleanUp Done");
                Thread.sleep(2000);
            } else {
                boolean deleteAll = deleteAllSIPInterface(driver);
                assert deleteAll;
                Thread.sleep(2000);
                System.out.println("SIP Cleanup Done.");
            }
            driver.findElement(By.xpath("//a[@title='media-manager group']//span[@class='oj-navigationlist-item-label'][normalize-space()='media-manager']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[normalize-space()='realm-config']")).click();
            Thread.sleep(2000);
            if (driver.findElement(By.xpath("//button[@aria-label='Realm Config Add']//span[@class='oj-button-text']")).isDisplayed()) {
                System.out.println("RealM CleanUp Done");
            } else {
                driver.findElement(By.id("deleteAll_realm-config_buttonText")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]")).click();
                Thread.sleep(2000);
                System.out.println("RealM Cleanup Done.");

            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return true;
    }

    public boolean deleteAllSIPInterface(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement delete = driver.findElement(By.id(idDeleteAll));
        Thread.sleep(2000);
        if (delete.isDisplayed()) {
            delete.click();
            Thread.sleep(3000);
            driver.findElement(By.xpath(xpathConfirm)).click();
            Thread.sleep(2000);
            System.out.println("All Interfaces Deleted Successfully ");
            return true;
        } else {
            System.out.println("No Interfaces to be deleted");
            return false;
        }
    }

    public boolean editSIP(WebDriver driver, String oldSIP, String newSIP) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement checkBox = driver.findElement(By.xpath("//oj-selector[@id='sip-interface1_table_selector_" + oldSIP + "']//input[@type='checkbox']"));
        checkBox.click();
        driver.findElement(By.id("sip-interface1_editAction")).click();
        Thread.sleep(2000);
        WebElement realM = driver.findElement(By.id("realm-id|input"));
        realM.clear();
        Thread.sleep(2000);
        realM.sendKeys(newSIP);
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathOKButton)).click();
        Thread.sleep(2000);
        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + newSIP + "')]"));
        if (element1 == null) {
            System.out.println("Error in Editing" + newSIP + " ");
            return false;
        } else {
            System.out.println(newSIP + "" + "SIP Interface " + " edited Successfully");
            return true;
        }
    }

    public boolean deleteSIP(WebDriver driver, String sipInterfaceRealM) throws InterruptedException {
        WebElement checkBox = driver.findElement(By.xpath("//oj-selector[@id='sip-interface1_table_selector_" + sipInterfaceRealM + "']//input[@type='checkbox']"));
        checkBox.click();
        Thread.sleep(2000);
        driver.findElement(By.id("sip-interface1_deleteAction")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathConfirm)).click();
        Thread.sleep(2000);
        return true;
    }
    public boolean addMultipleSIP(WebDriver driver, String SIP,int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'sip-interface')]")).click();
        Thread.sleep(2000);
            for (int i =0; i <= n; i++) {
                if (i == 0) {
                    driver.findElement(By.xpath(xpathIntialAdd)).click();
                } else {
                    driver.findElement(By.id("add_sip-interface")).click();
                }
                driver.findElement(By.xpath("//div[@id='oj-combobox-choice-realm-id']//a[@role='presentation']")).click();
                Thread.sleep(2000);
                WebElement name = driver.findElement(By.id("realm-id|input"));
                name.sendKeys(SIP + i);
                name.sendKeys(Keys.ENTER);
                Thread.sleep(2000);
                driver.findElement(By.xpath(xpathOKButton)).click();
                Thread.sleep(2000);
                WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + SIP + i + "')]"));
                if (element.isDisplayed()) {
                    System.out.println("SIP" + " "+ SIP + i +" added successfully");
                } else {
                    System.out.println("Error in Adding " +"SIP"+" " + SIP + i);
                    return false;
                }
            }
            return true;
    }
    public boolean deleteSelective(WebDriver driver, String SIP, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        for (int i = 0; i <= n; i++) {
            String xpathRadio = "//oj-selector[@id='sip-interface1_table_selector_"+ SIP + i +"']//input[@type='checkbox']";
            driver.findElement(By.xpath(xpathRadio)).click();
            Thread.sleep(2000);
            driver.findElement(By.id("sip-interface1_deleteAction")).click();
            WebElement confirmButton = driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[@class='oj-button-text']"));
            confirmButton.click();
            Thread.sleep(3000);
            String xpathIdentifier = "//td[contains(text(),'" + SIP+ i + "')]";
            Thread.sleep(3000);
            List<WebElement> SIPs = driver.findElements(By.xpath(xpathIdentifier));
            Thread.sleep(3000);
            if (!SIPs.isEmpty()) {
                System.out.println("Error: SIP " + SIP + i + " still exists after deletion.");
                return false;
            }
        }
        System.out.println("All selected SIPs deleted successfully.");
        return true;
    }
    public boolean copyAndSaveSIP(WebDriver driver ,String nameOfSIP,String newNameOfSIP) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement button = driver.findElement(By.xpath("//oj-selector[@id='sip-interface1_table_selector_" + nameOfSIP + "']//input[@type='checkbox']"));
        button.click();
        Thread.sleep(2000);
        driver.findElement(By.id("sip-interface1_copyAction")).click();
        Thread.sleep(2000);
        WebElement name1 = driver.findElement(By.id("realm-id|input"));
        name1.clear();
        name1.sendKeys(newNameOfSIP);
        name1.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathOKButton)).click();
        Thread.sleep(2000);
        String xpathNewNameOfSIP = "//oj-selector[@id='sip-interface1_table_selector_" + newNameOfSIP + "']//input[@type='checkbox']";
        Thread.sleep(2000);
        WebElement newSIP = driver.findElement(By.xpath(xpathNewNameOfSIP));
        if (newSIP!=null) {
            System.out.println("NewSIP '" + newNameOfSIP + "' copied and saved successfully.");
            return true;
        } else {
            System.out.println("Error: SIP '" + newNameOfSIP + "' not found after copying and saving.");
            return false;
        }
    }
    public boolean enterSIPAndPressBack(WebDriver driver,String nameOfSIP) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'sip-interface')]")).click();
        driver.findElement(By.xpath(xpathIntialAdd)).click();
        WebElement input = driver.findElement(By.xpath("//input[@id='realm-id|input']"));
        input.sendKeys(nameOfSIP);
        driver.findElement(By.xpath("//span[contains(text(),'Back')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[@class='oj-button-text']")).click();
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
    public boolean searchSIP (WebDriver driver,String nameOfSIP) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement search = driver.findElement(By.id("searchid|input"));
        search.sendKeys(nameOfSIP);
        driver.findElement(By.id("tableSearchButton")).click();
        WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='sip-interface1_emptyDataIcons']"));
        if (emptyDataIcons.isDisplayed()) {
            System.out.println("Identifier '" +nameOfSIP + "' is Not Available");
            return false;
        }
        else{
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + nameOfSIP + "')]"));
            if (element != null) {
                System.out.println("SIP"+ nameOfSIP + " " + "is found successfully");
                return true;
            } else {
                System.out.println("SIP"+ nameOfSIP + " " + "is not found ");
                return false;
            }

        }
    }
    public void sIPWithEmptyName(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'sip-interface')]")).click();
        driver.findElement(By.xpath(xpathIntialAdd)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathOKButton)).click();
        WebElement error = driver.findElement(By.xpath("//span[@class='mainHeader']"));
        if (error != null) {
            System.out.println("Main Header Is Displayed");
            System.out.println("SIP Could Not be Added");
        } else {
            System.out.println("Main Header Is not Displayed");
            System.out.println("SIP is Added");
        }
    }
}
