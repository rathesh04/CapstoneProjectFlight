package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Wait {

    public static void main(String[] args) {

        System.out.println("Booking Started");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to website
        driver.get("https://blazedemo.com/");
        driver.manage().window().maximize();
        System.out.println("Browser opened and navigated to BlazeDemo website");

        // Select departure city
        WebElement dep = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fromPort")));
        Select departure = new Select(dep);
        departure.selectByVisibleText("Boston");
        System.out.println("Departure city selected: Boston");

        // Select destination city
        WebElement dest = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("toPort")));
        Select destination = new Select(dest);
        destination.selectByVisibleText("London");
        System.out.println("Destination city selected: London");

        // Click Find Flights
        WebElement findFlight = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='Find Flights']")));
        findFlight.click();
        System.out.println("Find Flights button clicked");

        // Verify flights list displayed
        WebElement flightsTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));

        if (flightsTable.isDisplayed()) {
            System.out.println("Flights list displayed successfully");
        } else {
            System.out.println("Not displayed quitting browser");
            driver.quit();
        }

        // Choose one flight
        WebElement chooseFlight = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//input[@value='Choose This Flight'])[1]")));
        chooseFlight.click();
        System.out.println("Flight selected successfully");

        // Verify purchase page
        boolean purchasePage = driver.getPageSource().contains("Your flight from");

        if (purchasePage) {
            System.out.println("Purchase flight page displayed with flight details");
        } else {
            System.out.println("Purchase flight page not displayed. Exiting Browser");
            driver.quit();
        }

        // Enter passenger details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputName")))
                .sendKeys("Rathesh Prabu");

        driver.findElement(By.id("address")).sendKeys("Valluvar Street");
        driver.findElement(By.id("city")).sendKeys("Chennai");
        driver.findElement(By.id("state")).sendKeys("Tamil Nadu");
        driver.findElement(By.id("zipCode")).sendKeys("600106");

        System.out.println("Passenger details entered successfully");

        // Scroll down
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).perform();

        // Enter payment details
        WebElement cardDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cardType")));
        Select card = new Select(cardDropdown);
        card.selectByVisibleText("American Express");

        driver.findElement(By.id("creditCardNumber")).sendKeys("123456789");

        // Clear month and year
        WebElement month = driver.findElement(By.id("creditCardMonth"));
        month.clear();
        month.sendKeys("27");

        WebElement year = driver.findElement(By.id("creditCardYear"));
        year.clear();
        year.sendKeys("2026");

        driver.findElement(By.id("nameOnCard")).sendKeys("Rathesh Prabu");

        System.out.println("Payment details entered successfully");

        // Checkbox
        WebElement checkbox = driver.findElement(By.id("rememberMe"));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        System.out.println("Remember Me checkbox selected");

        // Purchase Flight
        WebElement purchaseFlight = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='Purchase Flight']")));
        purchaseFlight.click();

        System.out.println("Purchase Flight button clicked");

        // Verify booking confirmation
        boolean confirmation = driver.getPageSource()
                .contains("Thank you for your purchase today!");

        if (confirmation) {
            System.out.println("Flight booking completed successfully");
        }

        driver.quit();
        System.out.println("Browser closed");

        System.out.println("End Of Automation");
    }
}