package webdriver;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Wait_VI_Explicit_Exercise {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	String dalatPhoto = "da_lat.jpeg";
	String haiphongPhoto = "hai_phong.jpeg";
	String hagiangPhoto = "ha_giang.jpeg";

	String dalatPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + dalatPhoto;
	String haiphongPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + haiphongPhoto;
	String hapgiangPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + hagiangPhoto;

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

		// wait status
		explicitWait = new WebDriverWait(driver, 30);

		// cho findelement/findElements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_Telerik() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		System.out.println("1 - Start: " + getDateTimeNow());
		String textToday = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText();
		System.out.println(textToday);
		System.out.println("2 - Start: " + getDateTimeNow());
		Assert.assertEquals(textToday, "No Selected Dates to display.");

		// Wait cho ngay can click la Clickable
		System.out.println("3 - Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='8']/parent::td")));

		// Click vao ngay hien tai
		System.out.println("4 - Start: " + getDateTimeNow());
		driver.findElement(By.xpath("//a[text()='8']/parent::td")).click();

		// Ajax loading
		// xpath element //div[not(@style='display:none;')]/div[@class='raDiv']
		// css element div:not([style='display:none;'])>div.raDiv
		// wait cho loading bien mat
		System.out.println("5 - Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("div:not([style='display:none;'])>div.raDiv")));

		// Verify ngay da chon, wait ngay duoc chon la selected
		System.out.println("6 - Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[text()='8']/parent::td[@class='rcSelected']")));
		textToday = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText();
		System.out.println(textToday);
		System.out.println("7 - Start: " + getDateTimeNow());
		Assert.assertEquals(textToday, "Saturday, July 8, 2023");
		System.out.println("7 - End: " + getDateTimeNow());

	}

	@Test
	public void TC_02_Upload() {
		driver.get("https://gofile.io/uploadFiles");

		// Wait cho loading icon bien mat
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("div#mainContent>div>div>div.spinner-border")));

		// Load file len
		By uploadFile = By.xpath("//input[@type='file']");

		// Load ca 3 files
		driver.findElement(uploadFile).sendKeys(dalatPhotoPath + "\n" + haiphongPhotoPath + "\n" + hapgiangPhotoPath);

		// Wait upload icon o trang Upload bien mat
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("div.mainUploadInitInfo>div>div.spinner-border")));

		// wait cho tat ca progress bar bien mat
		explicitWait.until(
				ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));

		// wait cho success messsage hien thi
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccess div.text-white")));

		// wait + click vao link
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")))
				.click();

		// Wait cho loading icon bien mat
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("div#mainContent>div>div>div.spinner-border")));

		// Wait đến khi bảng upload file xuất hiện
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesContentTable")));

		// Verify Download
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[text()='" + dalatPhoto
						+ "']/parent::a/parent::div/following-sibling::div//button/span[text()='Download']"))
				.isDisplayed());
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[text()='" + haiphongPhoto
						+ "']/parent::a/parent::div/following-sibling::div//button/span[text()='Download']"))
				.isDisplayed());
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[text()='" + hagiangPhoto
						+ "']/parent::a/parent::div/following-sibling::div//button/span[text()='Download']"))
				.isDisplayed());

		// Verify Play
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[text()='" + dalatPhoto
						+ "']/parent::a/parent::div/following-sibling::div//button/span[text()='Play']"))
				.isDisplayed());
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[text()='" + haiphongPhoto
						+ "']/parent::a/parent::div/following-sibling::div//button/span[text()='Play']"))
				.isDisplayed());
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[text()='" + hagiangPhoto
						+ "']/parent::a/parent::div/following-sibling::div//button/span[text()='Play']"))
				.isDisplayed());
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
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