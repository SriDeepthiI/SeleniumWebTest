package SeleniumDemo.project1;
import java.time.Duration;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.*;

public class SessionRecordingServer extends Base {
	String xpathConfiguration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
    String xpathRealM = "//a[contains(text(),'realm-config')]";
    String xpathInitialAdd = "//button[@aria-label='Session Recording Server Add']//span[@class='oj-button-text']";
    String xpathSwitchAdvanced = "//oj-switch[@id='form_advanced_switch']//div[@class='oj-switch-track']";
    String xpathOKButton = "//oj-button[@id='apply']//span[@class='oj-button-text']";
    String xpathConfirm = "//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]";

    public void configuration(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathConfiguration)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathRealM)).click();
    }

    public boolean addSessionRecordingServer(WebDriver driver, String nameOfSRS, String RealM, String Destination) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[normalize-space()='session-recording-server']")).click();
        Thread.sleep(2000);
        WebElement initialAdd = driver.findElement(By.xpath(xpathInitialAdd));
        initialAdd.click();
        WebElement switchAdvanced = driver.findElement(By.xpath(xpathSwitchAdvanced));
        switchAdvanced.click();
        WebElement name = driver.findElement(By.id("name|input"));
        name.sendKeys(nameOfSRS);
        name.sendKeys(Keys.ENTER);
        WebElement choiceRealM = driver.findElement(By.xpath("//input[@id='realm|input']"));
        choiceRealM.sendKeys(RealM);
        choiceRealM.sendKeys(Keys.ENTER);
        WebElement destination = driver.findElement(By.xpath("//input[@id='destination|input']"));
        destination.sendKeys(" " + Destination);
        destination.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath(xpathOKButton)).click();
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + nameOfSRS + "')]"));
        if (element != null) {
            System.out.println(nameOfSRS + " " + "SessionRecording " + " added Successfully");
            return true;
        } else {
            System.out.println("Error in Adding" + nameOfSRS + "SessionRecording ");
            return false;
        }
    }

    public boolean editSessionRecording(WebDriver driver, String nameOfSRS, String newNameOfSRS) throws InterruptedException {
        WebElement radioButton = driver.findElement(By.xpath("//oj-selector[@id='session-recording-server1_table_selector_" + nameOfSRS + "']//input[@type='checkbox']"));
        radioButton.click();
        driver.findElement(By.id("session-recording-server1_editAction")).click();
        Thread.sleep(2000);
        WebElement name = driver.findElement(By.id("name|input"));
        name.clear();
        name.sendKeys(newNameOfSRS);
        name.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath(xpathOKButton)).click();
        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + newNameOfSRS + "')]"));
        if (element1 == null) {
            System.out.println("Error in Editing" + newNameOfSRS + " ");
            return false;
        } else {
            System.out.println(newNameOfSRS + " " + "Sessionrecording " + " edited Successfully");
            return true;
        }
    }

    public boolean deleteSRS(WebDriver driver, String nameOfSRS) throws InterruptedException {
        WebElement radioButton = driver.findElement(By.xpath("//oj-selector[@id='session-recording-server1_table_selector_" + nameOfSRS + "']//input[@type='checkbox']"));
        radioButton.click();
        driver.findElement(By.id("session-recording-server1_deleteAction")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]")).click();
        Thread.sleep(2000);
        return true;
    }

    public boolean cleanUp(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        try {
            driver.findElement(By.xpath("//span[normalize-space()='session-recording-group']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[normalize-space()='session-recording-server']")).click();
            Thread.sleep(2000);
            if (driver.findElement(By.xpath(xpathInitialAdd)).isDisplayed()) {
                System.out.println("SRS CleanUp Done");
                Thread.sleep(2000);
            } else {
                boolean deleteAll = deleteAllSRS(driver);
                assert deleteAll;
                Thread.sleep(2000);
                System.out.println("SRS CleanUp Done");
            }
            driver.findElement(By.xpath("//span[normalize-space()='realm-config']")).click();
            Thread.sleep(2000);
            if (driver.findElement(By.xpath("//button[@aria-label='Realm Config Add']//span[@class='oj-button-text']")).isDisplayed()) {
                System.out.println("RealM CleanUp Done ");
            } else {
                driver.findElement(By.xpath("//span[@id='deleteAll_realm-config_buttonText']")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]")).click();
                Thread.sleep(2000);
                System.out.println("RealM CleanUp Done");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return true;
    }

    public boolean deleteAllSRS(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement delete = driver.findElement(By.id("deleteAll_session-recording-server_buttonText"));
        Thread.sleep(2000);
        if (delete.isDisplayed()) {
            delete.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(xpathConfirm)).click();
            Thread.sleep(2000);
            System.out.println("All SRS Deleted Successfully ");
            return true;
        } else {
            System.out.println("No SRS to be deleted");
            return false;
        }
    }

    public boolean addMultipleSRS(WebDriver driver, String nameOfSRS, String RealM, String Destination, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[normalize-space()='session-recording-server']")).click();
        Thread.sleep(2000);
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                driver.findElement(By.xpath(xpathInitialAdd)).click();
                WebElement switchAdvanced = driver.findElement(By.xpath(xpathSwitchAdvanced));
                switchAdvanced.click();
            } else {
                driver.findElement(By.xpath("//span[@id='add_session-recording-server']")).click();
            }
            WebElement name = driver.findElement(By.id("name|input"));
            name.sendKeys(nameOfSRS + i);
            name.sendKeys(Keys.ENTER);
            WebElement choiceRealM = driver.findElement(By.xpath("//input[@id='realm|input']"));
            choiceRealM.sendKeys(RealM);
            choiceRealM.sendKeys(Keys.ENTER);
            WebElement destination = driver.findElement(By.xpath("//input[@id='destination|input']"));
            destination.sendKeys(" " + Destination);
            destination.sendKeys(Keys.ENTER);
            driver.findElement(By.xpath(xpathOKButton)).click();
            Thread.sleep(2000);
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + nameOfSRS + i + "')]"));
            Thread.sleep(2000);
            if (element.isDisplayed()) {
                System.out.println("Session Recorder" + " " + nameOfSRS + i + " added successfully");
            } else {
                System.out.println("Error in Adding " + "SessionRecording" + " " + nameOfSRS + i);
                return false;
            }
        }
        return true;
    }

    public boolean deleteSelective(WebDriver driver, String nameOfSRS, int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        for (int i = 0; i <= n; i++) {
            WebElement radioButton = driver.findElement(By.xpath("//oj-selector[@id='session-recording-server1_table_selector_" + nameOfSRS + i + "']//input[@type='checkbox']"));
            radioButton.click();
            Thread.sleep(2000);
            driver.findElement(By.id("session-recording-server1_deleteAction")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]")).click();
            Thread.sleep(2000);
            String xpathIdentifier = "//td[contains(text(),'" + nameOfSRS + i + "')]";
            Thread.sleep(3000);
            List<WebElement> SIPs = driver.findElements(By.xpath(xpathIdentifier));
            Thread.sleep(3000);
            if (!SIPs.isEmpty()) {
                System.out.println("Error: SIP " + nameOfSRS + i + " still exists after deletion.");
                return false;
            }
        }
        System.out.println("All selected SRS deleted successfully.");
        return true;
    }

    public boolean searchSRS(WebDriver driver, String nameOfSRS) throws InterruptedException {
        WebElement search = driver.findElement(By.id("searchid|input"));
        search.sendKeys(nameOfSRS);
        Thread.sleep(2000);
        driver.findElement(By.id("tableSearchButton")).click();
        Thread.sleep(2000);
        WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='session-recording-server1_emptyDataIcons']"));
        if (emptyDataIcons.isDisplayed()) {
            System.out.println("SessionRecorder '" + " " + nameOfSRS + "' is Not Available");
            return false;
        } else {
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + nameOfSRS + "')]"));
            if (element != null) {
                System.out.println("SessionRecorder" + " " + nameOfSRS + " " + "is found successfully");
                return true;
            } else {
                System.out.println("SessionRecorder" + " " + nameOfSRS + " " + "is not found ");
                return false;
            }

        }
    }

    public boolean copyAndSaveSRS(WebDriver driver, String nameOfSRS, String newNameOfSRS) throws InterruptedException {
        WebElement radioButton = driver.findElement(By.xpath("//oj-selector[@id='session-recording-server1_table_selector_" + nameOfSRS + "']//input[@type='checkbox']"));
        radioButton.click();
        Thread.sleep(2000);
        driver.findElement(By.id("session-recording-server1_copyAction")).click();
        Thread.sleep(2000);
        WebElement name = driver.findElement(By.id("name|input"));
        name.clear();
        name.sendKeys(newNameOfSRS);
        name.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath(xpathOKButton)).click();
        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + newNameOfSRS + "')]"));
        if (element1 == null) {
            System.out.println("Error in Copying" + newNameOfSRS + " ");
            return false;
        } else {
            System.out.println(newNameOfSRS + " " + "Sessionrecording " + " copied Successfully");
            return true;
        }
    }

    public boolean enterSRSAndPressBack(WebDriver driver, String nameOfSRS, String RealM, String Destination) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[normalize-space()='session-recording-server']")).click();
        Thread.sleep(2000);
        WebElement initialAdd = driver.findElement(By.xpath(xpathInitialAdd));
        initialAdd.click();
        WebElement switchAdvanced = driver.findElement(By.xpath(xpathSwitchAdvanced));
        switchAdvanced.click();
        WebElement name = driver.findElement(By.id("name|input"));
        name.sendKeys(nameOfSRS);
        name.sendKeys(Keys.ENTER);
        WebElement choiceRealM = driver.findElement(By.xpath("//input[@id='realm|input']"));
        choiceRealM.sendKeys(RealM);
        choiceRealM.sendKeys(Keys.ENTER);
        WebElement destination = driver.findElement(By.xpath("//input[@id='destination|input']"));
        destination.sendKeys(" " + Destination);
        destination.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//button[@aria-label='Back']//div[@class='oj-button-label']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[contains(text(),'Yes')]")).click();
        Thread.sleep(2000);
        WebElement add = driver.findElement(By.xpath(xpathInitialAdd));
        if (add != null) {
            System.out.println("SRS Not Added");
            return true;
        } else {
            System.out.println("SRS is Added");
            return false;
        }
    }

    public void sRSWithEmptyName(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='session-router']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[normalize-space()='session-recording-server']")).click();
        Thread.sleep(2000);
        WebElement initialAdd = driver.findElement(By.xpath(xpathInitialAdd));
        initialAdd.click();
        driver.findElement(By.xpath(xpathOKButton)).click();
        Thread.sleep(2000);
        WebElement error = driver.findElement(By.xpath("//span[@id='error_name']"));
        if (error != null) {
            System.out.println("RedButton Displayed");
            System.out.println("SRS could not be Added");
        } else {
            System.out.println("RedButton Is not Displayed");
            System.out.println("SRS is Added");
        }
    }
}




	