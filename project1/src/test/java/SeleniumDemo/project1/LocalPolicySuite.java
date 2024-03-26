package SeleniumDemo.project1;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LocalPolicySuite {
  @Test
  public void AddEditDelete() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      LocalPolicy lp = new LocalPolicy();
      try {
          lp.signIN(driver);
          lp.configuration(driver);
          boolean addRealM = rm.addRealM(driver,"realm1",80,30);
          assert addRealM;
          boolean add = lp.addLocalPolicy(driver, "0.0.0.0", "20.0.0.0","realm1");
          assert add;
          boolean edit = lp.editLocalPolicy(driver,"20.0.0.0","20.1.0.0");
          assert edit;
          boolean delete = lp.deleteLocalPolicy(driver,"20.1.0.0");
          assert delete;
      } finally {
          lp.cleanUp(driver);
          lp.logOut(driver);
      }
  }
  @Test
  public void AddMultipleDeleteAll() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      LocalPolicy lp = new LocalPolicy();
      try {
          lp.signIN(driver);
          lp.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          String[] fromAddress = {"0.0.0.0", "0.1.0.0", "0.2.0.0"};
          String[] toAddress = {"192.168.1.1", "192.168.1.2", "192.168.1.3"};
          boolean addMultiple = lp.addMultipleLocalPolicies(driver, fromAddress,toAddress, "realm1", 2);
          assert addMultiple;
          boolean delete = lp.deleteAllLocalPolicy(driver);
          assert delete;
      } finally {
          lp.cleanUp(driver);
          lp.logOut(driver);
      }
  }
  @Test
  public void AddMultipleDeleteFew() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      LocalPolicy lp = new LocalPolicy();
      try {
          lp.signIN(driver);
          lp.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          String[] fromAddress = {"0.0.0.0", "0.1.0.0", "0.2.0.0"};
          String[] toAddress = {"192.168.1.1", "192.168.1.2", "192.168.1.3"};
          boolean addMultiple = lp.addMultipleLocalPolicies(driver, fromAddress, toAddress, "realm1", 2);
          assert addMultiple;
          String[] fromAddressDelete = {"0.0.0.0"};
          String[] toAddressDelete = {"192.168.1.1"};
          boolean delete = lp.deleteSelective(driver, fromAddressDelete, toAddressDelete, "realm1", 1);
          assert delete;
      } finally {
          lp.cleanUp(driver);
          lp.logOut(driver);
      }
  }
  @Test
  public void CopyAndSave() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      LocalPolicy lp = new LocalPolicy();
      try {
          lp.signIN(driver);
          lp.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean add = lp.addLocalPolicy(driver, "0.0.0.0", "20.0.0.0", "realm1");
          assert add;
          boolean copy = lp.copyAndSaveLocalPolicy(driver, "0.0.0.0", "0.1.0.0", "20.0.0.0", "20.1.0.0", "realm1");
          assert copy;
      } finally {
          lp.cleanUp(driver);
          lp.logOut(driver);
      }
  }
 @Test
 public void EnterLocalPolicyAndPressBack() throws InterruptedException {
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
     WebDriver driver = new ChromeDriver();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
     RealM rm = new RealM();
     LocalPolicy lp = new LocalPolicy();
     try {
         lp.signIN(driver);
         lp.configuration(driver);
         boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
         assert addRealM;
         boolean back = lp.enterLocalPolicyAndPressBack(driver, "0.0.0.0", "20.0.0.0", "realm1");
         assert back;
     } finally {
         lp.cleanUp(driver);
         lp.logOut(driver);
     }
 }
 @Test
 public void LocalPOlicyWithEmptyName() throws InterruptedException { 
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
     WebDriver driver = new ChromeDriver();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
     RealM rm = new RealM();
     LocalPolicy lp = new LocalPolicy();
     try {
         lp.signIN(driver);
         lp.configuration(driver);
         lp.localPolicyWithEmptyName(driver);
     } finally {
         lp.cleanUp(driver);
         lp.logOut(driver);
     }
 }
 @Test
 public void SearchLocalPolicy() throws InterruptedException { 
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
     WebDriver driver = new ChromeDriver();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
     RealM rm = new RealM();
     LocalPolicy lp = new LocalPolicy();
     try {
         lp.signIN(driver);
         lp.configuration(driver);
         boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
         assert addRealM;
         String[] fromAddress = {"0.0.0.0", "0.1.0.0", "0.2.0.0"};
         String[] toAddress = {"192.168.1.1", "192.168.1.2", "192.168.1.3"};
         boolean addMultiple = lp.addMultipleLocalPolicies(driver, fromAddress, toAddress, "realm1", 2);
         assert addMultiple;
         boolean search = lp.searchLocalPolicy(driver, "0.0.0.0", "192.168.1.1");
         assert search;
     } finally {
         lp.cleanUp(driver);
         lp.logOut(driver);
     }
 }
}
 

  
  

  

