package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part_II_Fixed_Not_In_DOM {
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
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}
//@Test
public void TC_01_Fixed_Not_In_DOM_Tiki() {
	driver.get("https://tiki.vn/");
	
	// Khai bao By, chua di tim element, nen khong khai bao WebElement ma chi khai bao bien By
	
	By loginPopup = By.cssSelector("div.ReactModal__Content");
	
	// verify chua hien thi
	Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	
	// Click cho bat login popup len
	driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
	sleepInSecond(2);
	
	// verify hien thi
	Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
	Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
	
	// add sdt vo 
	/*
	 * driver.findElement(By.cssSelector("input[name='tel']")).sendKeys("0909999999"
	 * ); sleepInSecond(2);
	 */
	
	// Bấm vào đăng nhập bằng email
	driver.findElement(By.cssSelector("p.login-with-email")).click();
	// Bam vao button đăng nhập
	driver.findElement(By.xpath("//div[@class='ReactModalPortal']//button[text()='Đăng nhập']")).click();
	sleepInSecond(2);
	// Verify text hiển thị
	
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']"))
			.isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']"))
			.isDisplayed());
	
	// close popup
	driver.findElement(By.cssSelector("button.btn-close")).click();
	sleepInSecond(2);
	
	// verify khong con hien thi
	Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}
@Test
public void TC_02_Fixed_Not_In_DOM_Facebook() {
	driver.get("https://www.facebook.com/");
	
	// Khai bao popup
	By popupSignUp = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
	
	// Verify popupSignUp khong hien thi
	Assert.assertEquals(driver.findElements(popupSignUp).size(), 0);
	
	// Bam vao button sign up
	driver.findElement(By.cssSelector("a[data-testid = 'open-registration-form-button']")).click();
	sleepInSecond(2);
	
	// Verify popupSignup hien thi
	Assert.assertEquals(driver.findElements(popupSignUp).size(), 1);
	
	// Dien thu du lieu vao
	driver.findElement(By.name("firstname")).sendKeys("Automation");
	driver.findElement(By.name("lastname")).sendKeys("FC");
	driver.findElement(By.name("reg_email__")).sendKeys("0898877777");
	driver.findElement(By.name("reg_passwd__")).sendKeys("12345678");
	
	new Select(driver.findElement(By.id("day"))).selectByVisibleText("7");
	new Select(driver.findElement(By.id("month"))).selectByVisibleText("Jan");
	new Select(driver.findElement(By.id("year"))).selectByVisibleText("1980");
	
	driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
	sleepInSecond(2);
	// tat popup
	driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
	sleepInSecond(2);
	
	// verify popup khong hien thi
	Assert.assertEquals(driver.findElements(popupSignUp).size(), 0);
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