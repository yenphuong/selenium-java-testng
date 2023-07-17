package webdriver;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Action_Part_III {
WebDriver driver;
Actions action;
JavascriptExecutor jsExecutor;
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
	jsExecutor = (JavascriptExecutor) driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}
//@Test
public void TC_01_Double_Click() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	// scroll den element
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//button[text()='Double click me']")) );
	sleepInSecond(3);
	action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
}
//@Test
public void TC_02_Right_Click() {
	driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	// right click
	action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	// kiem tra xem option quit co ton tai ko
	Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
	// hover vo quit
	action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
	sleepInSecond(3);
	// kiem tra da hover duoc chua
	Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
	// click vo quit
	action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
	sleepInSecond(3);
	
	// accept alert
	driver.switchTo().alert().accept();
	sleepInSecond(3);
	
	Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
}
//@Test
public void TC_03_Drag_And_Drop_HTML4() {
	driver.get("https://automationfc.github.io/kendo-drag-drop/");
	WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
	WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
	
	action.dragAndDrop(smallCircle, bigCircle).perform();
	sleepInSecond(3);
	
	// Verify text
	Assert.assertEquals(bigCircle.getText(), "You did great!");
	
	// Verify background color, lay mau rgb
	String bigCircleRGB = bigCircle.getCssValue("background-color");
	
	// convert ma mau qua ma HEXA
	String bigCircleHexa = Color.fromString(bigCircleRGB).asHex();
	
	Assert.assertEquals(bigCircleHexa.toLowerCase(), "#03a9f4");
}

@Test
public void TC_04_Drag_And_Drop_HTML5() {
	driver.get("https://automationfc.github.io/drag-drop-html5/");
	
	
	
	
	
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