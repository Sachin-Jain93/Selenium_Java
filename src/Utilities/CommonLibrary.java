package Utilities;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.Assert;

public class CommonLibrary {

	public static WebDriver driver;

	/* This method is used for Opening FireFox Driver 
	 * and any URL in the Browser */
	public static void openFireFoxBrowser(String URL){
		driver = new FirefoxDriver();
		driver.get(URL);
		driver.manage().window().maximize();
	} 
	
	public static void openChromeBrowser(String URL){
		System.setProperty("webdriver.chrome.driver", "src/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
	}

	public static void openIEBrowser(String URL){
		System.setProperty("webdriver.ie.driver", "src/Drivers/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.get(URL);
		driver.manage().window().maximize();
	}

	//This method is used for verifying the title of any WebPage
	public static void openWeb(String URL){
		driver.get(URL);
	}

	public static void sleepApp(int time) throws Exception{
		Thread.sleep(time);
	}

	public static void verifyTitle(String title){
		String actualTitle = driver.getTitle();
		if(title.equals(actualTitle)){
			System.out.println("Correct Title is Displaying" );
		}
		else{
			System.out.println("Expected Title :" +title +" is NOT matching Actual Tile " +actualTitle);
		}
	}

	public static void verifyCurrentURL(String URL){
		String actualURL = driver.getCurrentUrl();
		if(actualURL.equals(URL)){
			System.out.println("Correct URL is displaying" );
		}
		else{
			System.out.println("Expected URL is " +URL + "Not matching actual URL " +actualURL);
		}
	}

	public static void enterText(By locator, String text){
		findElementByAnyLocator(locator).sendKeys(text);
	}

	public static void clickAtAnyElement(By locator){
		findElementByAnyLocator(locator).click();
	}
	public static WebElement findElementByAnyLocator(By locator ){
		WebElement we = driver.findElement(locator);
		return we;
	}

	public static WebElement findElementById(String id){
		return driver.findElement(By.id(id));
	}

	public static WebElement findElementByxpath(String xpath){
		return driver.findElement(By.xpath(xpath));
	}

	public static WebElement findElementByCSS(String css){
		return driver.findElement(By.cssSelector(css));
	}

	/*public static WebElement findElement(By locator){
		return driver.findElement(locator);
	}
	 */
	
	public static void clickUsingID(String id){
		findElementById(id).click();
	}

	public static void clickUsingCSS(String css){
		findElementByCSS(css).click();
	}

	public static void clickUsingXpath(String xpath){
		findElementByxpath(xpath).click();
	}

	public static WebElement findElement (By locator){
		return driver.findElement(locator);
	}

	public static void typeText(By locator , String value){
		findElement(locator).sendKeys(value);
	}

	public static boolean verifyText(By locator , String expectedText){

		String actualText = findElement(locator).getText();
		if(actualText.equals(expectedText))
			return true;
		else
			return false;
	}

	public static void mouseHover(By locator){
		Actions obj = new Actions(driver);
		obj.moveToElement(findElement(locator)).build().perform();

	}

	public static void doubleClick(By locator){
		Actions obj = new Actions(driver);
		obj.doubleClick(findElementByAnyLocator(locator)).build().perform();
	}

	public static void dragAndDrop(By locator , int x , int y){
		Actions obj = new Actions(driver);
		obj.dragAndDropBy(findElementByAnyLocator(locator), x, y).perform();
	}


	public static void pressAnyKey(Keys keys){
		Actions a = new Actions(driver);
		a.keyDown(keys);
		a.keyUp(keys);
	}

	public static void moveToAnyElementAndClick(By locator){
		Actions a = new Actions(driver);
		a.moveToElement(findElementByAnyLocator(locator)).click(findElement(locator)).build().perform();
	}

	public static void moveToAnyElementByOffset(By locator , int x , int y){
		Actions a = new Actions(driver);
		a.moveToElement(findElementByAnyLocator(locator), x, y);
	}

	public static void implicitWait(int waitSeconds){
		driver.manage().timeouts().implicitlyWait(waitSeconds, TimeUnit.SECONDS);
	}

	public static void explicitWaitForElement(By locator ,int wait){
		WebDriverWait ww = new WebDriverWait(driver, wait);
		ww.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void explicitWaitForAlert(){ 
		WebDriverWait w1 = new WebDriverWait(driver, 10);
		w1.until(ExpectedConditions.alertIsPresent());
	}

	public static void checkAnyCheckBox(By locator){
		if(findElementByAnyLocator(locator).isSelected()){
		}
		else{
			findElementByAnyLocator(locator).click();
		}
	}

	public static void unCheckAnyCheckBox(By locator){
		if(findElementByAnyLocator(locator).isSelected()){
			findElementByAnyLocator(locator).click();
		}
	}

	public static void selectValuesFromDropDownByIndex(By locator , int index){
		if(findElementByAnyLocator(locator).isSelected()){
			System.out.println("Value from Drop down is already selected" );
		}
		else{	
			Select dropdown = new Select(findElementByAnyLocator(locator));
			dropdown.selectByIndex(index);
		}
	}

	public static void selectValuesFromDropDownByValue(By locator , String index){
		if(findElementByAnyLocator(locator).isSelected()){
			System.out.println("Value from Drop down is already selected" );
		}
		else{	
			Select dropdown = new Select(findElementByAnyLocator(locator));
			dropdown.selectByValue(index);
		}
	}
	public static void selectValuesFromDropDownByVisibleText(By locator , String index){
		Select dropdown = new Select(findElementByAnyLocator(locator));
		dropdown.selectByVisibleText(index);
	}

	public static boolean verifyElementDisplayed(By locator){
		return findElementByAnyLocator(locator).isDisplayed();
	}

	public static boolean verifyElementEnabled(By locator){
		return findElementByAnyLocator(locator).isEnabled();
	}

	public static boolean verifyElementSelected(By locator) {
		return findElementByAnyLocator(locator).isSelected();
	}

	public static void navigate(String URL){
		driver.navigate().to(URL);
	}

	public static void navigateBack(){
		driver.navigate().back();
	}

	public static void navigateForward(){
		driver.navigate().forward();
	}

	public static void refreshBrowser(){
		driver.navigate().refresh();
	}

	public static void storePopUpText(){
		System.out.println(driver.switchTo().alert().getText());
	}

	public static void acceptPopUp(){
		driver.switchTo().alert().accept();
	}

	public static void dismissPopUp(){
		driver.switchTo().alert().dismiss();
	}

	public static void getCSSValue(By locator, String valueToBeFetched){
		String S1 = findElementByAnyLocator(locator).getCssValue(valueToBeFetched);
		System.out.println(valueToBeFetched +"is" +S1);
	}

	public static void killBrowser(){
		driver.quit();
	}

	public static void maximzeBrowser(){
		driver.manage().window().maximize();
	}

	public static boolean elementPresent(By locator){ 
		boolean isPresent = driver.findElements(locator).size()!=0;
		if(isPresent){
			return true;
		}
		else
		{
			return false;
		}
	}

	public static void findAllLinks(){
		List<WebElement> we = driver.findElements(By.tagName("a"));
		System.out.println("There are " +we.size() + "Links in this page");

		for(int i=0; i<we.size();i++){
			System.out.println("Name of WebElement is " +we.get(i).getText());
			System.out.println("Type attribute of Element is "  +we.get(i).getAttribute("type"));
		}
	}

	public static void Exp(int timeout, By locator){
		WebDriverWait ww = new WebDriverWait(driver, timeout);
		ww.until(ExpectedConditions.alertIsPresent());
	}

	public static void moveSlider(By locator , int x , int y){
		Actions a = new Actions(driver);
		a.dragAndDropBy(findElementByAnyLocator(locator), x, y).build().perform();
	}

	public static void moveSliderBy(By locator , int x , int y){
		new Actions(driver).clickAndHold(findElementByAnyLocator(locator)).moveByOffset(x, y).release().perform();
	}

	public static void datePicker(By locator ,String date){
		String temp = getLocatorText(locator);
		((JavascriptExecutor) driver).executeScript("$('"+temp+"').val('"+date+"')");
	}
	public static String getLocatorText(By locator) {
		String result = "";
		if (locator.toString().toLowerCase().contains("by.selector"))
			result = locator.toString().substring(13);
		else if (locator.toString().toLowerCase().contains("by.cssselector"))
			result = locator.toString().substring(16);
		else if (locator.toString().toLowerCase().contains("by.id"))
			result = "#" + locator.toString().substring(7);
		return result;
	}
}