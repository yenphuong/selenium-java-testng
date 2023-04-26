package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Element_PI {
	// Khai báo
	WebDriver driver;
	WebElement element;
	
	// Khai báo + Khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) { //Mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else { //Windows
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		// Khởi tạo
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	@Test
	public void TC_01_Browser() {
	// Các hàm tương tác với Browser sẽ thông qua biến driver
		// Đóng browser
		driver.close(); // đóng 1 tab đang đứng, nếu chỉ có 1 tab thì đóng browser luôn // **
		
		driver.quit(); // đóng luôn browser // **
		
		// Tìm ra 1 element (single)
		WebElement loginButton = driver.findElement(By.cssSelector("")); // **
		
		// Tìm ra nhiều element (multiple)
		List<WebElement> links = driver.findElements(By.cssSelector("")); // **
		
		// Mở ra Url truyền vào
		driver.get("https://wwww.facebook.com/"); // **
		
		// Trả về 1 url tại 1 page đang đứng
		String gamePageUrl = driver.getCurrentUrl();
		
		String gamePageTitle = driver.getTitle();
		
		//Source code page hiện tại
		String gamePageSource = driver.getPageSource();
		
		// Lấy ra cái ID của tab/window đang đứng/ active (Windows/ Tab)
		driver.getWindowHandle(); //1 // **
		driver.getWindowHandles(); //nhiều // **
		
		driver.manage().getCookies(); // **
		driver.manage().logs().getAvailableLogTypes();
		
		driver.manage().window().fullscreen();
		driver.manage().window().maximize(); // **
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://wwww.facebook.com/");
		
		driver.switchTo().alert(); // **
		driver.switchTo().frame(0); // **
		driver.switchTo().window(""); // **
		
		
		}
	@Test
	public void TC_02_Element() {
		driver.get("https://wwww.facebook.com/");
		// Các hàm tương tác với Element sẽ thông qua cái element
		// Có 2 cách để thao tác
		// Khai báo biến và dùng lại -> dùng đi dùng lại nhiều lần, ít nhất 2 lần
		// Dùng trực tiếp - dùng chỉ 1 lần
		
		WebElement emailAddressTextbox = driver.findElement(By.id("email"));
		emailAddressTextbox.clear();
		emailAddressTextbox.sendKeys("phuong@gmail.com");
		
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("phuong@gmail.com");
		
		element.clear();
		
		element.sendKeys("phuong@gmail.com");
		element.sendKeys(Keys.ENTER);
		
		// Tra ve gia tri nam trong attribute cua element
		element.getAttribute("placeholder");
		
		driver.findElement(By.id("firstname")).getAttribute("value");
		
		// tra ve thuoc tinh element
		element.getCssValue("background-color");
		
		element.getCssValue("font-size");
		
		element.getLocation();
		element.getRect();
		
		element.getScreenshotAs(OutputType.FILE);
		
		//WebElement emailAddressTextbox = driver.findElement(By.xpath("//input[@id='email"));
		emailAddressTextbox.getTagName();
		
		element.getText();
		
		element.isDisplayed();
		
		element.isEnabled();
		
		element.isSelected();
		
		element.submit();
		
		
		
		
		
		
		}
	@AfterClass
	public void afterClass() {
	driver.quit();
		}
}