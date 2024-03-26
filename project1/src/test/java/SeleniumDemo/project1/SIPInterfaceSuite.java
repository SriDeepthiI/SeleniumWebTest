package SeleniumDemo.project1;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

public class SIPInterfaceSuite {
  @Test
  public void addEditDelete() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SIPInterface sip = new SIPInterface();
      try {
          sip.signIN(driver);
          sip.configuration(driver);
          boolean addMultiple = rm.addMultipleRealM(driver,"identifier", 80, 30, 3);
          assert addMultiple;
          boolean addSIP = sip.addSipInterface(driver, "realm1");
          assert addSIP;
          boolean editSIP = sip.editSIP(driver, "realm1", "realm2");
          assert editSIP;
          boolean deleteSIP = sip.deleteSIP(driver,"realm2");
          assert deleteSIP;
      } finally {
          sip.cleanup(driver);
          sip.logOut(driver);
      }
  }
  @Test
  public void AddMultipleDeleteAll() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SIPInterface sip = new SIPInterface();
      try {
          sip.signIN(driver);
          sip.configuration(driver);
          boolean addMultiple = rm.addMultipleRealM(driver, "RealM", 80, 30, 3);
          assert addMultiple;
          boolean addMultipleSIP = sip.addMultipleSIP(driver, "RealM",2);
          assert addMultipleSIP;
          boolean deleteAllSIP = sip.deleteAllSIPInterface(driver);
          assert deleteAllSIP;
      } finally {
          sip.cleanup(driver);
          sip.logOut(driver);
      }
  }
  @Test
  public void AddMultipleDeleteFew() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SIPInterface sip = new SIPInterface();
      try {
          sip.signIN(driver);
          sip.configuration(driver);
          boolean addMultiple = rm.addMultipleRealM(driver, "RealM", 80, 30, 3);
          assert addMultiple;
          boolean addMultipleSIP = sip.addMultipleSIP(driver, "RealM", 2);
          assert addMultipleSIP;
          boolean deleteFewSIP = sip.deleteSelective(driver, "RealM", 1);
          assert deleteFewSIP;
      } finally {
          sip.cleanup(driver);
          sip.logOut(driver);
      } 
  }
  @Test
  public void CopyAndSave() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SIPInterface sip = new SIPInterface();
      try {
          sip.signIN(driver);
          sip.configuration(driver);
          boolean addMultiple = rm.addMultipleRealM(driver, "identifier", 80, 30, 3);
          assert addMultiple;
          boolean add = sip.addSipInterface(driver, "realM0");
          assert add;
          boolean copy = sip.copyAndSaveSIP(driver, "realM0", "realM1");
          assert copy;
      } finally {
          sip.cleanup(driver);
          sip.logOut(driver);
      }
  }
  @Test
  public void EnterSIPAndPressBack() throws InterruptedException {
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SIPInterface sip = new SIPInterface();
      try {
          sip.signIN(driver);
          sip.configuration(driver);
          boolean add = rm.addRealM(driver, "realm1", 80, 30);
          assert add;
          boolean empty = sip.enterSIPAndPressBack(driver, "realm1");
          assert empty;
      } finally {
          sip.cleanup(driver);
          sip.logOut(driver);
      }
  }
  @Test
  public void SIPWithEmptyName() throws InterruptedException {  
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SIPInterface sip = new SIPInterface();
      try {
          sip.signIN(driver);
          sip.configuration(driver);
          boolean add = rm.addRealM(driver, "realm0", 80, 30);
          assert add;
          sip.sIPWithEmptyName(driver);
      } finally {
          sip.cleanup(driver);
          sip.logOut(driver);
      }
  }
  @Test
  public void SearchSIPInterface() throws InterruptedException { 
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SIPInterface sip = new SIPInterface();
      try {
          sip.signIN(driver);
          sip.configuration(driver);
          boolean addMultiple = rm.addMultipleRealM(driver, "realM", 80, 30, 3);
          assert addMultiple;
          boolean addMultipleSIP = sip.addMultipleSIP(driver, "realM", 2);
          assert addMultipleSIP;
          boolean search = sip.searchSIP(driver, "realM0");
          assert search;
      } finally {
          sip.cleanup(driver);
          sip.logOut(driver);
      }
  }
 }
