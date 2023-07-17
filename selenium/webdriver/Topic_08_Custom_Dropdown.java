package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	// Khai bao
	WebDriver driver;
	WebDriverWait explicitWait; // explicit la ro rang, implicit la an y
	Select select;
	JavascriptExecutor jsExecutor;

// vua khai bao vua khoi tao
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) { // Mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else { // Windows
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		// khoi tao driver
		driver = new FirefoxDriver();
		// driver = new ChromeDriver();
		
		// Khởi tạo jsExecutor là 1 interface nên không new mà ép kiểu
		jsExecutor =  (JavascriptExecutor) driver;
		//driver.manage().window().setSize(new Dimension(1366,768));

		// khoi tao wait
		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// Goi ham: 1 ham co the goi ham khac de dung trong cung 1 Class
		// Chọn trong number dropdown
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "7");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "7");
		sleepInSecond(3);
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "5");
		sleepInSecond(3);
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "3");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "3");
		sleepInSecond(3);

		// Chọn trong speed dropdown
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Slower");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
		sleepInSecond(3);
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Fast");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");
		sleepInSecond(3);

		// Chọn trong title dropdown
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Mr.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mr.");
		sleepInSecond(3);
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");
		sleepInSecond(3);

		/*
		 * // Chon so 7 // 1. click va 1 phan tu nao do thuoc dropdown de no xo ra
		 * driver.findElement(By.cssSelector("span#number-button")).click();
		 * 
		 * // 2. Cho cho tat ca cacs item trong dropdown duoc load xong // Luu y khong
		 * dung sleep cung duoc, phai co ham wait de no linh dong // Neu nhu chua tim
		 * thay thi phai tim lai trong khoang time duoc set // Neu nhu tim thay roi thi
		 * khong can phai cho het khoang time nay // bat duoc locator dai dien cho tat
		 * ca cac item
		 * explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.
		 * cssSelector("ul#number-menu div")));
		 * 
		 * // 3.1 Neu item can chon dang hien thi // 3.2 Neu item can chon khong hien
		 * thi thi can cuon chuot xuong - scroll down // 4. Kiem tra text cua item, neu
		 * dung voi cai minh can thi click vao // Viet code de duyet qua tung item va
		 * kiem tra theo dieu kien
		 * 
		 * 
		 * // Luu tru tat ca items lai thi moi duyet qua duoc
		 * 
		 * List<WebElement> allItems =
		 * driver.findElements(By.cssSelector("ul#number-menu div")); // Duyet qua tung
		 * item, lay ra text, kiem tra neu bang cai minh mong muon thi click vao for
		 * (WebElement item : allItems) { // Dung bien item de thao tac trong vong lap
		 * for
		 * 
		 * // Lay text ra String textItem = item.getText();
		 * 
		 * // Kiem tra if (textItem.equals("7")) { // dieu kien boolean, neu dieu kien
		 * dung thi chay vao if // dung thi click vao item.click(); } }
		 * sleepInSecond(3); // Chon so 3
		 */
	}


	public void TC_02_Honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		driver.findElement(By.cssSelector("button.btn.btn-primary.x")).click();
		scrollToElement("div.carousel-item");
		selectItemInCustomDropdown("button#selectize-input", "button#selectize-input+div>a", "CITY L (Đỏ)");
		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CITY L (Đỏ)");
		sleepInSecond(3);
		selectItemInCustomDropdown("button#selectize-input", "button#selectize-input+div>a",
				"HR-V RS (Đỏ cá tính/ trắng ngọc quý phái/ trắng bạc thời trang)");
		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "HR-V RS (Đỏ cá tính/ trắng ngọc quý phái/ trắng bạc thời trang)");
		sleepInSecond(3);
		scrollToElement("div.container");
		select = new Select(driver.findElement(By.cssSelector("select#province")));
		select.selectByVisibleText("TP. Hồ Chí Minh");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "TP. Hồ Chí Minh");
		sleepInSecond(3);
		select.selectByVisibleText("Tuyên Quang");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tuyên Quang");
		sleepInSecond(3);
		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực II");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Khu vực II");
	}
	@Test
	public void TC_06_editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		enterItemInCustomDropdown("input.search", "div.menu span.text", "Algeria");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Algeria");
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public void scrollToElement (String cssLocator) {
		//"arguments[0].scrollIntoView(true);" hàm này là truyền biến element vào argument[0], 
		// muốn scroll lên mép trên thì để scrollIntoView = true, scroll xuống mép dưới thì = false
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
	}

// Viet ham select, khong dung cho dropdown nao cu the (specific)
// Khong cu the, viet chung chung

	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allItems) {
			String textActualItem = item.getText();
			if (textActualItem.equals(textExpectedItem)) {
				item.click();
			}
		}
	}
	public void enterItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
		driver.findElement(By.cssSelector(parentLocator)).clear();
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(textExpectedItem);
		sleepInSecond(1);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allItems) {
			String textActualItem = item.getText();
			if (textActualItem.equals(textExpectedItem)) {
				item.click();
			}
		}
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