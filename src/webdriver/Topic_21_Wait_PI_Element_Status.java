package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Wait_PI_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) { // Mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else { // Windows
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}

		driver = new FirefoxDriver();

		// Cho việc tìm element (findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Cho trạng thái của element -> Cụ thể
		explicitWait = new WebDriverWait(driver, 30);
	}

//@Test
	public void TC_01_Visible_Displayed() {
		driver.get("https://www.facebook.com/");

		// Điều kiện 1: Element có hiển thị trên UI và có trong DOM (cây HTML)

		// Chờ cho email textbox được hiển thị trước khi sendkeys
		// Chờ trong khoảng time là 30s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
	}

	// @Test
	public void TC_02_Invisible_Undisplayed_Case_I() {
		// Điều kiện 2: Element không hiển thị trên UI (không nhìn thấy) nhưng vẫn có
		// trong cây DOM (cây HTML)

		driver.get("https://www.facebook.com/");

		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		// Confirmation email khong hien thi khi chưa nhập email
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

		// Nhập email vô
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("automationfc@gmail.com");

		// Confirm email textbox hien thi sau khi nhap email
		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

		// Xuat hien confirm email thi sendkey vao cho no
		driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']")).sendKeys("automationfc@gmail.com");
	}

	// @Test
	public void TC_02_Invisible_Undisplayed_Case_II() {
		// Điều kiện 3: Element không nhìn thấy/không có trên UI và không có trong cây
		// HTML
		driver.get("https://www.facebook.com/");

		// Confirmation email khong hien thi khi chưa nhập email
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

		// Do không có trong DOM nên lâu, > 30s

	}

	// @Test
	public void TC_03_Presence_Case_I() {
		// Điều kiện 1: Element có hiển thị trên UI và có trong DOM (cây HTML)
		driver.get("https://www.facebook.com/");

		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#email")));
	}

	// @Test
	public void TC_03_Presence_Case_II() {
		// Điều kiện 2: Element không hiển thị trên UI (không nhìn thấy) nhưng vẫn có
		// trong cây DOM (cây HTML)

		driver.get("https://www.facebook.com/");

		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		// Confirmation email khong hien thi khi chưa nhập email
		explicitWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

	}

	@Test
	public void TC_04_Staleness() {
		// B1: có trong HTML -> Sau đó apply đk 3
		driver.get("https://www.facebook.com/");

		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		// Confirmation email khong hien thi khi chưa nhập email
		explicitWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		// B1: Element phai co trong HTM:
		WebElement confirmationEmailTextbox = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));

		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		
		// staleness chạy nhanh hơn do element đã lưu rồi
		explicitWait.until(ExpectedConditions.stalenessOf(confirmationEmailTextbox));
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

// Sleep cứng (Static wait)
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}