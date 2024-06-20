import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

@SuppressWarnings("unused")
public class Browser_Handling {
	private static final Logger LOGGER = Logger.getLogger(Launch_Browsers.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// To open the Website with Invalid certificate

		// Launch Chrome
		try {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\chromedriver-win64\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();

			options.setAcceptInsecureCerts(true);
			options.addExtensions(new File("C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\SelectorsHub.crx"));
			
			//Setting Proxy
			//Proxy p=new Proxy();
			//p.setHttpProxy("192.168.4.3:4444");
			//options.setCapability("proxy", p);
			
			//Default Download Folder
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", "C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\Online_Downloads");
			options.setExperimentalOption("prefs", prefs);
			
			WebDriver chromeDriver = new ChromeDriver(options);

			chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // Increase timeout
			LOGGER.log(Level.INFO, "Launching Chrome browser");

			chromeDriver.get("https://expired.badssl.com/");

			Thread.sleep(2000); // Wait for 2 seconds
			
			chromeDriver.get("https://www.seleniumhq.org/download/");
			chromeDriver.manage().window().maximize();
			
			// Find the download link using the href attribute
            WebElement downloadLink = chromeDriver.findElement(By.xpath("//a[@href='https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.21.0/selenium-server-4.21.0.jar']"));

            // Use JavaScript to click the link
            JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
            js.executeScript("arguments[0].click();", downloadLink);
            
			Thread.sleep(2000); // Wait for 2 seconds
			
			chromeDriver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_confirm");
			chromeDriver.manage().window().maximize();
			
			//bypass cookies if appears
			WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(120));
			
			Thread.sleep(5000); // Wait for 5 seconds

			WebElement w=chromeDriver.findElement(By.xpath("//iframe[@id='iframeResult']"));
			chromeDriver.switchTo().frame(w);

			//click try button
			chromeDriver.findElement(By.xpath("//button[@onclick='myFunction()']")).click();
			Thread.sleep(2000); // Wait for 2 seconds
			chromeDriver.switchTo().alert().accept();
			//driver.switchTo().alert().dismiss();
			
			Thread.sleep(2000); // Wait for 2 seconds
			
			// It is important to get out of the frame to click "get your website"
			chromeDriver.switchTo().defaultContent();
			chromeDriver.findElement(By.xpath("//a[@id='getwebsitebtn']")).click();
			
			// The number of frames in a website
			List<WebElement> l=chromeDriver.findElements(By.tagName("iframe"));
			System.out.println("No. of frames in webpage="+l.size());
			
			Thread.sleep(2000); // Wait for 2 seconds
			
			chromeDriver.quit(); // Properly end the WebDriver session
			LOGGER.log(Level.INFO, "Closed Chrome browser");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "An error occurred while launching Chrome", e);
		}

		// Launch Firefox
		try {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\geckodriver-win64\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();

			options.setAcceptInsecureCerts(true);
			
			//Setting Proxy
			//Proxy p=new Proxy();
			//p.setHttpProxy("192.168.4.3:4444");
			//options.setCapability("proxy", p);
			
			FirefoxProfile profile = new FirefoxProfile();

			//1. Instructing firefox to use custom download location
	        profile.setPreference("browser.download.folderList", 2);

	        //2. Setting custom download directory
	        profile.setPreference("browser.download.dir", "C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\Online_Downloads");

	        //3. Skipping Save As dialog box
	        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword;application/ms-doc;application/doc;application/pdf;text/plain;application/text;text/xml;application/xml");
			

			options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); // Update this path if Firefox is
																					// installed in a different location
			WebDriver firefoxDriver = new FirefoxDriver(options);

			firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // Increase timeout
			LOGGER.log(Level.INFO, "Launching Firefox browser");

			firefoxDriver.get("https://expired.badssl.com/");

			Thread.sleep(2000); // Wait for 2 seconds
			
			firefoxDriver.get("https://www.seleniumhq.org/download/");
			firefoxDriver.manage().window().maximize();
			
			// Find the download link using the href attribute
            WebElement downloadLink = firefoxDriver.findElement(By.xpath("//a[@href='https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.21.0/selenium-server-4.21.0.jar']"));

            // Use JavaScript to click the link
            JavascriptExecutor js = (JavascriptExecutor) firefoxDriver;
            js.executeScript("arguments[0].click();", downloadLink);
            
            Thread.sleep(2000); // Wait for 2 seconds
			
			firefoxDriver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_confirm");
			firefoxDriver.manage().window().maximize();
			
			//bypass cookies if appears
			WebDriverWait wait = new WebDriverWait(firefoxDriver, Duration.ofSeconds(120));
			
			Thread.sleep(5000); // Wait for 5 seconds

			WebElement w=firefoxDriver.findElement(By.xpath("//iframe[@id='iframeResult']"));
			firefoxDriver.switchTo().frame(w);

			//click try button
			firefoxDriver.findElement(By.xpath("//button[@onclick='myFunction()']")).click();
			Thread.sleep(2000); // Wait for 2 seconds
			firefoxDriver.switchTo().alert().accept();
			//driver.switchTo().alert().dismiss();
			
			Thread.sleep(2000); // Wait for 2 seconds
			
			// It is important to get out of the frame to click "get your website"
			firefoxDriver.switchTo().defaultContent();
			firefoxDriver.findElement(By.xpath("//a[@id='getwebsitebtn']")).click();
			
			// The number of frames in a website
			List<WebElement> l=firefoxDriver.findElements(By.tagName("iframe"));
			System.out.println("No. of frames in webpage="+l.size());
			
			Thread.sleep(2000); // Wait for 2 seconds
			
			firefoxDriver.quit(); // Properly end the WebDriver session
			LOGGER.log(Level.INFO, "Closed Firefox browser");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "An error occurred while launching Firefox", e);
		}

		// Launch Edge
		try {
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\edgedriver-win64\\msedgedriver.exe");
			EdgeOptions options = new EdgeOptions();

			options.setAcceptInsecureCerts(true);
			options.addExtensions(new File("C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\SelectorsHub.crx"));

			//Setting Proxy
			//Proxy p=new Proxy();
			//p.setHttpProxy("192.168.4.3:4444");
			//options.setCapability("proxy", p);
			
			//Default Download Folder
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", "C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\Online_Downloads");
			options.setExperimentalOption("prefs", prefs);
			
			// edgeOptions.addArguments("--headless"); // Uncomment if you want to run in
			// headless mode
			options.addArguments("--disable-gpu"); // Disabling GPU acceleration
			options.addArguments("--disable-extensions"); // Disabling extensions
			options.addArguments("--no-sandbox"); // Disabling sandbox for stability
			WebDriver edgeDriver = new EdgeDriver(options);

			edgeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // Increase timeout
			LOGGER.log(Level.INFO, "Launching Edge browser");

			edgeDriver.get("https://expired.badssl.com/");

			Thread.sleep(2000); // Wait for 2 seconds
			
			edgeDriver.get("https://www.seleniumhq.org/download/");
			edgeDriver.manage().window().maximize();
			
			// Find the download link using the href attribute
            WebElement downloadLink = edgeDriver.findElement(By.xpath("//a[@href='https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.21.0/selenium-server-4.21.0.jar']"));

            // Use JavaScript to click the link
            JavascriptExecutor js = (JavascriptExecutor) edgeDriver;
            js.executeScript("arguments[0].click();", downloadLink);
            
            Thread.sleep(2000); // Wait for 2 seconds
			
			edgeDriver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_confirm");
			edgeDriver.manage().window().maximize();
			
			//bypass cookies if appears
			WebDriverWait wait = new WebDriverWait(edgeDriver, Duration.ofSeconds(120));
			
			Thread.sleep(5000); // Wait for 5 seconds

			WebElement w=edgeDriver.findElement(By.xpath("//iframe[@id='iframeResult']"));
			edgeDriver.switchTo().frame(w);

			//click try button
			edgeDriver.findElement(By.xpath("//button[@onclick='myFunction()']")).click();
			Thread.sleep(2000); // Wait for 2 seconds
			edgeDriver.switchTo().alert().accept();
			//driver.switchTo().alert().dismiss();
			
			Thread.sleep(2000); // Wait for 2 seconds
			
			// It is important to get out of the frame to click "get your website"
			edgeDriver.switchTo().defaultContent();
			edgeDriver.findElement(By.xpath("//a[@id='getwebsitebtn']")).click();
			
			// The number of frames in a website
			List<WebElement> l=edgeDriver.findElements(By.tagName("iframe"));
			System.out.println("No. of frames in webpage="+l.size());
			
			Thread.sleep(2000); // Wait for 2 seconds
			
			edgeDriver.quit(); // Properly end the WebDriver session
			LOGGER.log(Level.INFO, "Closed Edge browser");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "An error occurred while launching Edge", e);
		}

	}

}
