package webdriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_02_Selenium_By {
//	- Bước 1: Mở browser lên
//	- Bước 2: Nhập vào Url
//	- Bước 3: Click vào My Account để mở trang login ra
//	- Bước 4: Click login
//	- Bước 5: Verify lỗi hiển thị
//	- Bước 6: Đóng browser
	
// Khai báo 1 biến để đại diện cho thư viện Selenium WebDriver
WebDriver driver;
String projectPath = System.getProperty("user.dir");
@BeforeClass
public void beforeClass() {
	//Bước 1: Mở browser lên
System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
driver = new FirefoxDriver();
// Bấm cho maximize browser lên
driver.manage().window().maximize();
	}
@Test
public void TC_01_() {
// Bước 2: Nhập vào Url
	driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	
//	- Bước 3: Click vào My Account để mở trang login ra
	// HLML của Element Email Textbox
//	<input 
//	type="email" 
//	autocapitalize="none" autocorrect="off" 
//	spellcheck="false" name="login[username]" 
//	value="" id="email" 
//	class="input-text required-entry validate-email" title="Email Address">
	// input <= thẻ của element này (tagname)
	// attribute name - type autocapitalize autocorrect spellcheck...
	// atrribute value - email none off...
	
	// Xpath
	//tagname[@attribute-name='attribute-value']
	//input[@type='email']
	//input[@autocapitalize='none']
	//input[@autocorrect='off']
	//input[@spellcheck='false']
	//input[@name='login[username]'] *
	//input[@id='email'] *
	//input[@class='input-text required-entry validate-email']
	//input[@title='Email Address'] *
	
	//CSS Format: tagname[attribute-name='attribute-value']
	
	
// ID - Email Textbox
	driver.findElement(By.id("email"));
	
// Class - New user form
	// Có 2 trường hợp
	// 1. Giá trị không có khoảng trắng: lấy hết
	// 2. Giá trị có khoảng trắng: lấy 1 phần
	driver.findElement(By.className("new-users"));
	
// Name - Email Textbox
	driver.findElement(By.name("login[username]"));
	
// Tagname - Tìm xem có bao nhiêu element/page
	driver.findElements(By.tagName("a"));
	
//	LinkText - Link - Text tuyệt đối
	driver.findElement(By.linkText("SEARCH TERMS"));
//	Partial LinkText - Link - tương đối
	driver.findElement(By.partialLinkText("SEARCH TERMS"));
	driver.findElement(By.partialLinkText("RCH TERMS"));
	driver.findElement(By.partialLinkText("TERMS"));
	driver.findElement(By.partialLinkText("SEARCH"));
//	Css - Cover được hết cả 6 loại trên
	driver.findElement(By.cssSelector("input[name='login[username]']"));
	driver.findElement(By.cssSelector("input[id='email']"));
	driver.findElement(By.cssSelector("input[title='Email Address']"));
//	Xpath
	driver.findElement(By.xpath("//input[@name='login[username]']"));
	driver.findElement(By.xpath("//input[@id='email']"));
	driver.findElement(By.xpath("//input[@title='Email Address']"));
	
	}

//public void afterClass() {
//driver.quit();
//	}
}