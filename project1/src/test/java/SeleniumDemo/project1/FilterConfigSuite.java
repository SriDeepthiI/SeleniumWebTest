package SeleniumDemo.project1;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FilterConfigSuite {
  @Test
  public void AddEditDelete() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      FilterConfig fc = new FilterConfig();
      try {
          fc.signIN(driver);
          fc.configuration(driver);
          boolean success = fc.addFilterConfig(driver, "filter1", "user1");
          assert success;
          boolean edit = fc.editFilterConfig(driver,"filter1","filter2");
          assert edit;
          boolean delete = fc.deleteFilterConfig(driver,"filter2");
          assert delete;
      } catch (InterruptedException e) {
          System.out.println(e);
      }
      finally {
          fc.cleanUp(driver);
          fc.logOut(driver);
          
      }
  }
  @Test
  public void AddMultipleFilterConfigDeleteAll() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      FilterConfig fc = new FilterConfig();
      try {
          fc.signIN(driver);
          fc.configuration(driver);
          boolean addMultiple = fc.addMultipleFilterConfig(driver, "filterconfig", "user1", 1);
          assert addMultiple;
          boolean deleteAll = fc.deleteAllFilterConfig(driver);
          assert deleteAll;
      } catch (InterruptedException e) {
          System.out.println(e);
      }
      finally {
          fc.cleanUp(driver);
          fc.logOut(driver);
      } 
  }
  @Test
  public void AddMultipleFilterConfigDeleteFew() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      FilterConfig fc = new FilterConfig();
      try {
          fc.signIN(driver);
          fc.configuration(driver);
          fc.addMultipleFilterConfig(driver, "filterconfig", "user1", 3);
          boolean deleteFew = fc.deleteSelectiveFilterConfig(driver, "filterconfig", 2);
          assert deleteFew;
      } catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          fc.cleanUp(driver);
          fc.logOut(driver);
      } 
  }
  @Test
  public void CopyAndSaveFilterConfig () throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      FilterConfig fc = new FilterConfig();
      try{
          fc.signIN(driver);
          fc.configuration(driver);
          boolean success = fc.addFilterConfig(driver,"filter1","user1");
          assert success;
          boolean validateCopy = fc.copySaveWithNewName(driver,"filter1","filter2");
          assert validateCopy;
      }
      catch (InterruptedException e){
          System.out.println(e);
      }
      finally {
          fc.cleanUp(driver);
          fc.logOut(driver);
      }
  }
  @Test
  public void EnterFilter_ConfigAndPressBack() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      FilterConfig fc = new FilterConfig();
      try {
          fc.signIN(driver);
          fc.configuration(driver);
          boolean pressBack = fc.enterFilter_ConfigAndPressBack(driver, "filter1");
          assert pressBack;
      }
      catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          fc.cleanUp(driver);
          fc.logOut(driver);

      }
  }
  @Test
  public void FilterWithEmptyName() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      FilterConfig fc = new FilterConfig();
      try {
          fc.signIN(driver);
          fc.configuration(driver);
          boolean emptyName = fc.filterWithEmptyName(driver);
          assert emptyName;
      }
      catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          fc.cleanUp(driver);
          fc.logOut(driver);

      }
  }
  @Test
  public void SearchFilterConfig () throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      FilterConfig fc = new FilterConfig();
      try {
          fc.signIN(driver);
          fc.configuration(driver);
          boolean success = fc.addFilterConfig(driver, "filter1", "user1");
          assert success;
          boolean search = fc.searchPolicy(driver, "filter1");
          assert search;
      } catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          fc.cleanUp(driver);
          fc.logOut(driver);
      }
  }
  
}
