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
}
