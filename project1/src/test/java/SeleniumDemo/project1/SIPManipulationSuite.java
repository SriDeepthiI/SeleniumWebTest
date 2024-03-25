package SeleniumDemo.project1;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SIPManipulationSuite {
	@Test
	public void addEditDel() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        SIPManipulation sm = new SIPManipulation();
        try {
            sm.signIN(driver);
            sm.configuration(driver);
            boolean add = sm.addSIP(driver, "policy1");
            assert add;
            boolean edit = sm.editSIP(driver, "policy1", "policy2");
            assert edit;
            boolean delete = sm.deleteSIP(driver, "policy2");
            assert delete;
        } finally {
            sm.cleanUP(driver);
            sm.logOut(driver);
        }
    }
	@Test
	public void addMultipleDeleteAll() throws InterruptedException {
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
	        WebDriver driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        SIPManipulation sm = new SIPManipulation();
	        try {
	            sm.signIN(driver);
	            sm.configuration(driver);
	            boolean addMultiple = sm.addMultipleSIP(driver, "SIP", 3);
	            assert addMultiple;
	            boolean deleteAll = sm.deleteAllSIP(driver);
	            assert deleteAll;
	        } finally {
	            sm.cleanUP(driver);
	            sm.logOut(driver);
	        }
	}
	@Test
	public void addMultipleDeleteFew() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        SIPManipulation sm = new SIPManipulation();
        try {
            sm.signIN(driver);
            sm.configuration(driver);
            boolean addMultiple = sm.addMultipleSIP(driver, "SIP", 3);
            assert addMultiple;
            boolean deleteFew = sm.deleteSelective(driver, "SIP", 2);
            assert deleteFew;
        } finally {
            sm.cleanUP(driver);
            sm.logOut(driver);
        }
	}
	@Test
	public void searchSIPManipulation() throws InterruptedException {
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
	        WebDriver driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        SIPManipulation sm = new SIPManipulation();
	        try {
	            sm.signIN(driver);
	            sm.configuration(driver);
	            boolean addMultiple = sm.addMultipleSIP(driver, "SIP", 2);
	            assert addMultiple;
	            boolean search = sm.searchSIPManipulation(driver, "SIP1");
	            assert search;
	        } finally {
	            sm.cleanUP(driver);
	            sm.logOut(driver);
	        }
	}
	@Test
	public void copyAndSaveSIPManipulation() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        SIPManipulation sm = new SIPManipulation();
        try {
            sm.signIN(driver);
            sm.configuration(driver);
            boolean add = sm.addSIP(driver, "SIP0");
            assert add;
            boolean copy = sm.copyAndSaveSIPManipulation(driver, "SIP0", "SIP1");
            assert copy;
        } finally {
            sm.cleanUP(driver);
            sm.logOut(driver);
        }
	}
	@Test
	public void EnterSIPManipulationAndPressBack() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        SIPManipulation sm = new SIPManipulation();
        try {
            sm.signIN(driver);
            sm.configuration(driver);
            boolean enterSIP = sm.enterSIPManipulationAndPressBack(driver, "SIP");
            assert enterSIP;
        } finally {
            sm.cleanUP(driver);
            sm.logOut(driver);
        }
	}
	@Test
	public void SIPManipulationWithEmptyName() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        SIPManipulation sm = new SIPManipulation();
        try {
            sm.signIN(driver);
            sm.configuration(driver);
            sm.sIPWithEmptyName(driver);
        } finally {
            sm.cleanUP(driver);
            sm.logOut(driver);
        }
	}
}
