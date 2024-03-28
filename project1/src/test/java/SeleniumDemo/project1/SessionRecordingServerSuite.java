package SeleniumDemo.project1;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class SessionRecordingServerSuite {
  @Test
  public void addEditDelete() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionRecordingServer srs = new SessionRecordingServer();
      try {
          srs.signIN(driver);
          srs.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean add = srs.addSessionRecordingServer(driver, "policy1", "realm1", "0.0.0.0");
          assert add;
          boolean edit = srs.editSessionRecording(driver,"policy1","policy2");
          assert edit;
          boolean delete = srs.deleteSRS(driver,"policy2");
          assert delete;
      }
      finally {
    	  Thread.sleep(2000);
          srs.cleanUp(driver);
          Thread.sleep(2000);
          srs.logOut(driver);
      }
  }
  @Test
  public void addMultipleDeleteAll() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionRecordingServer srs = new SessionRecordingServer();
      try {
          srs.signIN(driver);
          srs.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean addMultiple = srs.addMultipleSRS(driver, "policy", "realm1", "0.0.0.0", 2);
          assert addMultiple;
          boolean deleteAll = srs.deleteAllSRS(driver);
          assert deleteAll;
      } finally {
    	  Thread.sleep(2000);
          srs.cleanUp(driver);
          Thread.sleep(2000);
          srs.logOut(driver);
      }
  }
  @Test
  public void addMultipleDeleteFew() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionRecordingServer srs = new SessionRecordingServer();
      try {
          srs.signIN(driver);
          srs.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean addMultiple = srs.addMultipleSRS(driver, "policy", "realm1", "0.0.0.0", 2);
          assert addMultiple;
          boolean delete = srs.deleteSelective(driver, "policy", 1);
          assert delete;
      } finally {
    	  Thread.sleep(2000);
          srs.cleanUp(driver);
          Thread.sleep(2000);
          srs.logOut(driver);
      }
  }
  @Test
  public void copyAndSave() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionRecordingServer srs = new SessionRecordingServer();
      try {
          srs.signIN(driver);
          srs.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean add = srs.addSessionRecordingServer(driver, "policy1", "realm1", "0.0.0.0");
          assert add;
          boolean copy = srs.copyAndSaveSRS(driver, "policy1", "policy2");
          assert copy;
      } finally {
    	  Thread.sleep(2000);
          srs.cleanUp(driver);
          Thread.sleep(2000);
          srs.logOut(driver);
      }
  }
  @Test
  public void searchSRS() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionRecordingServer srs = new SessionRecordingServer();
      try {
          srs.signIN(driver);
          srs.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean addMultiple = srs.addMultipleSRS(driver, "policy", "realm1", "0.0.0.0", 2);
          assert addMultiple;
          boolean search = srs.searchSRS(driver, "policy1");
          assert search;
      } finally {
    	  Thread.sleep(2000);
          srs.cleanUp(driver);
          Thread.sleep(2000);
          srs.logOut(driver);
      }
  }
  @Test
  public void enterSRSAndPressBack() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionRecordingServer srs = new SessionRecordingServer();
      try {
          srs.signIN(driver);
          srs.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean pressBack = srs.enterSRSAndPressBack(driver, "policy1", "realm1", "0.0.0.0");
          assert pressBack;
      } finally {
    	  Thread.sleep(2000);
          srs.cleanUp(driver);
          Thread.sleep(2000);
          srs.logOut(driver);
      }
  }
  @Test
  public void SRSWithEmptyName() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionRecordingServer srs = new SessionRecordingServer();
      try {
          srs.signIN(driver);
          srs.configuration(driver);
          boolean add = rm.addRealM(driver, "realm1", 80, 30);
          assert add;
          srs.sRSWithEmptyName(driver);
      } finally {
    	  Thread.sleep(2000);
          srs.cleanUp(driver);
          Thread.sleep(2000);
          srs.logOut(driver);
      }
  } 
}
