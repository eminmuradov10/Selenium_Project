import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Locators {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");

		// Use inspect tool and preferably with pointing arrow to locate any field to
		// see
		// their class name, id, name and etc.

		// css selector with index method
		// here there was only 1 element inside login
		// commented out previous statement to make this work
		driver.findElement(By.cssSelector("input[id*='log']:nth-child(1)")).sendKeys("abc");

		// now with name
		driver.findElement(By.name("passwd")).sendKeys("123456");

		// now lets tick that "keep me signed in" with class name, as well as to see the
		// password
		driver.findElement(By.className("lblkeepme")).click();
		driver.findElement(By.className("eye-icon")).click();

		// now lets click sign in
		driver.findElement(By.className("signinbtn")).click();

		// there is a security check happens due to automation
		// lets add a wait time to pass that check in order to see "wrong message and
		// password" message
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		// Print the error message to console
		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("div_login_error")));
		System.out.println(errorMessage.getText());

		// lets get the link behind "forgot password"
		// driver.findElement(By.linkText("Forgot Password?")).click();

		// xpath with index
		// there was 1 element->by itself
		driver.findElement(By.xpath("//input[@type='password'][1]")).sendKeys("123456");

		try {
			Thread.sleep(1000); // Adding a 1-second buffer time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.navigate().back();

		try {
			Thread.sleep(1000); // Adding a 1-second buffer time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.navigate().forward();
		
		try {
			Thread.sleep(1000); // Adding a 1-second buffer time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.quit();

		// css selector and xpath are covered
		// they are formed based on id, name class, tag and link text

		// css selector way:
		// driver.findElement(By.cssSelector("input[id='login1']")).sendKeys("abc");

		// css selector with substring method
		// here it has been told that the id starts with 'log'
		// commented out previous statement to make this work
		// driver.findElement(By.cssSelector("input[id*='log']")).sendKeys("abc");

		// xpath way:
		// driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123456");

		// xpath with substring method
		// here it has been told that the id starts with 'log'
		// commented out previous statement to make this work
		// driver.findElement(By.xpath("//input[contains(@type,'word')]")).sendKeys("1234");

		// parent child travelsal
		// xpath: //parent/child/child1
		// css selector: parent child child

	}

}
