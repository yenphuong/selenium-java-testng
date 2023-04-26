package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Element_PIII {
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
	System.out.println(osName);
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}
@Test
public void TC_01_Is_Displayed() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	// Email Textbox
	WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
	
	if (emailTextbox.isDisplayed()) {
		emailTextbox.sendKeys("Automation Testing");
		System.out.println("Email textbox is displayed");
	}
	else {
		System.out.println("Email textbox is not displayed");
	}
	// Age under 18 radio button
	WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
	if (ageUnder18Radio.isDisplayed()) {
		ageUnder18Radio.click();
		System.out.println("Age under 18 radio is displayed");
	}
	else {
		System.out.println("Age under 18 radio is not displayed");
	}
	
	// Education TextArea
	WebElement educationTextarea = driver.findElement(By.cssSelector("textarea#edu"));
	if (educationTextarea.isDisplayed()) {
		educationTextarea.sendKeys("Automation Testing Textarea");
		System.out.println("Education TextArea is displayed");
	}
	else {
		System.out.println("Education TextArea is not displayed");
	}

	// Image 5 (undisplayed)
	WebElement image5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		
	if (image5.isDisplayed()) {
		System.out.println("Image 5 is displayed");
	}
	else {
		System.out.println("Image 5 is not displayed");
	}
	}
@Test
public void TC_02_Is_Enabled() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	//Email textbox
	WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
	if (emailTextbox.isEnabled()) {
		System.out.println("Email Textbox is enabled");
	} else {
		System.out.println("Email Textbox is disabled");
	}
	
	// Password textbox
	WebElement passwordTextbox = driver.findElement(By.cssSelector("input#disable_password"));
	if (passwordTextbox.isEnabled()) {
		System.out.println("passwordTextbox is enabled");
	} else {
		System.out.println("passwordTextbox is disabled");
	}
	
	//
	
	
	}
@Test
public void TC_03_Is_Selected() {

	}
@AfterClass
public void afterClass() {
//driver.quit();
	}

public void sleepInSecond(long timeInSecond) {
	try {
		Thread.sleep(timeInSecond*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



}