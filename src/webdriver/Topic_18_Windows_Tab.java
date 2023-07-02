package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Windows_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Github_With_Two_Window_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// lay ID cua trang github, tra ve so it, chi 1 ID
		String githubID = driver.getWindowHandle();
		System.out.println("Github ID = " + githubID);

		// Click vao google link
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		System.out.println("Page title - Github = " + driver.getTitle());

		// driver van dang o github, phai chuyen qua google moi thao tac duoc
		// Tra ve ID cua tat ca cac tab/windows dang co
		switchToWindowByID(githubID);

		// title moi la google, gettitle
		System.out.println("Page title - Google = " + driver.getTitle());

		// Quay lai trang github
		// Tra ve id cua driver dang dung o do
		String googleID = driver.getWindowHandle();
		System.out.println("Google ID = " + googleID);
		switchToWindowByID(googleID);
		System.out.println("Page title - Github = " + driver.getTitle());
	}

	@Test
	public void TC_02_Github_Greater_Two_Window_Tab() {
		//Driver đang ở trang Github
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Lay parent id
		String githubID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		
		// Vào dev tool -> console -> type "document.title" để lấy title
		// Chuyen qua trang Google
		switchToWindowByTitle("Google");
		System.out.println("Page Title - Google = " + driver.getTitle());
		
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Automation FC");
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(Keys.ENTER);
		sleepInSecond(3);
		
		// Về lại trang Github
		switchToWindowByTitle("Selenium WebDriver");
		System.out.println("Page Title - Github = " + driver.getTitle());
		
		// Qua trang facebook
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Facebook – log in or sign up");
		System.out.println("Page Title - Facebook = " + driver.getTitle());
		driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("12345678");
		
		// Quay ve lai Github
		switchToWindowByTitle("Selenium WebDriver");
		System.out.println("Page Title - Github = " + driver.getTitle());
		
		// Qua Tiki
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		System.out.println("Page Title - Tiki = " + driver.getTitle());
		
		sleepInSecond(3);
		closeAllWindowWithoutParentID(githubID);
		
	}
	// Dung cho 2 tab, 2 windows thoi
	public void switchToWindowByID(String pageID) {
		// Lấy ra tất cả các ID của các tab/window đang có
		Set<String> allIDs = driver.getWindowHandles();

		// So sanh string khong dung phep = nen phai dung ! va equals
		// Dùng vòng lặp để duyệt qua từng item trong Set (allIDs)
		// Nếu item nào khác vs ID truyền vào thì switch vào
		for (String id : allIDs) {
			if (!id.equals(pageID)) {
				driver.switchTo().window(id);
				sleepInSecond(1);
			}
		}
	}
	
	// Dùng cho cả từ 2 tab/windows trở lên
	public void switchToWindowByTitle (String pageTitle) {
		// Lấy ra tất cả các ID của các tab/window đang có
		Set<String> allIDs = driver.getWindowHandles();
		
		// Dùng vòng lặp để duyệt qua từng item
		for (String id : allIDs) {
			// Switch vào từng ID trước
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(pageTitle)) {
				// Thoát khỏi vòng lặp không duyệt item tiếp theo nữa
				break;
			}
		}
	}
	// Close all window khong co parent id
	public void closeAllWindowWithoutParentID (String parentID) {
		Set<String> allIDs = driver.getWindowHandles();
		
		for (String id : allIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
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