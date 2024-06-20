import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Handling {

    public static void main(String[] args) throws InterruptedException {
        // Perform actions on the 1st website using EdgeDriver
        handleTrainlineWebsite();

        // Perform actions on the 2nd website using ChromeDriver
        handleIRCTCWebsiteWithChrome();
        
        // Perform actions on the 3rd website using EdgeDriver
        handleGoibiboWebsite();
        
        // Perform actions on the 4th website using ChromeDriver
        handleW3SchoolWebsite();
    }

    private static void handleTrainlineWebsite() throws InterruptedException {
        WebDriver driver = null;
        try {
            driver =  WebDriverSetup.getEdgeDriver();
            driver.get("https://www.thetrainline.com/");

            // Remove WebDriver detection
            ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
            );

            // Explicit wait for better synchronization
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

            // Maximize window
            driver.manage().window().maximize();

            // Handle cookie consent popup if present
            acceptCookiesIfPresent(driver, wait);

            // Wait for the first drop-down menu, From Menu
            WebElement fromMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='jsf-origin-input']")));
            fromMenu.click();
            humanType(fromMenu, "Antwerp");

            // Wait for the dropdown options to appear and become clickable
            WebElement fromOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Antwerpen-B')]/ancestor::li[@role='option']")));
            performClickWithRetry(driver, fromOption);

            // Wait for the second drop-down menu, To Menu
            WebElement toMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='jsf-destination-input']")));
            toMenu.click();
            humanType(toMenu, "Leuven");

            // Wait for the dropdown options to appear and become clickable
            WebElement toOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Leuven')]/ancestor::li[@role='option']")));
            performClickWithRetry(driver, toOption);

        }  finally {
        	// Keep the browser open for a few seconds to observe the results
        	Thread.sleep(5000);
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void handleIRCTCWebsiteWithChrome() throws InterruptedException {
        WebDriver driver = null;
        try {
            driver = WebDriverSetup.getChromeDriver();
            driver.get("https://www.irctc.co.in/nget/train-search");

            // Remove WebDriver detection
            ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
            );

            // Maximize window
            driver.manage().window().maximize();
            
            // Getting the list of checkboxes
            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='col-xs-12 remove-padding']//div[@class='col-xs-12 remove-padding']"));

            for (WebElement element : elements) {
                System.out.println(element.getText());
            }

            // Select one of the printed options
            driver.findElement(By.xpath("//label[normalize-space()='Train with Available Berth']")).click();


        } finally {
        	// Keep the browser open for a few seconds to observe the results
        	Thread.sleep(5000);
            if (driver != null) {
                driver.quit();
            }
        }
    }
    
    private static void handleGoibiboWebsite() throws InterruptedException {
        WebDriver driver = null;
        try {
        	driver = WebDriverSetup.getEdgeDriver();
            driver.get("https://www.goibibo.com/");

            // Remove WebDriver detection
            ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
            );

            // Wait for the popup to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement popupParent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-id='auth-flow-section']")));

            // Locate the close button within the popup using XPath
            WebElement closeButton = popupParent.findElement(By.xpath(".//span[@class='sc-gsFSXq bGTcbn']//span[@class='logSprite icClose']"));

            Thread.sleep(1000);

            // Click the close button
            closeButton.click();

            // Wait for the first drop-down menu, From Menu
            WebElement fromMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='sc-12foipm-22 kGtxGm']//div[contains(@class,'sc-12foipm-2 eTBlJr fswFld')]")));
            fromMenu.click();
            
            // Wait for the input element to appear and then interact with it
            WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text' and @value='']")));
            humanType(fromInput, "Pune");
            
            // Wait for the dropdown options to appear and become clickable
            WebElement fromOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(.,'Pune, India')]")));
            Thread.sleep(1000); // Add a short wait to ensure the dropdown is fully rendered
            performClickWithRetry(driver, fromOption);

            // Wait for the 2nd drop-down menu, To Menu
            // The following lines are commented out
            // Reason is that after filling the 1st field, it will jump directly to 2nd field
            //WebElement ToMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='sc-12foipm-22 OmQvV']//div[contains(@class,'sc-12foipm-2 eTBlJr fswFld')]")));
            //ToMenu.click();
            
            // Wait for the input element to appear and then interact with it
            WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text']")));
            humanType(toInput, "Nagpur");
            
            // Wait for the dropdown options to appear and become clickable
            WebElement toOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(.,'Nagpur, India')]")));
            Thread.sleep(1000); // Add a short wait to ensure the dropdown is fully rendered
            performClickWithRetry(driver, toOption);

            // Getting the list of checkboxes
            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='sc-12foipm-76 gxVLjc']"));

            for (WebElement element : elements) {
                System.out.println(element.getText());
            }

            // Click on the 'Student' fare option
            clickSpecialFare(driver, "Student");

            // Wait for a few seconds to observe the click action
            Thread.sleep(2000);

            // Click on the 'Senior Citizen' fare option
            clickSpecialFare(driver, "Senior Citizen");

            Thread.sleep(1000);
            
            // Scroll to the 'SEARCH FLIGHTS' button to ensure it's visible
            WebElement searchFlightsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='sc-12foipm-71 cJDpIZ']//span[@class='sc-12foipm-72 ezNmSh']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchFlightsButton);
            Thread.sleep(500); // Wait for a short period to ensure the element is in view

            // Ensure no other elements overlap it and click it
            try {
                searchFlightsButton.click();
            } catch (ElementClickInterceptedException e) {
                // If an ElementClickInterceptedException is caught, log the issue and click using JavaScript as a fallback
                System.out.println("Element click intercepted. Trying with JavaScript click.");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchFlightsButton);
            }
            
            // Wait for the page to load completely
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading']")));

            // Keep the browser open for a few seconds to observe the results

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	// Keep the browser open for a few seconds to observe the results
        	Thread.sleep(5000);
            if (driver != null) {
                driver.quit();
            }
        }
    }
    
    private static void  handleW3SchoolWebsite() throws InterruptedException {
    	WebDriver driver = null;
    	try {
    		driver = WebDriverSetup.getChromeDriver();
    		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_confirm");
    		

            // Remove WebDriver detection
            ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
            );
            
            // Maximize window
            driver.manage().window().maximize();
            
            //bypass cookies if appears
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
            
            
            // Frame Handling
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='iframeResult']")));
            
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Try it']"))).click();
            
            Alert alert = driver.switchTo().alert();
            //driver.switchTo().alert().accept();
            while(true) {
                
            	 if (alert.getText().equals("Press a button!")) {
            		 break;
            	 }
            	 
            	 wait.until(ExpectedConditions.alertIsPresent());
            	 alert = driver.switchTo().alert();
            }
            
            System.out.println("Alert detected: " + alert.getText());
            
            // Wait for a few seconds to observe the focus change
            Thread.sleep(2000);
            System.out.println("Dismissing the alert with 'Cancel' button.");
            
            // Dismiss the alert (equivalent to clicking the Cancel button)
            alert.dismiss();
            System.out.println("Alert dismissed.");
            
            
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
        	// Keep the browser open for a few seconds to observe the results
        	Thread.sleep(5000);
            if (driver != null) {
                driver.quit();
            }
        }
    	
    }
    
    private static void acceptCookiesIfPresent(WebDriver driver, WebDriverWait wait) {
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
            Thread.sleep(1000);
            acceptCookiesButton.click();
        } catch (Exception e) {
            // Cookie consent popup not present, continue with the script
        }
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

    private static void performClickWithRetry(WebDriver driver, WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(element).click().perform();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                element = reFindElement(driver, element);
            }
        }
    }

    private static WebElement reFindElement(WebDriver driver, WebElement element) {
        return driver.findElement(By.xpath(getXPath(driver, element)));
    }

    private static String getXPath(WebDriver driver, WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript(
            "function absoluteXPath(element) {" +
                "var comp, comps = [];" +
                "var parent = null;" +
                "var xpath = '';" +
                "var getPos = function(element) {" +
                "var position = 1, curNode;" +
                "if (element.nodeType == Node.ATTRIBUTE_NODE) {" +
                "return null;" +
                "}" +
                "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {" +
                "if (curNode.nodeName == element.nodeName) {" +
                "++position;" +
                "}" +
                "}" +
                "return position;" +
                "};" +
                "if (element instanceof Document) {" +
                "return '/';" +
                "}" +
                "for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {" +
                "comp = comps[comps.length] = {};" +
                "switch (element.nodeType) {" +
                "case Node.TEXT_NODE:" +
                "comp.name = 'text()';" +
                "break;" +
                "case Node.ATTRIBUTE_NODE:" +
                "comp.name = '@' + element.nodeName;" +
                "break;" +
                "case Node.PROCESSING_INSTRUCTION_NODE:" +
                "comp.name = 'processing-instruction()';" +
                "break;" +
                "case Node.COMMENT_NODE:" +
                "comp.name = 'comment()';" +
                "break;" +
                "case Node.ELEMENT_NODE:" +
                "comp.name = element.nodeName;" +
                "break;" +
                "}" +
                "comp.position = getPos(element);" +
                "}" +
                "for (var i = comps.length - 1; i >= 0; i--) {" +
                "comp = comps[i];" +
                "xpath += '/' + comp.name.toLowerCase();" +
                "if (comp.position !== null) {" +
                "xpath += '[' + comp.position + ']';" +
                "}" +
                "}" +
                "return xpath;" +
            "}" +
            "return absoluteXPath(arguments[0]);", element);
    }
    
    private static void clickSpecialFare(WebDriver driver, String fareType) {
        // Locate the element and click it using a single expression
        driver.findElement(By.xpath(String.format("//label[contains(., '%s')]", fareType))).click();
    }

}
