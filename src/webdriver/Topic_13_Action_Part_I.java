package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Action_Part_I {
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
public void TC_01_Tooltip() {
	driver.get("https://automationfc.github.io/jquery-tooltip/");
	
	action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
	// debug bang F8+cmd+\ hoac setTimeout(() => {debugger;}, 2000) vao console
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), 
			"We ask for your age only for statistical purposes.");

}
//@Test
public void TC_02_Myntra() {
	driver.get("http://www.myntra.com/");
	
	action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
	sleepInSecond(3);
	
	driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(),"Kids Home Bath");
	Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(),"Kids Home Bath");
	}
@Test
public void TC_03_Fahasa() {
	driver.get("https://www.fahasa.com/");
	sleepInSecond(10);
	action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
	sleepInSecond(2);
	action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
	sleepInSecond(2);
	driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Quản Trị - Lãnh Đạo']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Quản Trị - Lãnh Đạo']")).isDisplayed());
	
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