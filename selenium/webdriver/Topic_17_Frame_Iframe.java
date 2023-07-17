package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Frame_Iframe {
WebDriver driver;
JavascriptExecutor jsExecutor;
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
	
	//driver = new FirefoxDriver();
	
	FirefoxOptions options = new FirefoxOptions();
	options.setProfile(new FirefoxProfile());
	options.addPreference("dom.webnotifications.enabled", false);
	driver = new FirefoxDriver(options);
	jsExecutor = (JavascriptExecutor) driver; // ép kiểu
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}
//@Test
public void TC_01_Iframe() {
	driver.get("https://skills.kynaenglish.vn/");
	
	By iframeFanpage = By.cssSelector("div.fanpage iframe");
	Assert.assertTrue(driver.findElement(iframeFanpage).isDisplayed());
	
	// Switch vao facebook iframe
	driver.switchTo().frame(driver.findElement(iframeFanpage));
	System.out.println(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText());
	
	Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "165K followers");
	
	// quay ve trang truoc do (parent)
	driver.switchTo().defaultContent();
	
	// Switch qua frame chat
	By iframeChat = By.cssSelector("iframe#cs_chat_iframe");
	
	driver.switchTo().frame(driver.findElement(iframeChat));
	sleepInSecond(2);
	// click vao iframe chat
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.button_bar")));
	//driver.findElement(By.cssSelector("div.button_bar")).click();
	sleepInSecond(5);
	
	driver.findElement(By.cssSelector("input.input_name")).sendKeys("Hello Test");
	driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0909999999");
	new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
	driver.findElement(By.name("message")).sendKeys("automation test");
	
	// back to default content moi search duoc
	driver.switchTo().defaultContent();
	
	String keyword = "excel";
	driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
	driver.findElement(By.cssSelector("button.search-button")).click();
	sleepInSecond(5);
	
	List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
	
	for (WebElement course : courseName) {
		System.out.println(course.getText());
		Assert.assertTrue(course.getText().toLowerCase().contains(keyword));
	}
	
	}
//@Test
public void TC_02_() {
	// Trang 1 chua iframe 2
	driver.switchTo().frame("2");
	
	// iFrame 2 chua iframe 3
	driver.switchTo().frame("3");
	
	// Quay tu 3 ve 2 (khong ho tro 3 ve 1) ve trang iframe 2
	driver.switchTo().parentFrame();
	
	// 2 ve 1 (ve html chinh
	driver.switchTo().defaultContent();
	}

@Test
public void TC_03_Frame() {
	driver.get("https://netbanking.hdfcbank.com/netbanking/");
	
	// switch frame
	driver.switchTo().frame("login_page");
	sleepInSecond(3);
	
	driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationfc");
	driver.findElement(By.cssSelector("a.login-btn")).click();
	sleepInSecond(3);
	
	// switch ve default
	driver.switchTo().defaultContent();
	
	Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
	
	
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