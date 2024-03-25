package SeleniumDemo.project1;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TLSProfileSuite {
  @Test
  public void addEditDelete() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      TLSProfile tp = new TLSProfile();
      try {
          tp.signIN(driver);
          tp.configuration(driver);
          boolean addTLS = tp.add_TLS_Profile(driver, "name1", "entity1", "cer1", "list1");
          assert addTLS;
          boolean edit_TLS = tp.edit_TLS_Profile(driver, "name1", "name2");
          assert edit_TLS;
          boolean delete = tp.delete_TLS_Profile(driver, "name2");
          assert delete;
      } catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          tp.cleanUp(driver);
          tp.logOut(driver);
      }  
  }
  @Test
  public void addMultipleDeleteAll() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      TLSProfile tp = new TLSProfile();
      try {
          tp.signIN(driver);
          tp.configuration(driver);
          boolean add_Multiple = tp.add_Multiple_TLS_Profile(driver, "name", 2);
          assert add_Multiple;
          boolean delete = tp.deleteAll_TLS_Profile(driver);
          assert delete;
      } catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          tp.cleanUp(driver);
          tp.logOut(driver);
      }
  }
  @Test
  public void addMultipleDeleteFew() throws InterruptedException { 
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      TLSProfile tp = new TLSProfile();
      try {
          tp.signIN(driver);
          tp.configuration(driver);
          boolean add_Multiple = tp.add_Multiple_TLS_Profile(driver, "name", 2);
          assert add_Multiple;
          boolean delete = tp.deleteSelective(driver, "name", 2);
          assert delete;
      }
      catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          tp.cleanUp(driver);
          tp.logOut(driver);
      }
  }
  @Test
  public void search() throws InterruptedException { 
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      TLSProfile tp = new TLSProfile();
      try {
          tp.signIN(driver);
          tp.configuration(driver);
          boolean add_Multiple = tp.add_Multiple_TLS_Profile(driver, "name", 2);
          assert add_Multiple;
          boolean search = tp.searchTLS_Profile(driver, "name1");
          assert search;
      } catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          tp.cleanUp(driver);
          tp.logOut(driver);
      }
  }
  @Test
  public void copyAndSave() throws InterruptedException { 
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      TLSProfile tp = new TLSProfile();
      try {
          tp.signIN(driver);
          tp.configuration(driver);
          boolean add = tp.add_TLS_Profile(driver, "name1", "entity1", "cer1", "list1");
          assert add;
          boolean copy = tp.copyAndSave_TLSProfile(driver, "name1", "name2");
          assert copy;
      }
      catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          tp.cleanUp(driver);
          tp.logOut(driver);
      }
  }
  @ Test
  public void EnterTLS_AndPressBack() throws InterruptedException { 
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      TLSProfile tp = new TLSProfile();
      try {
          tp.signIN(driver);
          tp.configuration(driver);
          boolean enter_TLS = tp.enterTLS_ProfileAndPressBack(driver, "name1");
          assert enter_TLS;
      }
      catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          tp.cleanUp(driver);
          tp.logOut(driver);
      }
  }
  @ Test
  public void TLSWithEmptyName() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      TLSProfile tp = new TLSProfile();
      try {
          tp.signIN(driver);
          tp.configuration(driver);
          boolean empty = tp.TLS_ProfileWithEmptyName(driver);
          assert empty;
      }
      catch (InterruptedException e) {
          System.out.println(e);
      } finally {
          tp.cleanUp(driver);
          tp.logOut(driver);
      }
  }  
}
