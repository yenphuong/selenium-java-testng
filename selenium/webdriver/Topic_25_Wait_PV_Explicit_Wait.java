package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Wait_PV_Explicit_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC_01_Wait_For_Attribute_Contain_Value() {
		driver.get("http://live.techpanda.org/index.php");

		explicitWait = new WebDriverWait(driver, 30);

		// Wait cho search textbox chua gia tri la 1 doan placeholder text
		// Có thể lấy toàn bộ text hoặc 1 phần do nó là hàm contain
		// Cái này wait trước
		explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder",
				"Search entire store"));
		explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder",
				"Search entire store here..."));

		// Nên dùng By hơn là dùng Element vì có bước tìm element, sẽ ảnh hưởng
		// implicitWait
		explicitWait.until(ExpectedConditions.attributeContains(driver.findElement(By.cssSelector("input#search")),
				"placeholder", "Search entire store here..."));

		// Chứa thôi thì dùng contains, còn tuyệt đối thì dùng to be
		explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector("input#search"), "placeholder",
				"Search entire store here..."));

		// Verify luôn chứ không wait
		Assert.assertEquals(driver.findElement(By.cssSelector("input#search")).getAttribute("placeholder"),
				"Search entire store here...");

	}

//@Test
	public void TC_02_Wait_For_Element_Clickable() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait = new WebDriverWait(driver, 30);
		// Wait cho button clickable trước khi được click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));

		// click vào button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// vào mailchimp
		driver.get("https://login.mailchimp.com/signup/");
		// Wait cho button clickable trước khi được click
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.cssSelector("button#create-account-enabled")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#create-account-enabled")));
		// click vào button
		driver.findElement(By.cssSelector("button#create-account-enabled")).click();
	}

@Test
	public void TC_03_Wait_For_Element_Selected() {
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://automationfc.github.io/multiple-fields/");
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("input.form-checkbox"), 29));
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		
		// Neu gap 1 checkbox co ten thi click
		for (WebElement checkbox : allCheckboxes) {
			checkbox.click();
		}

		// Verify tất cả các checkbox được chọn thành công
		for (WebElement checkbox : allCheckboxes) {
			explicitWait.until(ExpectedConditions.elementToBeSelected(checkbox));
			Assert.assertTrue(checkbox.isSelected());
		}

	}

	//@Test
	public void TC_04_Wait_For_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		explicitWait = new WebDriverWait(driver, 10);

		// switch frame
		explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("login_page")));
		// driver.switchTo().frame("login_page");
		
		// Thao tac voi userid
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("a.login-btn")).click();

		// switch ve default
		driver.switchTo().defaultContent();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("keyboard")));
		Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());

	}
	//@Test
	public void TC_05_Wait_For_GetText() {
		// Dùng hàm textToBe
		
		// Hàm này ít dùng, thường dùng hàm visible located hơn
		
		
		explicitWait = new WebDriverWait(driver, 10);
		//explicitWait.until(ExpectedConditions.
	}
	//@Test
	public void TC_06_Wait_For_Url_Title() {
		// Dùng urlcontains -> url chứa
		// dùng urltobe -> url tuyệt đối
		// title tương tự
		
		// có case number of nữa
	
	}
	//@Test
	public void TC_07_Timeout_Less_Than_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 3);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Chờ cho 1 step trước biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		// chờ cho 1 step sau xuất hiện
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

		}
	//@Test
	public void TC_08_Timeout_Equal_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Chờ cho 1 step trước biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		}
	//@Test
	public void TC_09_Timeout_More_Than_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Chờ cho 1 step trước biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

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