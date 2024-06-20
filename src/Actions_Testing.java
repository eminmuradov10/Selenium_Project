import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions_Testing {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver = null;
		try {
			driver = WebDriverSetup.getFirefoxDriver();
			driver.get("https://www.amazon.com");

			// Remove WebDriver detection
			((JavascriptExecutor) driver)
					.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

			// Explicit wait for better synchronization for certain elements
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

			driver.manage().window().maximize();

			// Dismiss the alert
			dismissAlertIfPresent(driver, wait);

			// To inspect the hovering menu, it is important to note how mouse is able to
			// trigger that menu
			// In this example, when mouse pointer is moved to the button of Accounts&List,
			// it is triggering that drop-down menu
			// So Inspection should start from inspecting that button, which would yield the
			// class name
			// Don't try to inspect the opened menu as a result of hovering, as it is
			// triggered by hovering Account&List
			// Move your mouse to hover over the element that causes the hidden menu to
			// appear, and that button is what you need for inspection

			// Accounts&List Hidden Menu
			Actions a = new Actions(driver);
			a.moveToElement(driver.findElement(
					By.xpath("//span[contains(@class, 'nav-line-2') and contains(text(), 'Account & Lists')]"))).build()
					.perform();

			// Other Possible Solutions
			// a.moveToElement(driver.findElement(By.xpath("//span[@class='nav-line-2
			// ']"))).build().perform();
			// a.moveToElement(driver.findElement(By.xpath("//span[contains(text(), 'Account
			// & Lists')]"))).build().perform();

			// Additional wait for 5 seconds
			waitFor(5000);

			// Click on "Today's Deals"
			WebElement elementToHover = driver.findElement(By.xpath("//a[contains(text(), \"Today's Deals\")]"));
			a.moveToElement(elementToHover).click().build().perform();

			// Wait for the page to load completely
			waitForPageToLoad(driver, wait);

			// Additional wait for 5 seconds
			waitFor(5000);

			driver.navigate().back();
			
			// Locate the search box
            WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
            searchBox.click();

            // Use the humanType function to type "pen driver"
            humanType(searchBox, "pen driver");

            // Perform double click action on the search box
            a.moveToElement(searchBox).doubleClick().build().perform();
            
            // Additional wait for 2 seconds
         	waitFor(2000);
         	
         	// Clear the search box
            searchBox.clear();
            
         	// Dismiss the context menu by sending the ESC key
            a.sendKeys(Keys.ESCAPE).perform();
            
            // Additional wait for 1 seconds
         	waitFor(1000);
            
         	// Again moving to that searchbox and typing with large letters
            a.moveToElement(searchBox).click().perform();

            // Use the humanType function to type "PEN DRIVER"
            humanTypeWithShift(searchBox, "pen driver");
            
            // 2nd Solution
            //a.moveToElement(driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"))).click().keyDown(Keys.SHIFT).sendKeys("pen driver").build().perform();
            
            // Additional wait for 2 seconds
         	waitFor(2000);

            // Delete the text by sending backspace keys with Ctrl+A then backspace
            searchBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
            
         	// Dismiss the context menu by sending the ESC key
            a.sendKeys(Keys.ESCAPE).perform();
            
            
            // Open Right Click Menu on Today's Deals
            WebElement Today = driver.findElement(By.xpath("//a[contains(text(), \"Today's Deals\")]"));
            a.moveToElement(Today).contextClick().build().perform();
            
            // Additional wait for 2 seconds
         	waitFor(2000);
         	
         	
         	// Additional wait for 2 seconds
         	waitFor(2000);
            
            // Drag and Drop
            driver.get("https://jqueryui.com/droppable/");
    		driver.manage().window().maximize();
    		
    		// Getting the frame then switching
    		WebElement w=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
    		driver.switchTo().frame(w);

    		// Drag and Drop elements are inside of a frame, 
    		a.dragAndDrop(driver.findElement(By.xpath("//div[@id='draggable']")), driver.findElement(By.xpath("//div[@id='droppable']"))).build().perform();

		} finally {
			// Keep the browser open for a few seconds to observe the results
			waitFor(5000);
			if (driver != null) {
				driver.quit();
			}
		}

	}

	private static void dismissAlertIfPresent(WebDriver driver, WebDriverWait wait) {
		try {
			WebElement dismissButton = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//span[contains(@class, 'glow-toaster-button-dismiss')]//input[@type='submit']")));
			dismissButton.click();
		} catch (Exception e) {
			System.out.println("Dismiss button not found or not clickable");
		}
	}

	private static void waitFor(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void waitForPageToLoad(WebDriver driver, WebDriverWait wait) {
		/*
		 * wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
		 * .executeScript("return document.readyState").equals("complete"));
		 */
		/*
		 * wait.until((ExpectedCondition<Boolean>) wd -> { JavascriptExecutor jsExecutor
		 * = (JavascriptExecutor) wd; String readyState = (String)
		 * jsExecutor.executeScript("return document.readyState"); return
		 * readyState.equals("complete"); });
		 */
		
		 // Define the ExpectedCondition with an anonymous class
        ExpectedCondition<Boolean> pageLoadConditionAnonymous = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver wd) {
            	 JavascriptExecutor jsExecutor = (JavascriptExecutor) wd;
                 String readyState = (String) jsExecutor.executeScript("return document.readyState");
                 if (!readyState.equals("complete")) {
                     System.out.println("Document is not ready yet (anonymous class)");
                     return false;
                 }
                 System.out.println("Document is ready (anonymous class)");
                 return true;
            }
        };

        // Use the defined ExpectedCondition with wait.until (anonymous class)
        wait.until(pageLoadConditionAnonymous);

        
        // Define the ExpectedCondition with a lambda expression
        ExpectedCondition<Boolean> pageLoadCondition = (ExpectedCondition<Boolean>) wd -> {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) wd;
            String readyState = (String) jsExecutor.executeScript("return document.readyState");
            if (!readyState.equals("complete")) {
                System.out.println("Document is not ready yet (lambda expression)");
                return false;
            }
            System.out.println("Document is ready (lambda expression)");
            return true;
        };
        
        // Use the defined ExpectedCondition with wait.until (lambda expression)
		wait.until(pageLoadCondition);
		
		// Perform further actions now that the document is fully loaded
        System.out.println("Page is fully loaded using both conditions (anonymous class and lambda expression)");
		
		

	}
	
	private static void humanType(WebElement element, String text) throws InterruptedException {
        Random rand = new Random();
        boolean typedCorrectly = false;
        
        for (int attempt = 0; attempt < 3; attempt++) {
            element.clear(); // Clear the input field before typing
            Thread.sleep(500); // Wait a bit to ensure the field is cleared
            
            for (char c : text.toCharArray()) {
                element.sendKeys(String.valueOf(c));
                Thread.sleep(100 + rand.nextInt(100)); // Random delay to simulate human typing speed
            }
            
            // Verify if the text was typed correctly
            if (element.getAttribute("value").equals(text)) {
                typedCorrectly = true;
                break;
            }
        }
        
        if (!typedCorrectly) {
            throw new RuntimeException("Failed to type the text correctly after multiple attempts");
        }
    }
	
	// Mockup humanTypeWithShift method to simulate typing with the SHIFT key held down
    public static void humanTypeWithShift(WebElement element, String text) {
        for (char c : text.toCharArray()) {
            element.sendKeys(Keys.SHIFT, String.valueOf(c));
            try {
                Thread.sleep(100); // Add a small delay to simulate human typing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
