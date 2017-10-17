package TestCases;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import Utilities.*;
import PageObjects.*;


public class Sachin {
	WebDriver driver;

	@DataProvider
	public Object[][] Authentication() throws Exception{
		Object[][] testObjArray = ExcelUtils.getTableArray("src/TestData/TestData.xlsx","Sheet1");
		return (testObjArray);
	}


	@Test(dataProvider = "Authentication")
	public void Login(String userName, String password) {
		LogInPage.userName(driver).sendKeys(userName);
		LogInPage.password(driver).sendKeys(password);
	}

	@BeforeMethod
	public void beforeMethod() {
		String exePath = "src/Drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.store.demoqa.com");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
