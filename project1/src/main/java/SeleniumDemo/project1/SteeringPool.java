package SeleniumDemo.project1;
import java.time.Duration;
import java.util.List;

import com.sun.source.tree.YieldTree;
import org.openqa.selenium.*;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SteeringPool extends Base {
	 String xpathConfiguration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
	    String xpathRealM = "//a[contains(text(),'realm-config')]";
	    String xpathSteeringPool = "//span[normalize-space()='steering-pool']";
	    String xpathInitalAdd = "//span[@id='add_steering-pool_span']";
	    String xpathOkButton = "//oj-button[@id='apply']//span[@class='oj-button-text']";
	    String idDeleteAll = "deleteAll_steering-pool_buttonText";
	    String xpathConfirm = "//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]";
public void configuration(WebDriver driver) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        driver.findElement(By.xpath(xpathConfiguration)).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(xpathRealM)).click();
	        Thread.sleep(2000);
	    }
public boolean addSteeringPool(WebDriver driver, String ip, String realMName) throws InterruptedException {
	        driver.findElement(By.xpath(xpathSteeringPool)).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(xpathInitalAdd)).click();
	        WebElement nameIP = driver.findElement(By.id("ip-address|input"));
	        nameIP.sendKeys("" + ip);
	        Thread.sleep(2000);
	        WebElement realMId = driver.findElement(By.xpath("//div[@id='oj-combobox-choice-realm-id']//a[@role='presentation']"));
	        realMId.click();
	        WebElement realMIdInput = driver.findElement(By.xpath("//input[@id='realm-id|input']"));
	        realMIdInput.sendKeys(realMName);
	        realMIdInput.click();
	        Thread.sleep(2000);
	        WebElement okButton = driver.findElement(By.xpath(xpathOkButton));
	        okButton.click();
	        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + ip + "')]"));
	        if (element != null) {
	            System.out.println(ip + " " + " added Successfully");
	            return true;
	        } else {
	            System.out.println("Error in Adding" + ip + " ");
	            return false;
	        }
	    }
public boolean editSteeringPool(WebDriver driver, String iP, String newIP, String realMName) throws InterruptedException {
	        String checkBoxPath = "//oj-selector[@id='steering-pool1_table_selector_" + iP + "=" + 0 + "+" + realMName + "']//input[@type='checkbox']";
	        WebElement checkBox = driver.findElement(By.xpath(checkBoxPath));
	        checkBox.click();
	        Thread.sleep(2000);
	        WebElement editAction = driver.findElement(By.id("steering-pool1_editAction"));
	        editAction.click();
	        WebElement nameIP = driver.findElement(By.id("ip-address|input"));
	        nameIP.clear();
	        nameIP.sendKeys(newIP);
	        WebElement okButton = driver.findElement(By.xpath(xpathOkButton));
	        okButton.click();
	        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + newIP + "')]"));
	        if (element1 != null) {
	            System.out.println(newIP + " edited successfully");
	            return true;
	        } else {
	            System.out.println("Error in Editing " + newIP + " ");
	            return false;
	        }
	    }
public void cleanUp(WebDriver driver) {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        try {
	            driver.findElement(By.xpath("//span[normalize-space()='realm-config']")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//span[normalize-space()='steering-pool']")).click();
	            Thread.sleep(2000);
	            if (driver.findElement(By.xpath(xpathInitalAdd)).isDisplayed()) {
	                System.out.println("CleanUp Done");
	                Thread.sleep(2000);
	            } else {
	                boolean deleteAll = deleteAllSteeringPool(driver);
	                assert deleteAll;
	                Thread.sleep(2000);
	                System.out.println("Cleanup Done.");
	            }
	        } catch (InterruptedException e) {
	            System.out.println(e);
	        }
	    }
public boolean deleteAllSteeringPool(WebDriver driver) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        WebElement delete = driver.findElement(By.id(idDeleteAll));
	        Thread.sleep(3000);
	        if (delete.isDisplayed()) {
	            delete.click();
	            Thread.sleep(3000);
	            driver.findElement(By.xpath(xpathConfirm)).click();
	            Thread.sleep(2000);
	            System.out.println("All Ips Deleted Successfully ");
	            return true;
	        } else {
	            System.out.println("No Ips to be deleted");
	            return false;
	        }
	    }
public boolean deleteSteeringPool(WebDriver driver, String iP, String realMName) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        String checkBoxPath = "//oj-selector[@id='steering-pool1_table_selector_" + iP + "=" + 0 + "+" + realMName + "']//input[@type='checkbox']";
	        WebElement checkBox = driver.findElement(By.xpath(checkBoxPath));
	        checkBox.click();
	        Thread.sleep(2000);
	        WebElement delete = driver.findElement(By.id("steering-pool1_deleteAction"));
	        delete.click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]")).click();
	        Thread.sleep(2000);
	        return true;
	    }
public boolean addMultipleSteeringPool(WebDriver driver, String[] IPs, String realM) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        driver.findElement(By.xpath(xpathSteeringPool)).click();
	        Thread.sleep(2000);
	        for (int i = 0; i < IPs.length; i++) {
	            if (i == 0) {
	                driver.findElement(By.xpath(xpathInitalAdd)).click();
	            } else {
	                driver.findElement(By.id("add_steering-pool")).click();
	            }
	            WebElement nameIP = driver.findElement(By.id("ip-address|input"));
	            nameIP.sendKeys("" + IPs[i]);
	            Thread.sleep(2000);
	            WebElement realMId = driver.findElement(By.xpath("//div[@id='oj-combobox-choice-realm-id']//a[@role='presentation']"));
	            realMId.click();
	            WebElement realMIdInput = driver.findElement(By.xpath("//input[@id='realm-id|input']"));
	            realMIdInput.sendKeys(realM);
	            realMIdInput.click();
	            Thread.sleep(2000);
	            WebElement okButton = driver.findElement(By.xpath(xpathOkButton));
	            okButton.click();
	            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + IPs[i] + "')]"));
	            if (element != null) {
	                System.out.println(IPs[i] + " added successfully");
	            } else {
	                System.out.println("Error in Adding " + IPs[i]);
	                return false;
	            }
	        }
	        return true;
	    }
public boolean deleteSelective(WebDriver driver, String[] IPs, String realM) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        for (int i = 0; i < IPs.length; i++) {
	            String checkBoxPath = "//oj-selector[@id='steering-pool1_table_selector_" + IPs[i] + "=" + 0 + "+" + realM + "']//input[@type='checkbox']";
	            driver.findElement(By.xpath(checkBoxPath)).click();
	            Thread.sleep(2000);
	            WebElement delete = driver.findElement(By.id("steering-pool1_deleteAction"));
	            delete.click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]")).click();
	            Thread.sleep(2000);
	            String xpathSteeringPoolIP = "//td[contains(text(),'" + IPs[i] + "')]";
	            List<WebElement> iP = driver.findElements(By.xpath(xpathSteeringPoolIP));
	            if (!iP.isEmpty()) {
	                System.out.println("Error: IP " + IPs[i] + " still exists after deletion.");
	                return false;
	            } else {
	                System.out.println(" IP" + IPs[i] + " still exists after deletion.");
	                return true;
	            }
	        }
	        System.out.println("All the selected Ips are deleted");
	        return true;
	    }
public boolean enterSteeringPoolAndPressBack(WebDriver driver,String IP,String Realm) throws InterruptedException {
	        driver.findElement(By.xpath(xpathSteeringPool)).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(xpathInitalAdd)).click();
	        WebElement nameIP = driver.findElement(By.id("ip-address|input"));
	        nameIP.sendKeys("" + IP);
	        Thread.sleep(2000);
	        WebElement realMId = driver.findElement(By.xpath("//div[@id='oj-combobox-choice-realm-id']//a[@role='presentation']"));
	        realMId.click();
	        WebElement realMIdInput = driver.findElement(By.xpath("//input[@id='realm-id|input']"));
	        realMIdInput.sendKeys(Realm);
	        realMIdInput.click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//span[contains(text(),'Back')]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[@class='oj-button-text']")).click();
	        Thread.sleep(2000);
	        WebElement add = driver.findElement(By.xpath(xpathInitalAdd));
	        if(add!=null){
	            System.out.println("IP Not Added");
	            return true;
	        }
	        else {
	            System.out.println("IP is Added");
	            return false;
	        }
	    }
public void steeringPoolWithEmptyName(WebDriver driver){
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        driver.findElement(By.xpath(xpathSteeringPool)).click();
	        driver.findElement(By.xpath(xpathInitalAdd)).click();
	        driver.findElement(By.xpath("//span[@data-bind='text: label'][normalize-space()='OK']")).click();
	        WebElement error = driver.findElement(By.id("error_ip-address"));
	        if (error != null) {
	            System.out.println("Red Button Displayed");
	        } else {
	            System.out.println("Red Button Not Displayed");
	        }
	    }
public boolean searchSteeringPoolIp (WebDriver driver,String IP) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        WebElement search = driver.findElement(By.xpath("//input[@id='searchid|input']"));
	        search.sendKeys(IP);
	        Thread.sleep(2000);
	        WebElement searchButton = driver.findElement(By.id("tableSearchButton"));
	        searchButton.click();
	        Thread.sleep(2000);
	        WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='steering-pool1_emptyDataIcons']"));
	        if (emptyDataIcons.isDisplayed()) {
	            System.out.println("IP '" + IP + "' is Not Available");
	            return false;
	        }
	        else{
	            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + IP + "')]"));
	            if (element != null) {
	                System.out.println(IP + " " + "is found successfully");
	                return true;
	            } else {
	                System.out.println(IP + " " + "is not found ");
	                return false;
	            }
	        }
	        }
public boolean copyAndSaveIPWithNewIP(WebDriver driver ,String IP,String newIP,String realMName) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        String checkBoxPath = "//oj-selector[@id='steering-pool1_table_selector_" + IP + "=" + 0 + "+" + realMName + "']//input[@type='checkbox']";
	        WebElement checkBox = driver.findElement(By.xpath(checkBoxPath));
	        checkBox.click();
	        Thread.sleep(2000);
	        WebElement copy = driver.findElement(By.id("steering-pool1_copyAction"));
	        copy.click();
	        WebElement nameIP = driver.findElement(By.id("ip-address|input"));
	        nameIP.clear();
	        nameIP.sendKeys("" + newIP);
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//oj-button[@id='apply']//span[@class='oj-button-text']")).click();
	        String xpathNewIP = "//oj-selector[@id='steering-pool1_table_selector_" + IP + "=" + 0 + "+" + realMName + "']//input[@type='checkbox']";
	        Thread.sleep(2000);
	        WebElement newIp = driver.findElement(By.xpath(xpathNewIP));
	        if (newIp!=null) {
	            System.out.println("NewIP '" + newIP + "' copied and saved successfully.");
	            return true;
	        } else {
	            System.out.println("Error: IP '" + newIP + "' not found after copying and saving.");
	            return false;
	        }
	    }
}










