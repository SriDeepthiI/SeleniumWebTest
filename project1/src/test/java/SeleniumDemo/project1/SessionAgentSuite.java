package SeleniumDemo.project1;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class SessionAgentSuite {
  @Test
  public void AddEditDelete() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionAgent sA = new SessionAgent();
      try {
          sA.signIN(driver);
          sA.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean addSessionAgent = sA.addSessionAgent(driver, "Agent1", "0.0.0.0", "realM1");
          assert addSessionAgent;
          boolean editSessionAgent = sA.editSessionAgent(driver, "Agent1", "Agent2");
          assert editSessionAgent;
          boolean deleteSessionAgent = sA.deleteSessionAgent(driver, "Agent2");
          assert deleteSessionAgent;
      } finally {
          sA.cleanup(driver);
          sA.logOut(driver);
      }
  }
  @Test
  public void AddMultipleDeleteAll() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionAgent sA = new SessionAgent();
      try {
          sA.signIN(driver);
          sA.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean addMultiple = sA.addMultipleSession(driver, "Agent", "0.0.0.0", "realm1", 2);
          assert addMultiple;
          boolean deleteAll = sA.deleteAllSessionAgent(driver);
          assert deleteAll;
      } finally {
          sA.cleanup(driver);
          sA.logOut(driver);
      }
  }
  @Test
  public void AddMultipleDeletFew() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionAgent sA = new SessionAgent();
      try {
          sA.signIN(driver);
          sA.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean addSessionAgent = sA.addSessionAgent(driver, "Agent1", "0.0.0.0", "realM1");
          assert addSessionAgent;
          boolean editSessionAgent = sA.editSessionAgent(driver, "Agent1", "Agent2");
          assert editSessionAgent;
          boolean deleteSessionAgent = sA.deleteSessionAgent(driver, "Agent2");
          assert deleteSessionAgent;
      } finally {
          sA.cleanup(driver);
          sA.logOut(driver);
      }
  }
  @Test
  public void CopyAndSave() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionAgent sA = new SessionAgent();
      try {
          sA.signIN(driver);
          sA.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean addSessionAgent = sA.addSessionAgent(driver, "Agent1", "0.0.0.0", "realM1");
          assert addSessionAgent;
          boolean copy = sA.copyAndSaveSessionAgent(driver, "Agent1", "Agent2");
          assert copy;
      } finally {
          sA.cleanup(driver);
          sA.logOut(driver);
      }
  }
  @Test
  public void SessionAgentWithEmptyName() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionAgent sA = new SessionAgent();
      try {
          sA.signIN(driver);
          sA.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          sA.sessionAgentWithEmptyName(driver);
      }
      finally {
          sA.cleanup(driver);
          sA.logOut(driver);
      }
  }
  @Test
  public void EnterSessionAgentAndPressBack() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionAgent sA = new SessionAgent();
      try {
          sA.signIN(driver);
          sA.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean add = sA.enterSessionAgentAndPressBack(driver, "agent1");
          assert add;
      } finally {
          sA.cleanup(driver);
          sA.logOut(driver);
      }
  }
  @Test
  public void SearchSessionAgent() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      RealM rm = new RealM();
      SessionAgent sA = new SessionAgent();
      try {
          sA.signIN(driver);
          sA.configuration(driver);
          boolean addRealM = rm.addRealM(driver, "realm1", 80, 30);
          assert addRealM;
          boolean addMultiple = sA.addMultipleSession(driver, "Agent", "0.0.0.0", "realm1", 2);
          assert addMultiple;
          boolean search = sA.searchSessionAgent(driver, "Agent0");
          assert search;
      } finally {
          sA.cleanup(driver);
          sA.logOut(driver);
      }
  }
}
