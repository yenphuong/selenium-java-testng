package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Wait_PVIII_Fluent {
	WebDriver driver;
//WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentWaitDriver;
	FluentWait<WebElement> fluentWaitElement;
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
		// explicitWait = new WebDriverWait(driver, 30);
		// fluentWaitElement = new
		// FluentWait<WebElement>(driver.findElement(By.cssSelector("")));

		// Selenium 2.x 3.x
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_GetText() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// click vào button
		driver.findElement(By.cssSelector("div#start>button")).click();

		fluentWaitDriver = new FluentWait<WebDriver>(driver);

		// Selenium ver 2.x (TimeUnit) -> Decrecated (Lỗi thời)
		// fluentWaitDriver.withTimeout(15, TimeUnit.SECONDS);

		// Selenium 3.x 4.x
		/*
		 * // Tổng thời gian chờ là bao lâu
		 * fluentWaitDriver.withTimeout(Duration.ofSeconds(15));
		 * 
		 * // Thời gian tìm lại là bao lâu (polling)
		 * fluentWaitDriver.pollingEvery(Duration.ofMillis(100));
		 * 
		 * // Nếu trong quá trình tìm element mà không thấy thì sẽ throw ngoại lệ //
		 * Ignore exception này trong code
		 * fluentWaitDriver.ignoring(NoSuchElementException.class);
		 * 
		 * // Điều kiện của fluent wait fluentWaitDriver.until(new Function<WebDriver,
		 * Boolean>() {
		 * 
		 * @Override public Boolean apply(WebDriver driver) { // TODO Auto-generated
		 * method stub return
		 * driver.findElement(By.cssSelector("div#finish>h4")).getText().
		 * equals("Hello World!"); } });
		 */

		// Cách viết gọn hơn
		// Setting time + duration
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15)) // set timeout
				.pollingEvery(Duration.ofMillis(100)) // set polling
				.ignoring(NoSuchElementException.class); // ignore exception

		// Gán biến
		String helloWorldText = fluentWaitDriver.until(new Function<WebDriver, String>() {
			@Override
			public String apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish>h4")).getText();
			}
		});

		
	}

	//@Test
	public void TC_02_Equals() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// click vào button
		driver.findElement(By.cssSelector("div#start>button")).click();

		fluentWaitDriver = new FluentWait<WebDriver>(driver);

		// Cách viết gọn hơn
		// Setting time + duration
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15)) // set timeout
				.pollingEvery(Duration.ofMillis(100)) // set polling
				.ignoring(NoSuchElementException.class); // ignore exception

		// Gán biến
		fluentWaitDriver.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish>h4")).getText().equals("Hello World!");
			}
		});

		// Wrapper Class: define cho type của hàm
		// boolean -> Boolean
		// int -> Integer
		// float -> Float
	}

	//@Test
	public void TC_03_Is_Displayed() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// click vào button
		driver.findElement(By.cssSelector("div#start>button")).click();

		fluentWaitDriver = new FluentWait<WebDriver>(driver);

		// Cách viết gọn hơn
		// Setting time + duration
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15)) // set timeout
				.pollingEvery(Duration.ofMillis(100)) // set polling
				.ignoring(NoSuchElementException.class); // ignore exception

		// Gán biến
		fluentWaitDriver.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id = 'finish']/h4[text()='Hello World!']")).isDisplayed();
			}
		});
	}
	@Test
	public void TC_04_Element() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		fluentWaitElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("div#javascript_countdown_time")));

		// Cách viết gọn hơn
		// Setting time + duration
		fluentWaitElement.withTimeout(Duration.ofSeconds(15)) // set timeout
				.pollingEvery(Duration.ofMillis(500)) // set polling
				.ignoring(NoSuchElementException.class); // ignore exception

		// Gán biến
		Boolean status = fluentWaitElement.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
		Assert.assertTrue(status);
		Assert.assertTrue(status);
		// test a comment ngày 11/7/2023 lúc 13:10
		// test 2 
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