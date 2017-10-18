package Utilities;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CrossBrowser {
	public static WebDriver driver;
	private enum browsertype {
		IE, FF, CHROME, SAFARI;
	}

	/**
	 * open browser for requested browser
	 */
	@SuppressWarnings("null")
	public static void openBrowser(String vBrowser, String vPlatform, String vHubUrl) throws IOException {
		browsertype browserType = browsertype.valueOf(vBrowser);
		DesiredCapabilities capability = null;
		switch (browserType) {
		case IE:
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			capability.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capability.setJavascriptEnabled(true);
			/**
			 * IE not supporting to frames. So, we have to use below
			 * capabilities
			 **/
//			if (!(moduleName.equalsIgnoreCase("Registration"))) {
//				capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//				capability.setCapability("ignoreZoomSetting", true);
//				capability.setCapability("nativeEvents", false);
//			}
			break;

		case CHROME:
			capability = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("start-maximized");
			capability.setCapability(ChromeOptions.CAPABILITY, options);
			String exePath = "src/Drivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", exePath);
			break;
			
		case FF:
			FirefoxProfile myprofile=new FirefoxProfile();
			myprofile.setPreference("browser.download.folderList", 2);
			myprofile.setPreference("browser.download.manager.showWhenStarting", false);
			myprofile.setPreference("browser.download.dir", "C:\\Users\\user\\Downloads");
			myprofile.setPreference("browser.helperApps.neverAsk.openFile","application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
			myprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
			myprofile.setPreference("browser.helperApps.alwaysAsk.force", false);
			myprofile.setPreference("browser.download.manager.showAlertOnComplete", false);
			myprofile.setPreference("browser.download.manager.closeWhenDone", false);
			capability.setCapability(FirefoxDriver.PROFILE, myprofile);
			capability = DesiredCapabilities.firefox();
			break;
			
		case SAFARI:
			capability = DesiredCapabilities.safari();
			break;
		}
		
		driver = new RemoteWebDriver(new URL(vHubUrl), capability);
		if (vPlatform.equalsIgnoreCase("Mac")) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			int Width = (int) toolkit.getScreenSize().getWidth();
			int Height = (int) toolkit.getScreenSize().getHeight();
			driver.manage().window().setSize(new Dimension(Width, Height));
		}
		driver.manage().window().maximize();
	}
}