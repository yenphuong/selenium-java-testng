package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
WebDriver driver;
String projectPath = System.getProperty("user.dir");
String osName = System.getProperty("os.name");
String firstName, lastName, employeeID, editFirstName, editLastName;

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
	firstName = "John";
	lastName = "Cena";
	editFirstName = "Xinh";
	editLastName = "Dep";
	}
@Test
public void TC_01_Textbox_TextArea() {
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	// Nhap username va password
	driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
	
//	 click button dang nhap 
	driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
	
	// Cho 5s de loading
	sleepInSecond(5);
	
	// mo trang addemployee
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
	
	// Nhap du lieu vao man hinh add employee
	driver.findElement(By.cssSelector("input.orangehrm-firstname")).sendKeys(firstName);
	//driver.findElement(By.cssSelector("input.orangehrm-middlename")).sendKeys(middleName);
	driver.findElement(By.cssSelector("input.orangehrm-lastname")).sendKeys(lastName);
	
	// luu gia tri employee id vao bien
	
	employeeID = driver.findElement(By.cssSelector("input.oxd-input.oxd-input--active")).getAttribute("value");
	
	
			
	}
@Test
public void TC_02_() {

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