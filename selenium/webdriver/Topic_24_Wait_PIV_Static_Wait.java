package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Wait_PIV_Static_Wait {
WebDriver driver;
String projectPath = System.getProperty("user.dir");
String osName = System.getProperty("os.name");

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac")) { //Mac
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
	} else { //Windows
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	}
	
	driver = new FirefoxDriver();
	
	}
@Test
public void TC_01_Timeout_Less_Than_5_Seconds() {
	driver.get("https://automationfc.github.io/dynamic-loading/");
	
	driver.findElement(By.cssSelector("div#start>button")).click();
	
	sleepInSecond(3);
	
	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}
@Test
public void TC_02_Timeout_Equal_5_Seconds() {
	driver.get("https://automationfc.github.io/dynamic-loading/");
	
	driver.findElement(By.cssSelector("div#start>button")).click();
	
	sleepInSecond(5);
	
	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}
@Test
public void TC_03_Timeout_More_Than_5_Seconds() {
	driver.get("https://automationfc.github.io/dynamic-loading/");
	
	driver.findElement(By.cssSelector("div#start>button")).click();
	
	sleepInSecond(10);
	
	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}
@AfterClass
public void afterClass() {
driver.quit();
	}


// Sleep cứng (Static wait)
public void sleepInSecond(long timeInSecond) {
	try {
		Thread.sleep(timeInSecond*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}