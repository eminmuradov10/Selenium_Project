import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Synchronization {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		// Sometimes the element that you are looking for might not be there
		// Using Thread.sleep(10000) is an inefficient way as some elements might be there in 1-2 seconds
		
		// Implicit way: Setting a global waiting time
		// Explicit way: Setting a certain amount of wait time for 1 WebElement
		// FLuent way: Special type of Explicit way
			// There are polling and total time
			// WebElement is checked among the intervals of polling time through total time period
		
		WebDriver driver = null;
		
		driver =  WebDriverSetup.getEdgeDriver();
        driver.get("https://www.justwatch.com/in/movies");

        // Remove WebDriver detection
        ((JavascriptExecutor) driver).executeScript(
            "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );
        
        driver.manage().window().maximize();

        // Implicit wait
        // Global way
        // Here all the WebElements will wait for maximum of 5 seconds for item to be present
        // Otherwise it will produce "No such element"
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		// Explicit wait for better synchronization
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        
        
        
        // Click on the button with the text "Genres"
        driver.findElement(By.xpath("//div[@class='chip-button filter-bar-seo__additional--long']//button[contains(text(), 'Genres')]")).click();
		//driver.findElement(By.xpath("//div[@class='hidden-horizontal-scrollbar scrollbar']//div[2]//button[1]")).click();
		
        // Explicit wait
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='popover-content sc-ion-popover-ios']")));
		
		try {
		    Thread.sleep(500);  // Wait about 500 milliseconds
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		// Click on the button with the text "Made in Europe"
		driver.findElement(By.xpath("//div[@class='chip-button toggle-button']//button[contains(., 'Made in Europe')]")).click();
		//driver.findElement(By.xpath("//div[@class='columns']//div[1]//button[1]")).click();
		
		try {
		    Thread.sleep(500);  // Wait about 500 milliseconds
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		// Click on the button with the text "Drama"
		driver.findElement(By.xpath("//div[@class='chip-button toggle-button']//button[contains(., 'Drama')]")).click();
		//driver.findElement(By.xpath("//div[@class='columns']//div[2]//button[1]")).click();
		
		// Use JavaScript to click the ion-backdrop
		// WebElement backdrop = driver.findElement(By.xpath("//ion-backdrop']"));
		WebElement backdrop = driver.findElement(By.xpath("//ion-backdrop[@class='sc-ion-popover-ios ios hydrated']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", backdrop);
		
		try {
		    Thread.sleep(500);  // Wait about 500 milliseconds
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		// Send the "Escape" key to close the "Genres" menu, 2nd solution for ion-backdrop
		//driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
		
		
		
		
		// Click on the "Rating" button
		driver.findElement(By.xpath("//div[@class='chip-button filter-bar-seo__additional--long']//button[contains(text(), 'Rating')]")).click();
		//driver.findElement(By.xpath("//div[@class='hidden-horizontal-scrollbar scrollbar']//div[4]//button[1]")).click();
		
		// Wait on the "Rating" menu to appear
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='popover-content sc-ion-popover-ios']")));

		// Locate the slider element using a more specific XPath
		WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ion-range[@class='inverted-color md range-has-pin hydrated']")));

		// Set initial and target values
		double currentValue = 0; // Initial value of the slider
		double targetValue = 8.5; // Target value of the slider
		int steps = 100; // Number of steps to gradually change the slider value
		double increment = (targetValue - currentValue) / steps; // Calculate the value increment for each step

		// Hold the knob for a bit less than 1 second (900 milliseconds)
		((JavascriptExecutor) driver).executeScript(
		    "let slider = arguments[0]; " + // Assign the slider element to the `slider` variable in JavaScript
		    "slider.shadowRoot.querySelector('.range-knob-handle').classList.add('range-knob-pressed');", // Simulate pressing the knob
		    slider // Pass slider as an argument to the JavaScript code
		);

		try {
		    Thread.sleep(900);  // Hold the knob for 900 milliseconds
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}

		// Gradually change the slider value in small steps
		for (int i = 1; i <= steps; i++) {
		    double newValue = currentValue + (i * increment); // Calculate the new value for the current step
		    
		    ((JavascriptExecutor) driver).executeScript(
		        // JavaScript code to update the slider value and simulate the visual effects
		        "let slider = arguments[0]; " + // Assign the slider element to the `slider` variable in JavaScript
		        "slider.value = arguments[1]; " + // Set the slider value to the new value
		        // Dispatch ionInput event to simulate real-time updates as the knob is being dragged
		        "slider.dispatchEvent(new CustomEvent('ionInput', { detail: { value: arguments[1] }, bubbles: true })); " +
		        // Dispatch ionChange event to simulate the final change when the knob is released
		        "slider.dispatchEvent(new CustomEvent('ionChange', { detail: { value: arguments[1] }, bubbles: true }));", 
		        slider, newValue // Pass slider and newValue as arguments to the JavaScript code
		    );
		    
		    try {
		        // Vary the delay to simulate human-like sliding
		        long delay = (long) (20 + (Math.random() * 30)); // Random delay between 20ms and 50ms
		        Thread.sleep(delay);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}

		// Release the knob after reaching the target value
		((JavascriptExecutor) driver).executeScript(
		    "let slider = arguments[0]; " + // Assign the slider element to the `slider` variable in JavaScript
		    "slider.shadowRoot.querySelector('.range-knob-handle').classList.remove('range-knob-pressed');", // Simulate releasing the knob
		    slider // Pass slider as an argument to the JavaScript code
		);

		// Optionally, wait for the value change to reflect in the input field
		WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='aux-input']")));
		wait.until(ExpectedConditions.attributeToBe(input, "value", String.valueOf(targetValue))); // Wait until the input value matches the target value

		try {
		    Thread.sleep(500);  // Wait about 500 milliseconds
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		// Use JavaScript to click the ion-backdrop
		// WebElement backdrop = driver.findElement(By.xpath("//ion-backdrop']"));
		backdrop = driver.findElement(By.xpath("//ion-backdrop[@class='sc-ion-popover-ios ios hydrated']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", backdrop);
		
		
		
		
		// Fluent wait to click on the "Age" button and wait for the "Age" menu to appear
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofSeconds(2))
            .ignoring(NoSuchElementException.class);
        
        // Click on the "Age" button
        WebElement ageButton = fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@class='chip-button filter-bar-seo__additional--long']//button[contains(text(), 'Age')]"));
            }
        });
        ageButton.click();
        
        // Wait on the "Age" menu to appear
        fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@class='popover-content sc-ion-popover-ios']"));
            }
        });
        
        // Click U
        fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@class='chip-button toggle-button']//button[contains(., 'U')]"));
            }
        }).click();
        
        try {
		    Thread.sleep(500);  // Wait about 500 milliseconds
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
        
        // Click UA
        fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@class='chip-button toggle-button']//button[contains(., 'UA')]"));
            }
        }).click();
        
        try {
		    Thread.sleep(500);  // Wait about 500 milliseconds
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
        
        // Use JavaScript to click the ion-backdrop
     	// WebElement backdrop = driver.findElement(By.xpath("//ion-backdrop']"));
        backdrop = fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//ion-backdrop[@class='sc-ion-popover-ios ios hydrated']"));
            }
        });
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", backdrop);
        
        try {
		    Thread.sleep(2000);  // Wait about 2 seconds
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
        
        driver.quit();
        
        
	}

}
