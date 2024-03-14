package SeleniumDemo.project1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {
	 String website = "http://20.55.216.158";
	    String user = "admin";
	    String pwd = "Abcd1234";
	    String xpathUser = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/oj-module[1]/div[1]/div[2]/oj-module[1]/div[1]/div[3]/oj-input-text[1]/div[1]/div[1]/input[1]";
	    String xpathPwd = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/oj-module[1]/div[1]/div[2]/oj-module[1]/div[1]/div[3]/oj-input-password[1]/div[1]/div[1]/input[1]";
	    String xpathSignIn ="//oj-button[@id='loginbtn']//div[@class='oj-button-label']";

	    public void signIN(WebDriver driver) throws InterruptedException {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        driver.get(website);
	        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
	        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='inputusername|input']")));
	        WebElement userName = driver.findElement(By.xpath(xpathUser));
	        WebElement password = driver.findElement(By.xpath(xpathPwd));
	        userName.sendKeys(user);
	        password.sendKeys(pwd);
	        driver.findElement(By.xpath(xpathSignIn)).click();
	    }

	    public void logOut(WebDriver driver) throws InterruptedException {
	        driver.findElement(By.xpath("//button[@aria-label='admin menu']//span[@class='oj-button-icon oj-end oj-component-icon oj-button-menu-dropdown-icon']")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/oj-menu[1]/oj-option[3]/a[1]/span[1]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[contains(text(),'Yes')]")).click();
	        driver.quit();
	    }
	}


	


