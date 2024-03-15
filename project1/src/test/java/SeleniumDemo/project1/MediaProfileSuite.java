package SeleniumDemo.project1;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class MediaProfileSuite {
@Test
public void addEditDel() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    MediaProfile mp = new MediaProfile();
    try{
        mp.signIN(driver);
        mp.configuration(driver);
        boolean success = mp.addMediaProfile(driver,"media1","subName1","load1");
        assert success;
        boolean editMedia =mp.editMedia(driver,"media1","media2","subName1","subName2");
        assert editMedia;
        boolean delete= mp.deleteMedia(driver,"media2","subName2");
        assert delete;
    }
    finally {
        mp.cleanUp(driver);
        mp.logOut(driver);

    }
}
@Test
public void AddMultipleDeleteAll() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    MediaProfile mp = new MediaProfile();
    try {
        mp.signIN(driver);
        mp.configuration(driver);
        boolean addMultiple = mp.addMultipleMediaProfiles(driver,"mediaName_","load1",2);
        assert addMultiple;
        boolean deleteAll = mp.deleteAllMediaProfile(driver);
        assert deleteAll;
    }
    catch (InterruptedException e) {
        System.out.println(e);
    }
    finally {
        mp.cleanUp(driver);
        mp.logOut(driver);
    }
}
@Test
public void AddMultipleDeleteFew() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    MediaProfile mp = new MediaProfile();
    try {
        mp.signIN(driver);
        mp.configuration(driver);
        boolean addMultiple = mp.addMultipleMediaProfiles(driver, "mediaName_", "load1", 3);
        assert addMultiple;
        boolean deleteFew = mp.deleteSelective(driver, "mediaName_", 1);
        assert deleteFew;
    } catch (InterruptedException e) {
        System.out.println(e);
    } finally {
        mp.cleanUp(driver);
        mp.logOut(driver);
    }
}
@Test
public void CopyAndSaveMediaProfile() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    MediaProfile mp = new MediaProfile();
    try {
        mp.signIN(driver);
        mp.configuration(driver);
        boolean success = mp.addMediaProfile(driver,"media1","subName1","load1");
        assert success;
        boolean copyAndSave = mp.copyAndSaveMediaProfile(driver,"media1","media2","subName1","subName2");
        assert copyAndSave;
    }
    catch (InterruptedException e) {
        System.out.println(e);
    }
    finally {
        mp.cleanUp(driver);
        mp.logOut(driver);
    }
}
@Test
public void EnterMediaProfileAndPressBack() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    MediaProfile mp = new MediaProfile();
    try {
        mp.signIN(driver);
        mp.configuration(driver);
        boolean enterMP = mp.enterMediaProfileAndPressBack(driver,"media1");
        assert enterMP;
    }
    catch (InterruptedException e) {
        System.out.println(e);
    } finally {
        mp.cleanUp(driver);
        mp.logOut(driver);

    }
}
@Test
public void MediaProfileWithEmptyname() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    MediaProfile mp = new MediaProfile();
    try {
        mp.signIN(driver);
        mp.configuration(driver);
        mp.mediaProfileWithEmptyName(driver);
    } catch (InterruptedException e) {
        System.out.println(e);
    } finally {
        mp.cleanUp(driver);
        mp.logOut(driver);
    }
}
@Test
public void SearchMediaProfile() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    MediaProfile mp = new MediaProfile();
    try {
        mp.signIN(driver);
        mp.configuration(driver);
        boolean multipleProfiles = mp.addMultipleMediaProfiles(driver, "mediaName", "load1", 2);
        assert multipleProfiles;
        boolean search = mp.searchMediaProfile(driver, "mediaName0");
        assert search;
    }catch (InterruptedException e) {
        System.out.println(e);
    }
    finally {
        mp.cleanUp(driver);
        mp.logOut(driver);
    }
}
}
