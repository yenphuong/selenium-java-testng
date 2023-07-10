package webdriver;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Upload_File {
WebDriver driver;
String projectPath = System.getProperty("user.dir");
String osName = System.getProperty("os.name");
JavascriptExecutor jsExecutor;

String dalatPhoto =   "da_lat.jpeg";
String haiphongPhoto =   "hai_phong.jpeg";
String hagiangPhoto =   "ha_giang.jpeg";

String dalatPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + dalatPhoto;
String haiphongPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + haiphongPhoto;
String hapgiangPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + hagiangPhoto;

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac")) { //Mac
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
	} else { //Windows
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	}
	
	driver = new ChromeDriver();
	jsExecutor = (JavascriptExecutor) driver;
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
//@Test
public void TC_01_One_File_Per_Time() {
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	
	By uploadFile = By.xpath("//input[@type='file']");
	
	driver.findElement(uploadFile).sendKeys(dalatPhotoPath);
	sleepInSecond(1);
	driver.findElement(uploadFile).sendKeys(haiphongPhotoPath);
	sleepInSecond(1);
	driver.findElement(uploadFile).sendKeys(hapgiangPhotoPath);
	sleepInSecond(1);
	
	// verify anh da duoc load len
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + dalatPhoto + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + haiphongPhoto + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + hagiangPhoto + "']")).isDisplayed());
	
	// Click upload cho tung file
	List<WebElement> startButton = driver.findElements(By.cssSelector("tbody.files button.start"));
	for (WebElement start : startButton) {
		start.click();
		sleepInSecond(2);
	}
	
	// verify anh da duoc upload len
	Assert.assertTrue(
			driver.findElement(By.xpath("//a[text()='" + dalatPhoto + "']")).isDisplayed());
	Assert.assertTrue(
			driver.findElement(By.xpath("//a[text()='" + haiphongPhoto + "']")).isDisplayed());
	Assert.assertTrue(
			driver.findElement(By.xpath("//a[text()='" + hagiangPhoto + "']")).isDisplayed());

	// Verify cac hinh upload len la hinh thuc
	Assert.assertTrue(isImageLoaded("//a[@title='" + dalatPhoto + "']//img"));
	Assert.assertTrue(isImageLoaded("//a[@title='" + haiphongPhoto + "']//img"));
	Assert.assertTrue(isImageLoaded("//a[@title='" + hagiangPhoto + "']//img"));
	
	}
@Test
public void TC_02_Multiple_File_Per_Time() {
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	By uploadFile = By.xpath("//input[@type='file']");
	
	// Load ca 3 files
	driver.findElement(uploadFile).sendKeys(dalatPhotoPath + "\n" + haiphongPhotoPath + "\n" + hapgiangPhotoPath);
	sleepInSecond(3);
	
	// verify anh da duoc load len
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + dalatPhoto + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + haiphongPhoto + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + hagiangPhoto + "']")).isDisplayed());
	
	// Click upload cho tung file
	List<WebElement> startButton = driver.findElements(By.cssSelector("tbody.files button.start"));
	for (WebElement start : startButton) {
		start.click();
		sleepInSecond(3);
	}
	
	sleepInSecond(3);
	// verify anh da duoc upload len
	Assert.assertTrue(
			driver.findElement(By.xpath("//a[text()='" + dalatPhoto + "']")).isDisplayed());
	Assert.assertTrue(
			driver.findElement(By.xpath("//a[text()='" + haiphongPhoto + "']")).isDisplayed());
	Assert.assertTrue(
			driver.findElement(By.xpath("//a[text()='" + hagiangPhoto + "']")).isDisplayed());

	// Verify cac hinh upload len la hinh thuc
	Assert.assertTrue(isImageLoaded("//a[@title='" + dalatPhoto + "']//img"));
	Assert.assertTrue(isImageLoaded("//a[@title='" + haiphongPhoto + "']//img"));
	Assert.assertTrue(isImageLoaded("//a[@title='" + hagiangPhoto + "']//img"));
	
	}
@AfterClass
public void afterClass() {
driver.quit();
	}

public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
}

public boolean isImageLoaded(String locator) {
	boolean status = (boolean) jsExecutor.executeScript(
			"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
	return status;
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