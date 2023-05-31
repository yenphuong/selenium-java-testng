package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Checkbox {
WebDriver driver;
String projectPath = System.getProperty("user.dir");
String osName = System.getProperty("os.name");

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac")) { //Mac
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else { //Windows
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}
	
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	//driver.get("https://www.facebook.com/");
	}

public void TC_01_Button() {
	driver.get("https://www.fahasa.com/customer/account/create");
	driver.findElement(By.cssSelector("li.popup-login-tab-item")).click();
	By loginButton = By.cssSelector("button.fhs-btn-login");
	// Verify login button is disabled
	Assert.assertFalse(driver.findElement(loginButton).isEnabled());
	
	// Verify button color is gray -> sai mau, ko lam dc
	driver.findElement(By.cssSelector("input#login_username")).sendKeys("0908888777");
	driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
	sleepInSecond(2);
	
	// Verify button is enabled
	//Assert.assertTrue(driver.findElement(loginButton).isEnabled());
	Assert.assertTrue(driver.findElement(loginButton).isEnabled());
	String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");
	Color loginButtonBackgroundColour = Color.fromString(loginButtonBackground);
	Assert.assertEquals(loginButtonBackgroundColour.asHex().toUpperCase(), "#C92127");
	System.out.println(loginButtonBackground);
	System.out.println(loginButtonBackgroundColour.toString());
	
	
	}
@Test
public void TC_02_Default_Checkbox_Radio_Single() {
	driver.get("https://automationfc.github.io/multiple-fields/");
	
	// click chon 1 checkbox
	driver.findElement(By.xpath("//label[contains(text(), 'Anemia')]/preceding-sibling::input")).click();
	
	// click chon 1 radio
	driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]/preceding-sibling::input")).click();
	
	// Verify da chon chua
	Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), 'Anemia')]/preceding-sibling::input")).isSelected());
	Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]/preceding-sibling::input")).isSelected());
	
	// Checkbox co the tu bo chon duoc
	driver.findElement(By.xpath("//label[contains(text(), 'Anemia')]/preceding-sibling::input")).click();
	
	// Verify checkbox da duoc bo chon roi
	Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(), 'Anemia')]/preceding-sibling::input")).isSelected());
	
	// Radio khong the tu bo chon
	driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]/preceding-sibling::input")).click();
	
	// Verify radio khong bo chon
	Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]/preceding-sibling::input")).isSelected());
	
	}

public void TC_03_Default_Checkbox_Multiple() {
	driver.get("https://automationfc.github.io/multiple-fields/");
	List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
	
	/*
	 * // Dùng vòng lặp for để duyệt for (WebElement checkbox : allCheckboxes) {
	 * checkbox.click(); sleepInSecond(1); } // Verify da chon chua for (WebElement
	 * checkbox : allCheckboxes) { Assert.assertTrue(checkbox.isSelected()); }
	 */
	
	// Neu gap 1 checkbox co ten thi click
	for (WebElement webElement : allCheckboxes) {
		if (webElement.getAttribute("value").equals("Digestive Problems")) {
			webElement.click();		}
	}
	
}
@AfterClass
public void afterClass() {
//driver.quit();
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