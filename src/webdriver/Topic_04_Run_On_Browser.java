package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Run_On_Browser {
WebDriver driver;
String projectPath = System.getProperty("user.dir");
String osName = System.getProperty("os.name");

@Test
public void TC_01_Firefox() {
	if (osName.contains("Mac")) { //Mac
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else { //Windows
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}

	driver = new FirefoxDriver();
	driver.get("https://www.facebook.com/");
	driver.quit();
	}
@Test
public void TC_02_Chrome() {
	if (osName.contains("Mac")) { //Mac
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
	} else { //Windows
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	}

	driver = new ChromeDriver();
	driver.get("https://www.facebook.com/");
	driver.quit();
	}

@Test
public void TC_03_Edge() {
	if (osName.contains("Mac")) { //Mac
		System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
	} else { //Windows
		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
	}

	driver = new EdgeDriver();
	driver.get("https://www.facebook.com/");
	driver.quit();
	}
@AfterClass
public void afterClass() {
//driver.quit();
	}
}