package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Wait_PII_FindElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	List<WebElement> elements;
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
		driver.get("https://www.facebook.com/reg/");

		// Set implicit wait 10s
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	//@Test
	public void TC_01_FindElement() {
		//WebElement element = driver.findElement(By.cssSelector(""));
		// Trả về 1 element

		// 3 trường hợp
		// TH1: Nếu tìm chỉ có 1 element được tìm thấy
		// FindElement sẽ apply cái timeout của implicitWait
		// Vì nó vào và tìm thấy element ngay nên không cần phải chờ hết timeout là 10s
//		driver.findElement(By.cssSelector("input[name='firstname']"));
//		// TH2: Tìm thấy nhiều hơn 1 element
//		// Sẽ luôn lấy element đầu tiên
//		// Vì nó vào và tìm thấy element ngay nên không cần phải chờ hết timeout là 10s
//		driver.findElement(By.cssSelector("input[type='text']")).sendKeys("AutomationFC");;
		// TH3: Không tìm thấy element nào hết
		driver.findElement(By.xpath("//div[text()=\"What's your name?\"]"));
	}

	@Test
	public void TC_02_FindElements() {
		//List<WebElement> element = driver.findElements(By.cssSelector(""));
		// Trả về nhiều element (1 danh sách element)

		// 3 trường hợp
		// TH1: Nếu tìm chỉ có 1 element được tìm thấy
		// FindElements sẽ apply cái timeout của implicitWait
		// Vì nó vào và tìm thấy element ngay nên không cần phải chờ hết timeout là 10s
//		elements = driver.findElements(By.cssSelector("input[name='firstname']"));
//		// Trả về số lượng element, dùng .size() để lấy số lượng
//		System.out.println("Case 1: Tim thay 1 element = " + elements.size());
//		
//		// TH2: Tìm thấy nhiều hơn 1 element
//		// Nó sẽ lấy ra hết tất cả các element được tìm thấy
//		elements = driver.findElements(By.cssSelector("input[type='text']"));
//		System.out.println("Case 2: Tim thay 6 element = " + elements.size());
//		// TH3: Không tìm thấy element nào hết
		elements = driver.findElements(By.xpath("//div[text()=\"What's your name?\"]"));
		System.out.println("Case 3: Không tìm thấy element, size " + elements.size());
		Assert.assertEquals(elements.size(), 0);
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