package SeleniumDemo.project1;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CodecPolicy extends Base{
    String xpathConfiguration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
    String xpathCodecPolicy = "//a[contains(text(),'codec-policy')]";
    String xpathIntialAdd = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/oj-module[1]/div[1]/div[7]/oj-module[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[2]/oj-module[1]/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/div[2]/oj-button[1]/button[1]/div[1]/span[1]";
    String idDeleteAll = "deleteAll_codec-policy_buttonText";
    String xpathConfirmDelete = "//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]";
    String xpathOkButton ="//span[@data-bind='text: label'][normalize-space()='OK']";

    public void configuration(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathConfiguration)).click();
        driver.findElement(By.xpath(xpathCodecPolicy)).click();
    }
    public boolean addPolicy(WebDriver driver, String policyName, String codec1, String codec2) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath(xpathIntialAdd)).click();
        Thread.sleep(2000);
        WebElement name = driver.findElement(By.xpath("//input[@id='name|input']"));
        name.sendKeys(policyName);
        WebElement codecs = driver.findElement(By.xpath("//input[@id='allow-codecs|input']"));
        codecs.sendKeys(codec1);
        codecs.sendKeys(Keys.ENTER);
        codecs.sendKeys(codec2);
        codecs.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//span[@data-bind='text: label'][normalize-space()='OK']")).click();
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + policyName + "')]"));
        if (element != null) {
            System.out.println(policyName + " " +" added Successfully");
            return true;
        } else {
            System.out.println("Error in Adding" +policyName + " ");
            return false;
        }
   }
    public boolean editPolicy(WebDriver driver, String name, String new_Name) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//oj-selector[@id='codec-policy1_table_selector_" + name + "']//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//span[@id='codec-policy1_editAction']")).click();
        WebElement nameField = driver.findElement(By.xpath("//input[@id='name|input']"));
        nameField.clear();
        nameField.sendKeys(new_Name);
        driver.findElement(By.xpath("//span[@data-bind='text: label'][normalize-space()='OK']")).click();
        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + new_Name + "')]"));
        if (element1 == null) {
            System.out.println("Error in Editing" + new_Name + " ");
            return false;
        } else {
            System.out.println(new_Name + " " + "edited successfully");
            return true;
        }
    }
    public boolean deletePolicy(WebDriver driver, String policyName) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement checkBox = driver.findElement(By.xpath("//oj-selector[@id='codec-policy1_table_selector_" + policyName + "']//input[@type='checkbox']"));
        checkBox.click();
        WebElement delete = driver.findElement(By.id("codec-policy1_deleteAction"));
        delete.click();
        Thread.sleep(2000);
        WebElement confirm = driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]"));
        confirm.click();
        Thread.sleep(2000);
        return true;
    }
    public boolean addMultiplePolicies(WebDriver driver, String policyName, String codec1, String codec2,int n) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        String PolicyName;
        for (int i = 0; i <=n; i++) {
            if (i == 0) {
                driver.findElement(By.xpath(xpathIntialAdd)).click();
            }
            else {
                driver.findElement(By.id("add_codec-policy")).click();
                }
            WebElement name = driver.findElement(By.xpath("//input[@id='name|input']"));
            name.sendKeys(policyName+i);
            WebElement codecs = driver.findElement(By.xpath("//input[@id='allow-codecs|input']"));
            codecs.sendKeys(codec1);
            codecs.sendKeys(Keys.ENTER);
            codecs.sendKeys(codec2);
            codecs.sendKeys(Keys.ENTER);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOkButton))).click();
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + policyName + i + "')]"));
            if (element != null) {
                System.out.println(policyName+ i + " added successfully");
            } else {
                System.out.println("Error in Adding " + policyName + i);
                return false;
            }
        }
        return true;
        }
        public boolean deleteAllCodecPolicies(WebDriver driver) throws InterruptedException {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            WebElement delete = driver.findElement(By.id(idDeleteAll));
            Thread.sleep(3000);
            if(delete.isDisplayed()) {
                delete.click();
                Thread.sleep(3000);
                driver.findElement(By.xpath(xpathConfirmDelete)).click();
                Thread.sleep(2000);
                System.out.println("All Codec Policies Deleted Successfully ");
                return true;
            }
            else{
                System.out.println("No Codec Policy to be deleted");
                return false;
            }
        }
            public boolean copySaveWithNewName(WebDriver driver,String policyName,String newPolicyName) throws InterruptedException {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                WebElement button = driver.findElement(By.xpath("//oj-selector[@id='codec-policy1_table_selector_"+ policyName +"']//input[@type='checkbox']"));
                button.click();
                Thread.sleep(2000);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                wait.until(ExpectedConditions.elementToBeClickable(By.id("codec-policy1_copyAction"))).click();
                Thread.sleep(2000);
                WebElement name1 = driver.findElement(By.xpath("//input[@id='name|input']"));
                name1.clear();
                name1.sendKeys(newPolicyName);
                driver.findElement(By.xpath("//span[@data-bind='text: label'][normalize-space()='OK']")).click();
                String xpathNewPolicyName ="//oj-selector[@id='codec-policy1_table_selector_"+ policyName +"']//input[@type='checkbox']";
                WebElement newPolicy = driver.findElement(By.xpath(xpathNewPolicyName));
                if (newPolicy!=null) {
                    System.out.println("CodecPolicy '" + newPolicyName + "' copied and saved successfully.");
                    return true;
                } else {
                    System.out.println("Error: CodecPolicy '" + newPolicyName + "' not found after copying and saving.");
                    return false;
                }

            }
            public void cleanUp(WebDriver driver) throws InterruptedException {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                try {
                    driver.findElement(By.xpath("//a[@title='media-manager']//span[@class='oj-navigationlist-item-label'][normalize-space()='media-manager']")).click();
                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//span[normalize-space()='codec-policy']")).click();
                    Thread.sleep(2000);
                    if (driver.findElement(By.xpath(xpathIntialAdd)).isDisplayed()) {
                        System.out.println("CleanUp Done");
                    } else {
                        boolean deleteAll = deleteAllCodecPolicies(driver);
                        assert deleteAll;
                        Thread.sleep(2000);
                        System.out.println("Cleanup Done.");
                    }
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }

            public boolean deleteSelective(WebDriver driver, String nameOfPolicy, int n) throws InterruptedException {
            	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                int i;
                for (i = 0; i <= n; i++) {
                    String xpathRadio = "//oj-selector[@id='codec-policy1_table_selector_" + nameOfPolicy + i + "']//input[@type='checkbox']";
                    driver.findElement(By.xpath(xpathRadio)).click();
                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//button[@aria-label='Codec Policy Delete']//div[@class='oj-button-label']")).click();
                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]")).click();
                    Thread.sleep(2000);
                    System.out.println("one Codec policy  deleted");
                }
                String xpathPolicy = "//td[contains(text(),'" + nameOfPolicy + i + "')]";
                List<WebElement> policies = driver.findElements(By.xpath(xpathPolicy));
                if (!policies.isEmpty()) {
                    System.out.println("Error: Policy " + nameOfPolicy + i + " still exists after deletion.");
                    return false;
                }
                System.out.println("All selected policies deleted successfully.");
                return true;
            }
            public boolean searchPolicy(WebDriver driver,String policyName) throws InterruptedException {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                WebElement search = driver.findElement(By.xpath("//input[@id='searchid|input']"));
                search.sendKeys(policyName);
                Thread.sleep(2000);
                driver.findElement(By.id("tableSearchButton")).click();
                Thread.sleep(2000);
                WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='codec-policy1_emptyDataIcons']"));
                if (emptyDataIcons.isDisplayed()) {
                    System.out.println("Policy '" + policyName + "' is Not Available");
                    return false;
                } else {
                    WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + policyName + "')]"));
                    if (element != null) {
                        System.out.println(policyName + " " + "is found successfully");
                        return true;
                    } else {
                        System.out.println(policyName + " " + "is not found ");
                        return false;
                    }

                }
            }
            public boolean enterPolicyAndPressBack(WebDriver driver,String policyName) throws InterruptedException {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/oj-module[1]/div[1]/div[7]/oj-module[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[2]/oj-module[1]/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/div[2]/oj-button[1]/button[1]/div[1]/span[1]")).click();
                WebElement name = driver.findElement(By.xpath("//input[@id='name|input']"));
                name.sendKeys(policyName);
                driver.findElement(By.xpath("//span[contains(text(),'Back')]")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[contains(text(),'Yes')]")).click();
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
            public boolean policyWithEmptyName(WebDriver driver) throws InterruptedException {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                driver.findElement(By.xpath(xpathIntialAdd)).click();
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
