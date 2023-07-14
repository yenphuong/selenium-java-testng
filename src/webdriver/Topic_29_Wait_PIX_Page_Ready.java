package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_29_Wait_PIX_Page_Ready {
WebDriver driver;
WebDriverWait explicitWait;
Actions action;
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
	
	driver = new FirefoxDriver();
	explicitWait = new WebDriverWait(driver, 30);
	action = new Actions(driver);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
//@Test
public void TC_01_Orage_HRM_API() {
	driver.get("https://api.orangehrm.com/");
	
	// Wait cho icon loading bien mat/ ajax loading/ progress bar loading
	// Vi khi no bien mat thi cái trang nó sẽ load hết dữ liệu về thành công
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader>div.spinner")));
	Assert.assertTrue(driver.findElement(By.cssSelector("div#project h1")).getText().equals("OrangeHRM REST API Documentation"));
	
	}
//@Test
public void TC_02_Admin_NopCommerce() {
	driver.get("https://admin-demo.nopcommerce.com");
	
	driver.findElement(By.cssSelector("input#Email")).clear();
	driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
	
	driver.findElement(By.cssSelector("input#Password")).clear();
	driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
	driver.findElement(By.cssSelector("button.login-button")).click();
	
	// wait clickable ko co tac dung do bi 1 cai khac che
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']")));
	// phai wait cho ajaxBusy bien mat
	// explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
	Assert.assertTrue(WaitForAjaxBusyLoadingInvisible());
	
	driver.findElement(By.xpath("//a[text()='Logout']")).click();
	
	Assert.assertTrue(WaitForAjaxBusyLoadingInvisible());
	
	Assert.assertEquals(driver.getTitle(), "Your store. Login" );
	
	}
//@Test
public void TC_03_Admin_NopCommerce_Wait_Page_Ready() {
	driver.get("https://admin-demo.nopcommerce.com");
	
	driver.findElement(By.cssSelector("input#Email")).clear();
	driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
	
	driver.findElement(By.cssSelector("input#Password")).clear();
	driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
	driver.findElement(By.cssSelector("button.login-button")).click();
	
	
	}
@Test
public void TC_03_Bog_Test_Project() {
	driver.get("https://blog.testproject.io/");
	
	// Hover mouse to any element at this page to page ready
	// Use action library
	// action luc nao cung phai perform
	action.moveToElement(driver.findElement(By.cssSelector("h1.main-heading"))).perform();
	 
	Assert.assertTrue(isPageLoadedSuccess());
	
	String keyword = "Selenium";
	
	driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
	driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
	
	// Wait cho page heading visible
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='main-heading' and text()='Search Results']")));
	Assert.assertTrue(isPageLoadedSuccess());
	
	List<WebElement> listArticle = driver.findElements(By.cssSelector("h3.post-title>a"));

	for (WebElement article : listArticle) {
		Assert.assertTrue(article.getText().contains(keyword));
	}


}

public boolean isPageLoadedSuccess() {
	WebDriverWait explicitWait = new WebDriverWait(driver, 30);
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
		}
	};

	ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
		}
	};
	return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
}

@AfterClass
public void afterClass() {
driver.quit();
	}

public boolean WaitForAjaxBusyLoadingInvisible() {
	return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
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