// WebDriverSetup.java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverSetup {

    private static WebDriver setUpEdgeDriver() {
        // Set up Edge options to use a realistic user agent
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        System.setProperty("webdriver.edge.driver", "C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\edgedriver-win64\\msedgedriver.exe");
        return new EdgeDriver(options);
    }

    private static WebDriver setUpChromeDriver() {
        // Set up Chrome options to use a realistic user agent
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\chromedriver-win64\\chromedriver.exe");
        return new ChromeDriver(options);
    }

    private static WebDriver setUpFirefoxDriver() {
        // Set up Firefox options to use a realistic user agent
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addPreference("general.useragent.override", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        // Specify the path to the Firefox binary
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\eminm\\Desktop\\My_Files\\Programming_Exercises\\Selenium_Example_Projects\\geckodriver-win64\\geckodriver.exe");
        return new FirefoxDriver(options);
    }

    public static WebDriver getEdgeDriver() {
        return setUpEdgeDriver();
    }

    public static WebDriver getChromeDriver() {
        return setUpChromeDriver();
    }

    public static WebDriver getFirefoxDriver() {
        return setUpFirefoxDriver();
    }
}
