package webdriver;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Javascript_Executor {
WebDriver driver;
JavascriptExecutor jsExecutor;
Random rand = new Random();
String emailAddress = "testdemo"+rand.nextInt(9999)+"@gmail.com";
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
	jsExecutor = (JavascriptExecutor) driver;
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	}
//@Test
public void TC_01_TechPanda() {
	// mo trang tech panda bang js
	navigateToUrlByJS("http://live.techpanda.org/");
	sleepInSecond(5);
	
	// lấy ra domain
	String homePageDomain = (String) executeForBrowser("return document.domain;");
	Assert.assertEquals(homePageDomain, "live.techpanda.org");
	
	// Lay url, neu khong khai bao bien thi khong can ep kieu
	Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.techpanda.org/");
	
	// Mo trang Mobile, locator dung xpath
	hightlightElement("//a[text()='Mobile']");
	clickToElementByJS("//a[text()='Mobile']");
	sleepInSecond(3);
	// click lay 1 samsung galaxy add to card
	hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
	clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
	sleepInSecond(3);
	
	// verify text
	hightlightElement("//li[@class='success-msg']");
	Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
	Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
	
	// Mở trang customer service
	hightlightElement("//a[text()='Customer Service']");
	clickToElementByJS("//a[text()='Customer Service']");
	sleepInSecond(3);
	Assert.assertEquals(executeForBrowser("return document.title;"), "Customer Service");
	
	hightlightElement("//input[@id='newsletter']");
	scrollToElementOnDown("//input[@id='newsletter']");
	sleepInSecond(3);
	
	hightlightElement("//input[@id='newsletter']");
	sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);
	sleepInSecond(3);
	
	hightlightElement("//span[text()='Subscribe']");
	clickToElementByJS("//span[text()='Subscribe']");
	sleepInSecond(3);
	
	hightlightElement("//li[@class='success-msg']");
	Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
	Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
	
	navigateToUrlByJS("http://demo.guru99.com/v4/");
	sleepInSecond(5);
	Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");
}
@Test
public void TC_02_Rode() {
	driver.get("https://warranty.rode.com/");
	
	driver.findElement(By.xpath("//a[text()=' Create an Account ']")).click();
	By registerButton = By.xpath("//button[text()=' Register ']");
	
	driver.findElement(registerButton).click();
	sleepInSecond(3);
	Assert.assertEquals(getElementValidationMessage("//input[@id='name']"), "Please fill out this field.");
	getElement("//input[@id='name']").sendKeys("Automation");
	
	driver.findElement(registerButton).click();
	sleepInSecond(3);
	Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please fill out this field.");
	getElement("//input[@id='email']").sendKeys(emailAddress);
	
	driver.findElement(registerButton).click();
	sleepInSecond(3);
	Assert.assertEquals(getElementValidationMessage("//input[@id='password']"), "Please fill out this field.");
	getElement("//input[@id='password']").sendKeys("123456");
	
	driver.findElement(registerButton).click();
	sleepInSecond(3);
	Assert.assertEquals(getElementValidationMessage("//input[@id='password_confirmation']"), "Please fill out this field.");
	getElement("//input[@id='password_confirmation']").sendKeys("123456");
	
	}
@AfterClass
public void afterClass() {
driver.quit();
	}

public Object executeForBrowser(String javaScript) {
	return jsExecutor.executeScript(javaScript);
}

public String getInnerText() {
	return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
}

public boolean areExpectedTextInInnerText(String textExpected) {
	String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
	return textActual.equals(textExpected);
}

public void scrollToBottomPage() {
	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}

public void navigateToUrlByJS(String url) {
	jsExecutor.executeScript("window.location = '" + url + "'");
	sleepInSecond(3);
}

public void hightlightElement(String locator) {
	WebElement element = getElement(locator);
	String originalStyle = element.getAttribute("style");
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
	sleepInSecond(2);
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
}

public void clickToElementByJS(String locator) {
	jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	sleepInSecond(3);
}

public void scrollToElementOnTop(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
}

public void scrollToElementOnDown(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
}

public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
	jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
}

public void removeAttributeInDOM(String locator, String attributeRemove) {
	jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
}
// hàm senkey này hạn chế dùng bằng js executor
public void sendkeyToElementByJS(String locator, String value) {
	jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
}

public String getAttributeInDOM(String locator, String attributeName) {
	return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
}

public String getElementValidationMessage(String locator) {
	return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
}

public boolean isImageLoaded(String locator) {
	boolean status = (boolean) jsExecutor.executeScript(
			"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
	return status;
}

public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
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