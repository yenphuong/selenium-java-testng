package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
WebDriver driver;
Alert alert;
WebDriverWait explicitWait;
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
	explicitWait = new WebDriverWait(driver, 10);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}
//@Test
public void TC_01_Accept_Alert() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	// Có thể switch qua rồi tương tác luôn hoặc wait khi nào xuất hiện thì tương tác -> cần wait để hiển thị alert rồi mới tương tác
	//alert = driver.switchTo().alert(); // cách 1 có thể ko hiển thị alert và không tương tác được
	alert = explicitWait.until(ExpectedConditions.alertIsPresent()); // cách 2 là wait rồi switch qua luôn
	// Tương tác - verify alert title đúng như mong đợi
	Assert.assertEquals(alert.getText(), "I am a JS Alert");
	// accept alert
	alert.accept();
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}
//@Test
public void TC_02_Confirm_Alert() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	alert.dismiss();
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");
	
	}
//@Test
public void TC_03_Prompt_Alert() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	Assert.assertEquals(alert.getText(), "I am a JS prompt");
	String courseName = "Fullstack Selenium Java";
	alert.sendKeys(courseName);
	alert.accept();
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: "+courseName);
	}
@Test
public void TC_04_Authentication_Alert() {
	// Truyền trực tiếp username và password vào url thì tự động signin vào luôn
	// http:// + username : password @ url
	
	// Cach 1
//	driver.get(passUserAndPassToUrl("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
//	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),\"Congratulations! You must have the proper credentials.\")]")).isDisplayed());
	
	// Cach 2
	driver.get("https://the-internet.herokuapp.com/");
	String ulrAlert = driver.findElement(By.xpath("//a[contains(text(),\"Basic Auth\")]")).getAttribute("href");
	driver.get(passUserAndPassToUrl(ulrAlert, "admin", "admin"));
	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),\"Congratulations! You must have the proper credentials.\")]")).isDisplayed());
	}
public String passUserAndPassToUrl (String url, String username, String password) {
	String[] arrayUrl = url.split("//");
	return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1] ;
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