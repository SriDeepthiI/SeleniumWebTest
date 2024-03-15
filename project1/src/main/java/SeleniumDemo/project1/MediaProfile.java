package SeleniumDemo.project1;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediaProfile extends Base {
	    String xpathConfiguration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
	    String xpathMediaProfile = "//a[contains(text(),'media-profile')]";
	    String xpathInitialAdd = "//button[@aria-label='Media Profile Add']//span[@class='oj-button-text']";

	    String xpathOkButton = "//span[@data-bind='text: label'][normalize-space()='OK']";
	    String idDeleteAll = "deleteAll_media-profile_buttonText";
	    String xpathConfirmButton = "//oj-button[@id='dialogConfirmBtn']//span[@class='oj-button-text']";

	    public void configuration(WebDriver driver) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        driver.findElement(By.xpath(xpathConfiguration)).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(xpathMediaProfile)).click();
	        Thread.sleep(2000);
	    }

	    public boolean addMediaProfile(WebDriver driver, String mediaName, String subName, String payLoadType) throws InterruptedException {
	        driver.findElement(By.xpath(xpathInitialAdd)).click();
	        WebElement name = driver.findElement(By.xpath("//input[@id='name|input']"));
	        name.sendKeys(mediaName);
	        WebElement subNamePath = driver.findElement(By.id("subname|input"));
	        subNamePath.sendKeys(subName);
	        WebElement payLoad = driver.findElement(By.xpath("//input[@id='payload-type|input']"));
	        payLoad.sendKeys(payLoadType);
	        WebElement okButton = driver.findElement(By.xpath(xpathOkButton));
	        okButton.click();
	        Thread.sleep(2000);
	        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + mediaName + "')]"));
	        if (element1 != null) {
	            System.out.println(mediaName + " " + " added Successfully");
	            return true;
	        } else {
	            System.out.println("Error in Adding" + mediaName + " ");
	            return false;
	        }
	    }

	    public boolean editMedia(WebDriver driver, String mediaName, String new_MediaName, String subName, String new_SubName) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        String mediaNameUpper = mediaName.toUpperCase();
	        String subNameUpper = subName.toUpperCase();
	        String checkBoxPath = "//oj-selector[@id='media-profile1_table_selector_" + mediaNameUpper + "::" + subNameUpper + "']//input[@type='checkbox']";
	        WebElement checkBox = driver.findElement(By.xpath(checkBoxPath));
	        checkBox.click();
	        Thread.sleep(2000);
	        driver.findElement(By.id("media-profile1_editAction")).click();
	        Thread.sleep(3000);
	        WebElement mediaNameField = driver.findElement(By.id("name|input"));
	        mediaNameField.clear();
	        mediaNameField.sendKeys(new_MediaName);
	        Thread.sleep(3000);
	        WebElement subNameField = driver.findElement(By.id("subname|input"));
	        subNameField.clear();
	        subNameField.sendKeys(new_SubName);
	        driver.findElement(By.xpath(xpathOkButton)).click();
	        Thread.sleep(3000);
	        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + new_MediaName + "')]"));
	        if (element1 != null) {
	            System.out.println(new_MediaName + " edited successfully");
	            return true;
	        } else {
	            System.out.println("Error in Editing " + new_MediaName + " ");
	            return false;
	        }
	    }

	    public boolean deleteMedia(WebDriver driver, String mediaName, String subName) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        WebElement checkBox = driver.findElement(By.xpath("//oj-selector[@id='media-profile1_table_selector_" + mediaName.toUpperCase() + "::" + subName.toUpperCase() + "']//input[@type='checkbox']"));
	        checkBox.click();
	        Thread.sleep(3000);
	        WebElement delete = driver.findElement(By.id("media-profile1_deleteAction"));
	        delete.click();
	        Thread.sleep(2000);
	        WebElement confirm = driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]"));
	        confirm.click();
	        Thread.sleep(2000);
	        return true;
	    }

	    public boolean deleteAllMediaProfile(WebDriver driver) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        WebElement delete = driver.findElement(By.id(idDeleteAll));
	        Thread.sleep(2000);
	        if (delete.isDisplayed()) {
	            delete.click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath(xpathConfirmButton)).click();
	            Thread.sleep(2000);
	            System.out.println("All MediaProfile Deleted Successfully");
	            return true;
	        } else {
	            System.out.println("No MediaProfile to be deleted");
	            return false;
	        }
	    }

	    public void cleanUp(WebDriver driver) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        try {
	            driver.findElement(By.xpath("//span[normalize-space()='local-routing-config']")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//span[normalize-space()='media-profile']")).click();
	            Thread.sleep(2000);
	            if (driver.findElement(By.xpath(xpathInitialAdd)).isDisplayed()) {
	                System.out.println("CleanUp Done");
	            } else {
	                boolean delete = deleteAllMediaProfile(driver);
	                assert delete;
	                Thread.sleep(2000);
	                System.out.println("Cleanup Done.");
	            }
	        } catch (InterruptedException e) {
	            System.out.println(e);
	        }
	    }

	    public boolean addMultipleMediaProfiles(WebDriver driver, String mediaName, String payLoadType, int n) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        for (int i = 0; i <= n; i++) {
	            if (i == 0) {
	                driver.findElement(By.xpath(xpathInitialAdd)).click();
	            } else {
	                driver.findElement(By.id("add_media-profile")).click();
	            }
	            WebElement name = driver.findElement(By.xpath("//input[@id='name|input']"));
	            name.sendKeys(mediaName + i);
	            WebElement payLoad = driver.findElement(By.xpath("//input[@id='payload-type|input']"));
	            payLoad.sendKeys(payLoadType);
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOkButton))).click();
	            Thread.sleep(3000);
	            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + mediaName + i + "')]"));
	            if (element != null) {
	                System.out.println(mediaName + i + " added successfully");
	            } else {
	                System.out.println("Error in Adding " + mediaName + i);
	                return false;
	            }
	        }
	        return true;
	    }

	    public boolean deleteSelective(WebDriver driver, String mediaName, int n) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        for (int i = 0; i <= n; i++) {
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	            String mediaNameString = String.valueOf(mediaName);
	            String mediaNameUpper = mediaNameString.toUpperCase();
	            String checkBoxPath = "//oj-selector[@id='media-profile1_table_selector_" + mediaNameUpper + i + "']//input[@type='checkbox']";
	            WebElement checkBox = driver.findElement(By.xpath(checkBoxPath));
	            checkBox.click();
	            Thread.sleep(2000);
	            driver.findElement(By.id("media-profile1_deleteAction")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//div[@class='oj-button-label']")).click();
	            String xpathMediaProfile = "//td[contains(text(),'" + mediaName + i + "')]";
	            List<WebElement> mediaNames = driver.findElements(By.xpath(xpathMediaProfile));
	            if (!mediaNames.isEmpty()) {
	                System.out.println("Error: Name " + mediaName + i + " still exists after deletion.");
	                return false;
	            }
	        }
	        System.out.println("All selected MediaNames deleted successfully.");
	        return true;
	    }

	    public boolean copyAndSaveMediaProfile(WebDriver driver, String mediaName, String new_MediaName, String subName, String new_SubName) throws InterruptedException {
	        String mediaNameUpper = mediaName.toUpperCase();
	        String subNameUpper = subName.toUpperCase();
	        String checkBoxPath = "//oj-selector[@id='media-profile1_table_selector_" + mediaNameUpper + "::" + subNameUpper + "']//input[@type='checkbox']";
	        driver.findElement(By.xpath(checkBoxPath)).click();
	        Thread.sleep(2000);
	        WebElement copy = driver.findElement(By.id("media-profile1_copyAction"));
	        copy.click();
	        WebElement name = driver.findElement(By.xpath("//input[@id='name|input']"));
	        name.clear();
	        name.sendKeys(new_MediaName);
	        WebElement subNamePath = driver.findElement(By.id("subname|input"));
	        subNamePath.clear();
	        subNamePath.sendKeys(new_SubName);
	        WebElement okButton = driver.findElement(By.xpath(xpathOkButton));
	        okButton.click();
	        Thread.sleep(2000);
	        String radioButton = "//oj-selector[@id='media-profile1_table_selector_" + mediaNameUpper + "::" + subNameUpper + "']//input[@type='checkbox']";
	        List<WebElement> newMediaProfiles = driver.findElements(By.xpath(radioButton));
	        if (!newMediaProfiles.isEmpty()) {
	            WebElement newMediaProfile = newMediaProfiles.get(0); // Assuming you only want the first element
	            System.out.println("New Media Profile" + newMediaProfile.getText() + "copied and saved successfully.");
	            return true;
	        } else {
	            System.out.println("Error: Media Profile '" + radioButton + "' not found after copying and saving.");
	            return false;
	        }
	    }

	    public boolean searchMediaProfile(WebDriver driver, String mediaName) throws InterruptedException {
	        WebElement search = driver.findElement(By.id("searchid|input"));
	        search.sendKeys(mediaName);
	        WebElement tableSearch = driver.findElement(By.id("tableSearchButton"));
	        tableSearch.click();
	        Thread.sleep(2000);
	    WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='media-profile1_emptyDataIcons']"));
	        if (emptyDataIcons.isDisplayed()) {
	        System.out.println("Policy '" + mediaName + "' is Not Available");
	        return false;
	    } else {
	        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + mediaName + "')]"));
	        if (element != null) {
	            System.out.println(mediaName + " " + "is found successfully");
	            return true;
	        } else {
	            System.out.println(mediaName + " " + "is not found ");
	            return false;
	        }

	    }
	}

	public boolean enterMediaProfileAndPressBack(WebDriver driver, String mediaName) throws InterruptedException {
	        driver.findElement(By.xpath(xpathInitialAdd)).click();
	        WebElement name = driver.findElement(By.id("name|input"));
	        name.sendKeys(mediaName);
	        WebElement back = driver.findElement(By.xpath("//span[contains(text(),'Back')]"));
	        back.click();
	        Thread.sleep(2000);
	        WebElement yesButton = driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[contains(text(),'Yes')]"));
	        yesButton.click();
	        Thread.sleep(2000);
	        WebElement add = driver.findElement(By.xpath(xpathInitialAdd));
	        if(add!=null){
	            System.out.println("Policy Not Added");
	            return true;
	        }
	        else {
	            System.out.println("Policy is Added");
	            return false;
	        }
	    }
	    public void mediaProfileWithEmptyName(WebDriver driver) {
	        driver.findElement(By.xpath(xpathInitialAdd)).click();
	        driver.findElement(By.xpath(xpathOkButton)).click();
	        WebElement error = driver.findElement(By.id("error_name"));
	        if (error != null) {
	            System.out.println("Red Button Displayed");
	        } else {
	            System.out.println("Red Button Not Displayed");
	        }
	    }
	}











