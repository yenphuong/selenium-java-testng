package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio {
WebDriver driver;
JavascriptExecutor jsExecutor;
String projectPath = System.getProperty("user.dir");
String osName = System.getProperty("os.name");

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac")) { //Mac
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
	} else { //Windows
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}
	
	driver = new ChromeDriver();
	// Khởi tạo sau biến driver
	jsExecutor = (JavascriptExecutor) driver; // ép kiểu
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}

public void TC_01_() {
	driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
	sleepInSecond(3);
	driver.findElement(By.xpath("//div[contains(text(),'Đăng ký cho người thân')]/preceding-sibling::div/input")).click();
	sleepInSecond(3);
	Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Đăng ký cho người thân')]/preceding-sibling::div/input")).isSelected());
	}

public void TC_02_() {
	driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
	sleepInSecond(3);
	driver.findElement(By.xpath("//div[contains(text(),'Đăng ký cho người thân')]/parent::label")).click();
	sleepInSecond(3);
	Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Đăng ký cho người thân')]/parent::label")).isSelected());
	}

public void TC_03_() {
	driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
	sleepInSecond(3);
	driver.findElement(By.xpath("//div[contains(text(),'Đăng ký cho người thân')]/parent::label")).click();
	sleepInSecond(3);
	Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Đăng ký cho người thân')]/preceding-sibling::div/input")).isSelected());
	}

public void TC_04_() {
	driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
	sleepInSecond(3);
	// Thẻ input bị ẩn nhưng vẫn dùng để click
	// Dùng hàm click của javascript có thể click vào element bị ẩn
	By radioButton =  By.xpath("//div[contains(text(),'Đăng ký cho người thân')]/preceding-sibling::div/input");
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
	sleepInSecond(3);
	Assert.assertTrue(driver.findElement(radioButton).isSelected());
	}
@Test
public void TC_05_() {
	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
	sleepInSecond(3);
	// Thẻ input bị ẩn nhưng vẫn dùng để click
	// Dùng hàm click của javascript có thể click vào element bị ẩn
	By radioButton =  By.cssSelector("div[aria-label='Hà Nội']");
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
	sleepInSecond(3);
	// Cách 1: check xem có display không
	Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hà Nội'][aria-checked='true']")).isDisplayed());
	
	// Cách 2: lấy attribute rồi dùng assert equal
	//Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");
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