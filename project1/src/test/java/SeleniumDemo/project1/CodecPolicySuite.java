package SeleniumDemo.project1;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class CodecPolicySuite {
   @Test
  public void addEditDel() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      CodecPolicy cp = new CodecPolicy();
      try {
         cp.signIN(driver);
          //Configuration and click Codec Policy.
          cp.configuration(driver);
          Thread.sleep(3000);
          boolean success = cp.addPolicy(driver,"policy1","AMR","G729");
          assert success;
//            Editing the codec policy.
          boolean edit= cp.editPolicy(driver,"policy1","policy2");
          assert edit;
//              Deleting the codec policy.
          boolean delete =cp.deletePolicy(driver,"policy2");
          assert delete;
          System.out.println("Codec Deleted Successfully");
          }
          catch (StaleElementReferenceException e){
              System.out.println(e);
          }
      catch (InterruptedException e) {
          System.out.println(e);
      }
      finally {
          cp.cleanUp(driver);
          cp.logOut(driver);
      }  
  }
  @Test
public void CopyAndSaveCodec() throws InterruptedException {
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
     WebDriver driver = new ChromeDriver();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
     CodecPolicy cp = new CodecPolicy();
     try {
         cp.signIN(driver);
         cp.configuration(driver);
         boolean success = cp.addPolicy(driver,"policy1","AMR","G729");
         assert success;
         boolean copyAndSave = cp.copySaveWithNewName(driver,"policy1","Policy6");
         assert copyAndSave;
     }
     catch (InterruptedException e) {
         System.out.println(e);
         }
     finally {
        cp.cleanUp(driver);
        cp.logOut(driver);
         }
  }
@Test
public void AddMultipleDeleteAll() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    CodecPolicy cp = new CodecPolicy();
    try {
        cp.signIN(driver);
        cp.configuration(driver);
        boolean addMultiple = cp.addMultiplePolicies(driver, "policy name", "AMR", "G729", 3);
        assert addMultiple;
        boolean deleteAll = cp.deleteAllCodecPolicies(driver);
        assert deleteAll;
    }
    catch (InterruptedException e) {
        System.out.println(e);
    }
    finally {
        cp.cleanUp(driver);
        cp.logOut(driver);
    }   
}
@Test
public void EnterCodecAndPressBack() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    CodecPolicy cp = new CodecPolicy();
    try {
        cp.signIN(driver);
        cp.configuration(driver);
        boolean enterPolicy = cp.enterPolicyAndPressBack(driver,"policy1");
        assert enterPolicy;
    } catch (InterruptedException e) {
        System.out.println(e);
    } finally {
        cp.cleanUp(driver);
        cp.logOut(driver);
    }
}
@Test
public void CodecWithEmptyName() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    CodecPolicy cp = new CodecPolicy();
    try {
        cp.signIN(driver);
        cp.configuration(driver);
        boolean emptyNamePolicy = cp.policyWithEmptyName(driver);
        assert emptyNamePolicy;
    }
    catch (InterruptedException e) {
        System.out.println(e);
    }
    finally {
        cp.cleanUp(driver);
        cp.logOut(driver);
    }
}
@Test
public void AddMultipleDeleteFew() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    CodecPolicy cp = new CodecPolicy();
    try {
        cp.signIN(driver);
        cp.configuration(driver);
        boolean addMultiple = cp.addMultiplePolicies(driver,"policy name", "AMR", "G729",2);
        assert addMultiple;
        boolean deleteSelective = cp.deleteSelective(driver,"policy name", 1);
        assert deleteSelective;
    }
    catch (InterruptedException e) {
        System.out.println(e);
    }
    finally {
        cp.cleanUp(driver);
        cp.logOut(driver);
    }
}
}


