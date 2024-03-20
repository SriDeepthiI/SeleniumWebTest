package SeleniumDemo.project1;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SteeringPoolSuite {
  @Test
      public void addMultipleDeleteAll() throws InterruptedException{
    	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
          WebDriver driver = new ChromeDriver();
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
          RealM rm = new RealM();
          SteeringPool sp = new SteeringPool();
          try {
              sp.signIN(driver);
              sp.configuration(driver);
              boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
              assert addRealM;
              String[] IPs = {"192.168.1.1", "192.168.1.2", "192.168.1.3"};
              boolean addMultiple = sp.addMultipleSteeringPool(driver,IPs,"realm1");
              assert addMultiple;
              boolean deleteAll = sp.deleteAllSteeringPool(driver);
              assert deleteAll;
          }
          catch (InterruptedException e) {
              System.out.println(e);
          }
          finally {
              sp.cleanUp(driver);
              rm.cleanUp(driver);
              sp.logOut(driver);
          }
      }
	@Test
	  public void addEditDelete() throws InterruptedException{
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
	      WebDriver driver = new ChromeDriver();
	      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	      RealM rm = new RealM();
	      SteeringPool sp = new SteeringPool();
	      try {
	          sp.signIN(driver);
	          sp.configuration(driver);
	          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
	          assert addRealM;
	          boolean success = sp.addSteeringPool(driver, "192.44.0.0", "realm1");
	          assert success;
	          boolean edit = sp.editSteeringPool(driver, "192.44.0.0", "20.0.0.0", "realm1");
	          assert edit;
	          boolean delete = sp.deleteSteeringPool(driver, "20.0.0.0", "realm1");
	          assert delete;
	      }
	      catch (InterruptedException e){
	          System.out.println(e);
	      }
	      finally {
	          sp.cleanUp(driver);
	          rm.cleanUp(driver);
	          sp.logOut(driver);
	      }
	  }
	@Test
	public void addMultipleDeleteFew() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        RealM rm = new RealM();
        SteeringPool sp = new SteeringPool();
        try {
            sp.signIN(driver);
            sp.configuration(driver);
            boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
            assert addRealM;
            String[] IPs = {"192.168.1.1", "192.168.1.2", "192.168.1.3"};
            boolean addMultiple = sp.addMultipleSteeringPool(driver, IPs, "realm1");
            assert addMultiple;
            String[] iPs = {"192.168.1.1", "192.168.1.2"};
            boolean deleteFew = sp.deleteSelective(driver, iPs, "realm1");
            assert deleteFew;
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        finally {
            sp.cleanUp(driver);
            rm.cleanUp(driver);
            sp.logOut(driver);
        }
	}
	@Test
	public void CopyAndSave() throws InterruptedException{
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
	        WebDriver driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        RealM rm = new RealM();
	        SteeringPool sp = new SteeringPool();
	        try {
	            sp.signIN(driver);
	            sp.configuration(driver);
	            boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
	            assert addRealM;
	            boolean success = sp.addSteeringPool(driver, "192.44.0.0", "realm1");
	            assert success;
	            boolean copy = sp.copyAndSaveIPWithNewIP(driver, "192.44.0.0", "192.44.0.1", "realm1");
	            assert copy;
	        }
	        catch (InterruptedException e) {
	            System.out.println(e);
	        }
	        finally {
	            sp.cleanUp(driver);
	            rm.cleanUp(driver);
	            sp.logOut(driver);
	        }
	}
	@Test
	public void enterSPAndPressBack() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        RealM rm = new RealM();
        SteeringPool sp = new SteeringPool();
        try {
            sp.signIN(driver);
            sp.configuration(driver);
            boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
            assert addRealM;
            boolean pressBackPool = sp.enterSteeringPoolAndPressBack(driver, "20.0.0.0", "realm1");
            assert pressBackPool;
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        finally {
            sp.cleanUp(driver);
            rm.cleanUp(driver);
            sp.logOut(driver);
        }
	}
	@Test
	public void searchSteeringPool() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        RealM rm = new RealM();
        SteeringPool sp = new SteeringPool();
        try {
            sp.signIN(driver);
            sp.configuration(driver);
            boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
            assert addRealM;
            String[] IPs = {"192.168.1.1", "192.168.1.2", "192.168.1.3"};
            boolean addMultiple = sp.addMultipleSteeringPool(driver, IPs, "realm1");
            assert addMultiple;
            boolean search = sp.searchSteeringPoolIp(driver, "192.168.1.1");
            assert search;
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        finally {
            sp.cleanUp(driver);
            rm.cleanUp(driver);
            sp.logOut(driver);
        }
	}
	@Test
	public void enterEmptySP() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        RealM rm = new RealM();
        SteeringPool sp = new SteeringPool();
        try {
            sp.signIN(driver);
            sp.configuration(driver);
            boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
            assert addRealM;
            sp.steeringPoolWithEmptyName(driver);
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        finally {
            sp.cleanUp(driver);
            rm.cleanUp(driver);
            sp.logOut(driver);
        }
	}
	
	
      
      
      

}
