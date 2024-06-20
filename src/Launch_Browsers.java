import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Launch_Browsers {

    private static final Logger LOGGER = Logger.getLogger(Launch_Browsers.class.getName());

    public static void main(String[] args) {
        // Launch Chrome
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\chromedriver-win64\\chromedriver.exe");
            WebDriver chromeDriver = new ChromeDriver();
            chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // Increase timeout
            LOGGER.log(Level.INFO, "Launching Chrome browser");
            chromeDriver.get("https://www.elborweltech.com");
            Thread.sleep(5000); // Wait for 5 seconds
            chromeDriver.quit(); // Properly end the WebDriver session
            LOGGER.log(Level.INFO, "Closed Chrome browser");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while launching Chrome", e);
        }

        // Launch Firefox
        try {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\geckodriver-win64\\geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");  // Update this path if Firefox is installed in a different location
            WebDriver firefoxDriver = new FirefoxDriver(options);
            firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // Increase timeout
            LOGGER.log(Level.INFO, "Launching Firefox browser");
            firefoxDriver.get("https://www.elborweltech.com");
            Thread.sleep(5000); // Wait for 5 seconds
            firefoxDriver.quit(); // Properly end the WebDriver session
            LOGGER.log(Level.INFO, "Closed Firefox browser");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while launching Firefox", e);
        }
        
        // Launch Edge
        try {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\edgedriver-win64\\msedgedriver.exe");
            EdgeOptions edgeOptions = new EdgeOptions();
            // edgeOptions.addArguments("--headless"); // Uncomment if you want to run in headless mode
            edgeOptions.addArguments("--disable-gpu"); // Disabling GPU acceleration
            edgeOptions.addArguments("--disable-extensions"); // Disabling extensions
            edgeOptions.addArguments("--no-sandbox"); // Disabling sandbox for stability
            WebDriver edgeDriver = new EdgeDriver(edgeOptions);
            edgeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // Increase timeout
            LOGGER.log(Level.INFO, "Launching Edge browser");
            edgeDriver.get("https://www.elborweltech.com");
            Thread.sleep(5000); // Wait for 5 seconds
            edgeDriver.quit(); // Properly end the WebDriver session
            LOGGER.log(Level.INFO, "Closed Edge browser");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while launching Edge", e);
        }
    }
}
