package SeleniumDemo.project1;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;

public class SIPManipulation extends Base {
	 String xpathConfiguration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
	    String xpathSIPManipulation = "//a[contains(text(),'sip-manipulation')]";
	    String xpathInitialAdd = "//button[@aria-label='SIP Manipulation Add']//span[@class='oj-button-text']";
	    String xpathOkButton = "//oj-button[@id='apply']//div[@class='oj-button-label']";
	    String xpathConfirm = "//oj-button[@id='dialogConfirmBtn']//div[@class='oj-button-label']";
	    String idDeleteAll = "deleteAll_sip-manipulation_buttonText";

	    public void configuration(WebDriver driver) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        driver.findElement(By.xpath(xpathConfiguration)).click();
	        driver.findElement(By.xpath(xpathSIPManipulation)).click();
	    }

	    public boolean addSIP(WebDriver driver, String name) throws InterruptedException {
	        driver.findElement(By.xpath(xpathInitialAdd)).click();
	        WebElement nameSIP = driver.findElement(By.id("name|input"));
	        nameSIP.sendKeys(name);
	        nameSIP.sendKeys(Keys.ENTER);
	        driver.findElement(By.xpath(xpathOkButton)).click();
	        Thread.sleep(2000);
	        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + name + "')]"));
	        if (element != null) {
	            System.out.println("SIPName" + name + " " + " added Successfully");
	            return true;
	        } else {
	            System.out.println("Error in Adding" + "SIPName" + name + " ");
	            return false;
	        }
	    }

	    public boolean editSIP(WebDriver driver, String nameOfSIP, String newNameOfSIP) throws InterruptedException {
	        WebElement name = driver.findElement(By.xpath("//oj-selector[@id='sip-manipulation1_table_selector_" + nameOfSIP + "']//input[@type='checkbox']"));
	        name.click();
	        Thread.sleep(2000);
	        driver.findElement(By.id("sip-manipulation1_editAction")).click();
	        WebElement nameSIP = driver.findElement(By.id("name|input"));
	        nameSIP.clear();
	        nameSIP.sendKeys(newNameOfSIP);
	        nameSIP.sendKeys(Keys.ENTER);
	        driver.findElement(By.xpath(xpathOkButton)).click();
	        Thread.sleep(2000);
	        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + newNameOfSIP + "')]"));
	        if (element != null) {
	            System.out.println("SIPName" + newNameOfSIP + " " + " edited Successfully");
	            return true;
	        } else {
	            System.out.println("Error in Adding" + "SIPName" + newNameOfSIP + " ");
	            return false;
	        }
	    }

	    public boolean deleteSIP(WebDriver driver, String nameOfSIP) throws InterruptedException {
	        WebElement name = driver.findElement(By.xpath("//oj-selector[@id='sip-manipulation1_table_selector_" + nameOfSIP + "']//input[@type='checkbox']"));
	        name.click();
	        Thread.sleep(2000);
	        WebElement delete = driver.findElement(By.id("sip-manipulation1_deleteAction"));
	        delete.click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(xpathConfirm)).click();
	        Thread.sleep(2000);
	        return true;
	    }

	    public boolean deleteAllSIP(WebDriver driver) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        WebElement delete = driver.findElement(By.id(idDeleteAll));
	        Thread.sleep(2000);
	        if (delete.isDisplayed()) {
	            delete.click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath(xpathConfirm)).click();
	            Thread.sleep(2000);
	            System.out.println("All SIP Deleted Successfully");
	            return true;
	        } else {
	            System.out.println("No SIP to be deleted");
	            return false;
	        }
	    }

	    public boolean cleanUP(WebDriver driver) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        try {
	            driver.findElement(By.xpath("//span[normalize-space()='sip-interface']")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//span[normalize-space()='sip-manipulation']")).click();
	            Thread.sleep(2000);
	            if (driver.findElement(By.xpath(xpathInitialAdd)).isDisplayed()) {
	                System.out.println("CleanUp Done");
	            } else {
	                boolean delete = deleteAllSIP(driver);
	                assert delete;
	                Thread.sleep(2000);
	                System.out.println("Cleanup Done.");
	            }
	        } catch (InterruptedException e) {
	            System.out.println(e);
	        }
	        return true;
	    }

	    public boolean addMultipleSIP(WebDriver driver, String nameOfSIP, int n) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        for (int i = 0; i <= n; i++) {
	            if (i == 0) {
	                driver.findElement(By.xpath(xpathInitialAdd)).click();
	            } else {
	                driver.findElement(By.id("add_sip-manipulation")).click();
	            }
	            WebElement nameSIP = driver.findElement(By.id("name|input"));
	            nameSIP.sendKeys(nameOfSIP + i);
	            nameSIP.sendKeys(Keys.ENTER);
	            driver.findElement(By.xpath(xpathOkButton)).click();
	            Thread.sleep(2000);
	            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + nameOfSIP + i + "')]"));
	            if (element != null) {
	                System.out.println(nameOfSIP + i + " added successfully");
	            } else {
	                System.out.println("Error in Adding " + nameOfSIP + i);
	                return false;
	            }
	        }
	        return true;
	    }

	    public boolean deleteSelective(WebDriver driver, String nameOfSIP, int n) throws InterruptedException {
	        for (int i = 0; i <= n; i++) {
	            WebElement name = driver.findElement(By.xpath("//oj-selector[@id='sip-manipulation1_table_selector_" + nameOfSIP + i + "']//input[@type='checkbox']"));
	            name.click();
	            WebElement delete = driver.findElement(By.id("sip-manipulation1_deleteAction"));
	            delete.click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath(xpathConfirm)).click();
	            Thread.sleep(2000);
	            String xpathSIPManipulation = "//td[contains(text(),'" + nameOfSIP + i + "')]";
	            List<WebElement> sIPName = driver.findElements(By.xpath(xpathSIPManipulation));
	            if (!sIPName.isEmpty()) {
	                System.out.println("Error: Name " + nameOfSIP + i + " still exists after deletion.");
	                return false;
	            }
	        }
	        System.out.println("All selected SIP names deleted successfully.");
	        return true;
	    }

	    public boolean copyAndSaveSIPManipulation(WebDriver driver, String nameOfSIP, String newNameOfSIP) throws InterruptedException {
	        WebElement name = driver.findElement(By.xpath("//oj-selector[@id='sip-manipulation1_table_selector_" + nameOfSIP + "']//input[@type='checkbox']"));
	        name.click();
	        Thread.sleep(2000);
	        driver.findElement(By.id("sip-manipulation1_copyAction")).click();
	        WebElement nameSIP = driver.findElement(By.id("name|input"));
	        nameSIP.clear();
	        nameSIP.sendKeys(newNameOfSIP);
	        nameSIP.sendKeys(Keys.ENTER);
	        driver.findElement(By.xpath(xpathOkButton)).click();
	        Thread.sleep(2000);
	        String xpathNewName = "//oj-selector[@id='sip-manipulation1_table_selector_" + newNameOfSIP + "']//input[@type='checkbox']";
	        WebElement copiedName = driver.findElement(By.xpath(xpathNewName));
	        if (copiedName != null) {
	            System.out.println("NewSIP '" + newNameOfSIP + "' is copied and saved successfully.");
	            return true;
	        } else {
	            System.out.println("Error: NewSIP '" + newNameOfSIP + "' not found after copying and saving.");
	            return false;
	        }
	    }
	    
	    public boolean searchSIPManipulation(WebDriver driver, String nameOfSIP) throws InterruptedException {
	        WebElement search = driver.findElement(By.id("searchid|input"));
	        search.sendKeys(nameOfSIP);
	        Thread.sleep(2000);
	        driver.findElement(By.id("tableSearchButton")).click();
	        WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='sip-manipulation1_emptyDataIcons']"));
	        if (emptyDataIcons.isDisplayed()) {
	            System.out.println(" SIP '" + nameOfSIP + "' is Not Available");
	            return false;
	        } else {
	            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + nameOfSIP + "')]"));
	            if (element != null) {
	                System.out.println(nameOfSIP + " " + "is found successfully");
	                return true;
	            } else {
	                System.out.println(nameOfSIP + " " + "is not found ");
	                return false;
	            }
	        }
	    }

	    public boolean enterSIPManipulationAndPressBack(WebDriver driver, String nameOfSIP) throws InterruptedException {
	        driver.findElement(By.xpath(xpathInitialAdd)).click();
	        WebElement nameSIP = driver.findElement(By.id("name|input"));
	        nameSIP.sendKeys(nameOfSIP);
	        nameSIP.sendKeys(Keys.ENTER);
	        driver.findElement(By.xpath("//span[contains(text(),'Back')]")).click();
	        Thread.sleep(2000);
	        String yesButton = "//oj-button[@id='dialogYesBtn']//span[contains(text(),'Yes')]";
	        driver.findElement(By.xpath(yesButton)).click();
	        Thread.sleep(2000);
	        WebElement add = driver.findElement(By.xpath(xpathInitialAdd));
	        if (add != null) {
	            System.out.println("SIP Name Not Added");
	            return true;
	        } else {
	            System.out.println("SIP Name is Added");
	            return false;
	        }
	    }
	    public void sIPWithEmptyName(WebDriver driver){
	        driver.findElement(By.xpath(xpathInitialAdd)).click();
	        driver.findElement(By.xpath(xpathOkButton)).click();
	        String xpathHeader = "//span[@class='mainHeader']";
	        if (driver.findElement(By.xpath(xpathHeader)).isDisplayed()) {
	            System.out.println("Error in adding SIP Name");
	        }
	        else{
	            System.out.println("SIP Name Added Successfully");
	        }
	    }
	}







