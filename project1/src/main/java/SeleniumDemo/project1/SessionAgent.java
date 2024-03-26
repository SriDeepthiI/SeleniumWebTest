package SeleniumDemo.project1;
import java.time.Duration;
import java.util.List;

import org.checkerframework.checker.units.qual.t;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SessionAgent extends Base {
	String xpathConfiguration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
    String xpathRealM = "//a[contains(text(),'realm-config')]";
    String xpathIntialAdd = "//oj-button[@id='add_session-agent_data']//div[@class='oj-button-label']";
    String xpathrfc2833Mode = "//oj-select-single[@id='rfc2833-mode']//a[@role='presentation']";
    String xpathReferCallTransfer = "//oj-select-single[@id='refer-call-transfer']//a[@role='presentation']";
    String xpathOkButton = "//span[@data-bind='text: label'][normalize-space()='OK']";
    String idDeleteAll = "deleteAll_session-agent_buttonText";
    String xpathConfirm = "//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]";


    public void configuration(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathConfiguration)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathRealM)).click();
        Thread.sleep(2000);

    }

    public boolean addSessionAgent(WebDriver driver, String hostName, String iP, String realM) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        driver.findElement(By.xpath("//li[@id='leftNav_session-agent']//a[@title='session-agent']")).click();
        driver.findElement(By.xpath(xpathIntialAdd)).click();
        Thread.sleep(2000);
        WebElement name = driver.findElement(By.id("hostname|input"));
        name.sendKeys(hostName);
        WebElement iPAddress = driver.findElement(By.id("ip-address|input"));
        iPAddress.sendKeys(" " + iP);
        WebElement realMId = driver.findElement(By.id("realm-id|input"));
        realMId.sendKeys(realM);
        realMId.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement rfc2833Mode = driver.findElement(By.xpath(xpathrfc2833Mode));
        rfc2833Mode.click();
        Thread.sleep(2000);
        WebElement dualRadio = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/oj-list-view[1]/ul[1]/li[1]"));
        dualRadio.click();
        Thread.sleep(2000);
        WebElement referCallTransfer = driver.findElement(By.xpath(xpathReferCallTransfer));
        referCallTransfer.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[normalize-space()='dynamic']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathOkButton)).click();
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + hostName + "')]"));
        if (element != null) {
            System.out.println(hostName + " " + " SessionAgent " + " added Successfully");
            return true;
        } else {
            System.out.println("Error in Adding" + hostName + " SessionAgent ");
            return false;
        }
    }

    public boolean editSessionAgent(WebDriver driver, String hostName, String new_hostName) throws InterruptedException {
        driver.findElement(By.xpath("//oj-selector[@id='session-agent1_table_selector_" + hostName + "']//input[@type='checkbox']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("session-agent1_editAction")).click();
        Thread.sleep(2000);
        WebElement name = driver.findElement(By.id("hostname|input"));
        name.clear();
        name.sendKeys(new_hostName);
        driver.findElement(By.xpath(xpathOkButton)).click();
        Thread.sleep(2000);
        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + new_hostName + "')]"));
        if (element1 == null) {
            System.out.println("Error in Editing" + new_hostName + " ");
            return false;
        } else {
            System.out.println(new_hostName + " " + "SessionAgent " + " edited Successfully");
            return true;
        }
    }

    public boolean deleteSessionAgent(WebDriver driver, String hostName) throws InterruptedException {
        driver.findElement(By.xpath("//oj-selector[@id='session-agent1_table_selector_" + hostName + "']//input[@type='checkbox']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@aria-label='Session Agent Delete']//div[@class='oj-button-label']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]")).click();
        Thread.sleep(2000);
        WebElement initialAdd = driver.findElement(By.xpath("//div[@id='session-agent1_emptyDataIcon']"));
        if (initialAdd.isDisplayed()) {
            System.out.println("Session agent deleted successfully");
        } else {
            System.out.println("Error in deleting the session agent");
        }
        return true;
    }

    public boolean deleteAllSessionAgent(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement delete = driver.findElement(By.id(idDeleteAll));
        if (delete.isDisplayed()) {
            delete.click();
            Thread.sleep(3000);
            driver.findElement(By.xpath(xpathConfirm)).click();
            Thread.sleep(2000);
            System.out.println("All SessionAgents Deleted Successfully ");
            return true;
        } else {
            System.out.println("No SessionAgents to be deleted");
            return false;
        }
    }

    public boolean cleanup(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='media-profile']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[normalize-space()='session-agent']")).click();
        Thread.sleep(2000);
        if (driver.findElement(By.xpath(xpathIntialAdd)).isDisplayed()) {
            System.out.println("Session Agent CleanUp Done");
            Thread.sleep(2000);
        } else {
            boolean deleteAll = deleteAllSessionAgent(driver);
            assert deleteAll;
            Thread.sleep(2000);
            System.out.println("Session Agent CleanUp Done");
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

    public boolean addMultipleSession(WebDriver driver, String hostName, String iP, String realM, int n) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        driver.findElement(By.xpath("//li[@id='leftNav_session-agent']//a[@title='session-agent']")).click();
        Thread.sleep(2000);
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                driver.findElement(By.xpath(xpathIntialAdd)).click();
            } else {
                driver.findElement(By.xpath("//span[@id='add_session-agent']")).click();
                Thread.sleep(2000);
            }
            WebElement name = driver.findElement(By.id("hostname|input"));
            name.sendKeys(hostName + i);
            WebElement iPAddress = driver.findElement(By.id("ip-address|input"));
            iPAddress.sendKeys(" " + iP);
            WebElement realMId = driver.findElement(By.id("realm-id|input"));
            realMId.sendKeys(realM);
            realMId.sendKeys(Keys.ENTER);
            Thread.sleep(2000);
            WebElement rfc2833Mode = driver.findElement(By.xpath(xpathrfc2833Mode));
            rfc2833Mode.click();
            Thread.sleep(2000);
            WebElement dualRadio = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/oj-list-view[1]/ul[1]/li[1]"));
            dualRadio.click();
            Thread.sleep(2000);
            WebElement referCallTransfer = driver.findElement(By.xpath(xpathReferCallTransfer));
            referCallTransfer.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[normalize-space()='dynamic']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(xpathOkButton)).click();
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + hostName + i + "')]"));
            if (element != null) {
                System.out.println(hostName + i + " added successfully");
            } else {
                System.out.println("Error in Adding " + hostName + i);
                return false;
            }
        }
        return true;
    }

    public boolean deleteSelective(WebDriver driver, String hostName, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        for (int i = 0; i <= n; i++) {
            String radioButton = "//oj-selector[@id='session-agent1_table_selector_" + hostName + i + "']//input[@type='checkbox']";
            driver.findElement(By.xpath(radioButton)).click();
            driver.findElement(By.xpath("//button[@aria-label='Session Agent Delete']//div[@class='oj-button-label']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]")).click();
            Thread.sleep(2000);
            String xpathSession = "//td[contains(text(),'" + hostName + i + "')]";
            Thread.sleep(3000);
            List<WebElement> sessionAgent = driver.findElements(By.xpath(xpathSession));
            if (!sessionAgent.isEmpty()) {
                System.out.println("Error: HostName " + sessionAgent + i + " still exists after deletion.");
                return false;
            }
        }
        System.out.println("All selected hostnames deleted successfully.");
        return true;
    }

    public boolean copyAndSaveSessionAgent(WebDriver driver, String hostName, String newHostName) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement button = driver.findElement(By.xpath("//oj-selector[@id='session-agent1_table_selector_" + hostName + "']//input[@type='checkbox']"));
        button.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@id='session-agent1_copyAction']")).click();
        WebElement name = driver.findElement(By.id("hostname|input"));
        name.clear();
        name.sendKeys(newHostName);
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathOkButton)).click();
        Thread.sleep(2000);
        String xpathNewHostName = "//oj-selector[@id='session-agent1_table_selector_" + newHostName + "']//input[@type='checkbox']";
        Thread.sleep(2000);
        WebElement newHost = driver.findElement(By.xpath(xpathNewHostName));
        if (newHost != null) {
            System.out.println("NewName '" + newHostName + "' copied and saved successfully.");
            return true;
        } else {
            System.out.println("Error: SIP '" + newHostName + "' not found after copying and saving.");
            return false;
        }
    }

    public boolean enterSessionAgentAndPressBack(WebDriver driver, String hostName) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        driver.findElement(By.xpath("//li[@id='leftNav_session-agent']//a[@title='session-agent']")).click();
        driver.findElement(By.xpath(xpathIntialAdd)).click();
        Thread.sleep(2000);
        WebElement name = driver.findElement(By.id("hostname|input"));
        name.sendKeys(hostName);
        driver.findElement(By.xpath("//span[contains(text(),'Back')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[@class='oj-button-text']")).click();
        Thread.sleep(2000);
        WebElement add = driver.findElement(By.xpath(xpathIntialAdd));
        if (add != null) {
            System.out.println("HostName Not Added");
            return true;
        } else {
            System.out.println("HostName is Added");
            return false;
        }
    }

    public boolean searchSessionAgent(WebDriver driver, String hostName) throws InterruptedException {
        WebElement search = driver.findElement(By.id("searchid|input"));
        search.sendKeys(hostName);
        Thread.sleep(2000);
        driver.findElement(By.id("tableSearchButton")).click();
        WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='session-agent1_emptyDataIcons']"));
        if (emptyDataIcons.isDisplayed()) {
            System.out.println("SessionAgent '" + hostName + "' is Not Available");
            return false;
        } else {
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + hostName + "')]"));
            if (element != null) {
                System.out.println("sessionAgent" + hostName + " " + "is found successfully");
                return true;
            } else {
                System.out.println("sessionAgent" + hostName + " " + "is not found ");
                return false;
            }

        }
    }

    public void sessionAgentWithEmptyName(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        driver.findElement(By.xpath("//li[@id='leftNav_session-agent']//a[@title='session-agent']")).click();
        driver.findElement(By.xpath(xpathIntialAdd)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathOkButton)).click();
        Thread.sleep(2000);
        WebElement error = driver.findElement(By.id("error_hostname"));
        if (error != null) {
            System.out.println("Red Button Is Displayed");
        } else {
            System.out.println("Agent is Added");
        }
    }
}

