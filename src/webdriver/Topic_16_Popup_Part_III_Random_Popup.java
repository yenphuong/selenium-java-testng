package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part_III_Random_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "testdemo" + getRandomNumber() + "@gmail.com";

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
// Yêu cầu:
// Random popup nên nó có thể hiện thị 1 cách ngẫu nhiên hay không hiển thị
// Nếu như nó hiển thị thì mình thao tác lên popup -> Đóng nó đi để qua step tiếp theo
// Khi đóng nó xong rồi có thể bị đóng luôn, nhưng khi refresh trang có thể hiện lại
// Nếu như nó không hiển thị thì qua step tiếp theo luôn

//@Test
	public void TC_01_Random_In_DOM() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(30);
		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");

		// Vi no luon co trong DOM nen co the dung ham isDisplayed de kiem tra
		if (driver.findElement(lePopup).isDisplayed()) {
			// Nhap Email vao
			driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(emailAddress);
			sleepInSecond(3);
			driver.findElement(By.cssSelector("a[data-label='Get the Books'],[data-label='OK']>span")).click();
			sleepInSecond(6);
			// Verify
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(),
					"Thank you!");
			Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText()
					.contains("Your sign-up request"));
			sleepInSecond(10);

			// Dong popup di -> qua step tiep theo
		}
		// qua step nay
		String articleName = "Agile Testing Explained";
		driver.findElement(By.cssSelector("input#search-input")).sendKeys(articleName);
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleepInSecond(3);
		Assert.assertEquals(
				driver.findElement(By.cssSelector("ul#posts-container>li:first-child>div.post-details>h2>a")).getText(),
				articleName);
	}

//@Test
	public void TC_02_Random_In_DOM_KMPlayer() {
		driver.get("http://www.kmplayer.com/");

		By popupAd = By.cssSelector("div.pop-container");
		sleepInSecond(30);

		if (driver.findElement(popupAd).isDisplayed()) {
			driver.findElement(By.cssSelector("div#layer2")).click();
			sleepInSecond(3);
			Assert.assertFalse(driver.findElement(popupAd).isDisplayed());
		}
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Media Player for Android/iOS']")).isDisplayed());

	}

	@Test
	public void TC_03_Random_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);

		By popup = By.cssSelector("div#div.popup-content");

		// Hàm isDisplayed chỉ check cho element có trong DOM
		// Không có trong DOM thì không check được -> Fail ngay đoạn findElement
		if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("Testd-Demo");
			driver.findElement(By.id("popup-email")).sendKeys(emailAddress);
			driver.findElement(By.id("popup-phone")).sendKeys("0898777666");
			sleepInSecond(3);=

			driver.findElement(By.id("close-popup")).click();
			sleepInSecond(1);
		}
		// Neu khong hien thi thi click vao Tat ca khoa hoc
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		String courseName = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		driver.findElement(By.id("search-courses")).sendKeys(courseName);
		driver.findElement(By.id("search-course-button")).click();
		sleepInSecond(3);

		// Duy nhat 1 khoa hoc hien thi
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course")).size(), 1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.course-content>h4")).getText(), courseName);

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

	public int getRandomNumber() {
		return new Random().nextInt(9999);
	}

}