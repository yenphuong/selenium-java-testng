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
		if (osName.contains("Mac")) { // Mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else { // Windows
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
		} else {
			System.out.println("Email textbox is not displayed");
		}
		// Age under 18 radio button
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age under 18 radio is displayed");
		} else {
			System.out.println("Age under 18 radio is not displayed");
		}

		// Education TextArea
		WebElement educationTextarea = driver.findElement(By.cssSelector("textarea#edu"));
		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing Textarea");
			System.out.println("Education TextArea is displayed");
		} else {
			System.out.println("Education TextArea is not displayed");
		}

		// Image 5 (undisplayed)
		WebElement image5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

		if (image5.isDisplayed()) {
			System.out.println("Image 5 is displayed");
		} else {
			System.out.println("Image 5 is not displayed");
		}
	}

	@Test
	public void TC_02_Is_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Email textbox
		WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
		if (emailTextbox.isEnabled()) {
			System.out.println("Email Textbox is enabled");
		} else {
			System.out.println("Email Textbox is disabled");
		}

		// Education textbox
		WebElement educationTextbox = driver.findElement(By.cssSelector("textarea#edu"));
		if (educationTextbox.isEnabled()) {
			System.out.println("educationTextbox is selected");
		} else {
			System.out.println("educationTextbox is de-selected");
		}

		// Job role 01 select
		WebElement jobRole01Select = driver.findElement(By.cssSelector("select#job1"));
		if (jobRole01Select.isEnabled()) {
			System.out.println("jobRole01Select is enabled");
		} else {
			System.out.println("jobRole01Select is disabled");
		}
		// Job role 02 select
		WebElement jobRole02Select = driver.findElement(By.cssSelector("select#job2"));
		if (jobRole02Select.isEnabled()) {
			System.out.println("jobRole02Select is enabled");
		} else {
			System.out.println("jobRole02Select is disabled");
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
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Age under 18 radio
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		ageUnder18Radio.click();
		if (ageUnder18Radio.isSelected()) {
			System.out.println("ageUnder18Checkbox is selected");
		} else {
			System.out.println("ageUnder18Checkbox is de-selected");
		}
		// Java checkbox
		WebElement javaCheckbox = driver.findElement(By.cssSelector("input#java"));
		javaCheckbox.click();
		if (javaCheckbox.isSelected()) {
			System.out.println("javaCheckbox is selected");
		} else {
			System.out.println("javaCheckbox is de-selected");
		}

	}

@Test
public void TC_04_MailChimp() {
	driver.get("https://login.mailchimp.com/signup/post");
	// email username textbox
	driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.net");
	sleepInSecond(3);
	
	
	WebElement passwordTextbox = driver.findElement(By.cssSelector("input#new_password"));

	// Check lowercase (viet thuong)
	passwordTextbox.sendKeys("aaa");
	driver.findElement(By.cssSelector("main.c-entryShellBody")).click();
	sleepInSecond(2);
	
	Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.\\38-char.not-completed")).isDisplayed());
	
	// Check uppercase (viet hoa)
	passwordTextbox.clear();
	passwordTextbox.sendKeys("AAA");
	sleepInSecond(2);
	
	Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.\\38-char.not-completed")).isDisplayed());
	
	// Check number
	passwordTextbox.clear();
	passwordTextbox.sendKeys("123456");
	sleepInSecond(2);
	
	Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.\\38-char.not-completed")).isDisplayed());
	
	// Check special char
	passwordTextbox.clear();
	passwordTextbox.sendKeys("!@#$%^&");
	sleepInSecond(2);
	
	Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.\\38-char.not-completed")).isDisplayed());
	
	// Check 8 char
	passwordTextbox.clear();
	passwordTextbox.sendKeys("AAAaaa@#$%^");
	sleepInSecond(2);
	
	Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.\\38-char.completed")).isDisplayed());
	// Check Full
	passwordTextbox.clear();
	passwordTextbox.sendKeys("AAAaaa@#$%^1234");
	sleepInSecond(2);
}

	@AfterClass
	public void afterClass() {
//driver.quit();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}