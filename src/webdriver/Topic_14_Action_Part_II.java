package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_Part_II {
WebDriver driver;
Actions action;
String projectPath = System.getProperty("user.dir");
String osName = System.getProperty("os.name");

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac")) { //Mac
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else { //Windows
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}
	
	driver = new FirefoxDriver();
	action = new Actions(driver);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}
//@Test
public void TC_01_Click_And_Hold_Block() {
	driver.get("https://automationfc.github.io/jquery-selectable/");
	// tao 1 list chua 12 so (12 item)
	List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
	// click vao so 1 (source)
	action.clickAndHold(listNumber.get(0))
	// van giu chuot/ chua nha chuot ra
	// di chuot toi so (target)
	.moveToElement(listNumber.get(7))
	// nha chuot trai ra
	.release()
	// execute
	.perform();
	
	sleepInSecond(3);
	List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
	Assert.assertEquals(listSelectedNumber.size(), 8);
}
@Test
public void TC_02_Click_And_Hold_Random() {
	driver.get("https://automationfc.github.io/jquery-selectable/");
	
	// Chay duoc cho ca Mac va Windows
	Keys key = null;
	if (osName.contains("Windows")) {
		key = Keys.CONTROL;
	} else {
		key = Keys.COMMAND;
	}
	
	// tao 1 list chua 12 so (12 item)
	List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
	// nhan ctrl xuong
	action.keyDown(key).perform();
	// click chon cac so random
	action.click(listNumber.get(0))
	.click(listNumber.get(3))
	.click(listNumber.get(5))
	.click(listNumber.get(10)).perform();
	// nha phim ctrl ra
	action.keyUp(key).perform();
	sleepInSecond(3);
	
	List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
	Assert.assertEquals(listSelectedNumber.size(), 4);
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