package SeleniumDemo.project1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class RealMSuiteTest {
  @Test
  public void AddMultipleDeleteAll()throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      try {
          rm.signIN(driver);
          rm.configuration(driver);
          Thread.sleep(3000);
          boolean addMultiple = rm.addMultipleRealM(driver, "identifier", 80, 30, 3);
          assert addMultiple;
          Thread.sleep(3000);
          boolean delete = rm.deleteAllRealM(driver);
          assert delete;
      } catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          rm.cleanUp(driver);
          rm.logOut(driver);
      }
      }

  @Test
  public void addEditDelTest()throws InterruptedException  {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      try {
          rm.signIN(driver);
          //Configuration and click RealM Policy.
          rm.configuration(driver);
//          Add Realm Policy
         boolean success = rm.addRealM(driver,"realm1",80,30);
         assert success;
         boolean edit = rm.editRealM(driver,"realm1","realm2");
          assert edit;
         boolean delete = rm.deleteAllRealM(driver);
         assert delete;
      } catch (InterruptedException e) {
          System.out.println(e);
      } finally {
         rm.cleanUp(driver);
         rm.logOut(driver);
      }
      }
  @Test
    public void CopyAndSaveRealM()throws InterruptedException  {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      try {
          rm.signIN(driver);
          //Configuration and click RealM Policy.
          rm.configuration(driver);
          Thread.sleep(3000);
//          Add Realm Policy
          boolean success = rm.addRealM(driver, "realm1", 80, 30);
          assert success;
          Thread.sleep(3000);
          boolean copyAndSave = rm.copyAndSaveWithNewName(driver,"realm1","realm2");
          assert copyAndSave;
      } catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          rm.cleanUp(driver);
          rm.logOut(driver);
      }
  }
  @Test
    public void RealMWithEmptyName()throws InterruptedException  {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      try {
          rm.signIN(driver);
          rm.configuration(driver);
          rm.realMWithEmptyName(driver);
      } catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          rm.cleanUp(driver);
          rm.logOut(driver);
      }
      }
  @Test
  public void EnterRealMAndPressBack()throws InterruptedException  {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      try {
          rm.signIN(driver);
          rm.configuration(driver);
          Thread.sleep(3000);
          boolean enterRealM = rm.enterRealMAndPressBack(driver, "RealM1");
          assert enterRealM;
      }
      catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          rm.cleanUp(driver);
          rm.logOut(driver);
      }
  } 
  @Test
  public void AddMultipleDeleteFew()throws InterruptedException  {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      try {
          rm.signIN(driver);
          rm.configuration(driver);
          Thread.sleep(3000);
          rm.addMultipleRealM(driver, "identifier", 80, 30, 1);
          Thread.sleep(3000);
          boolean deleteSelective = rm.deleteSelective(driver, "identifier", 0);
          assert deleteSelective;
          Thread.sleep(2000);
      } catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          rm.cleanUp(driver);
          rm.logOut(driver);
      }
  }
  }     
  
  

