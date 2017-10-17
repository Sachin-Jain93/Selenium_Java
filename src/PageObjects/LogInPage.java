package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInPage {
	private static WebElement element = null;

	public static WebElement userName(WebDriver driver){
		element = driver.findElement(By.id("username"));
		return element;
	}

	public static WebElement password(WebDriver driver){
		element = driver.findElement(By.id("password"));
		return element;
	}
	
	public static WebElement loginButton(WebDriver driver){
		element = driver.findElement(By.id("Login"));
		return element;
	}


}
