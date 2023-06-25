package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part_I_Fixed_In_DOM {
WebDriver driver;
String projectPath = System.getProperty("user.dir");
String osName = System.getProperty("os.name");

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac")) { //Mac
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else { //Windows
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}
	FirefoxOptions options = new FirefoxOptions();
	options.setProfile(new FirefoxProfile());
	options.addPreference("dom.webnotifications.enabled", false);
	driver = new FirefoxDriver(options);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}

//@Test
public void TC_01_Fixed_In_DOM_NgoaiNgu() {
	driver.get("https://ngoaingu24h.vn/");
	By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
	
	// Verify login popup undisplayed
	Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	
	// CLick vao button login
	driver.findElement(By.cssSelector("button.login_")).click();
	sleepInSecond(3);
	// Verify login popup is displayed
	Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
	
	driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
	driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
	driver.findElement(By.cssSelector("button.btn-login-v1")).click();
	
	Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
	}

@Test
public void TC_02_Fixed_In_DOM_KyNa() {
	driver.get("https://skills.kynaenglish.vn/");
	
	By loginPopup = By.cssSelector("div#k-popup-account-login");
	// verify undisplayed
	Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

	driver.findElement(By.cssSelector("a.login-btn")).click();
	sleepInSecond(2);
	// verify displayed
	Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
	
	driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
	driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
	driver.findElement(By.cssSelector("button#btn-submit-login")).click();
	sleepInSecond(2);
	Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
	
	driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
	// verify undisplayed
	Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
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