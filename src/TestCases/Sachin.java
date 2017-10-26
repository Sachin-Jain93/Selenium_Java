package TestCases;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
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
	@Parameters({"browser","weburl"})
	public void setup(String browser, String weburl) {		
		switch(browser){		
		case "firefox":
			CommonLibrary.openFireFoxBrowser(weburl);
			break;
		case "ie":
			CommonLibrary.openIEBrowser(weburl);
			break;
		case "chrome":
			CommonLibrary.openChromeBrowser(weburl);
			break;	
		}
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
