package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

public class FlightAutomation {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Started The Automation");

        WebDriver driver = new ChromeDriver();

        //Navigate to website
        driver.get("https://blazedemo.com/");
        driver.manage().window().maximize();
        System.out.println("1.Browser opened and navigated to BlazeDemo website");

        //Select departure city
        WebElement dep = driver.findElement(By.name("fromPort"));
        dep.click();
        Thread.sleep(2000);

        Select departure = new Select(dep);
        departure.selectByVisibleText("Boston");
        System.out.println("2.Departure city selected");

        // Select destination city
        WebElement dest = driver.findElement(By.name("toPort"));
        dest.click();
        Thread.sleep(2000);

        Select destination = new Select(dest);
        destination.selectByVisibleText("London");
        System.out.println("3.Destination city selected");

        //Click Find Flights
        driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
        System.out.println("4.Find Flights button clicked");

        Thread.sleep(2000);

        // Verify flights list displayed
        boolean flightsVisible = driver.findElement(By.xpath("//table")).isDisplayed();

        if (flightsVisible) {
            System.out.println("5.Flights list displayed successfully");
        }else {
        	System.out.println("5.Not displayed Quiting browser");
        	driver.quit();
        }

        // Choose one flight
        driver.findElement(By.xpath("(//input[@value='Choose This Flight'])[1]")).click();
        System.out.println("6.Flight selected successfully");
        Thread.sleep(2000);

        // Verify purchase page
        boolean purchasePage = driver.getPageSource().contains("Your flight from");

        if (purchasePage) {
            System.out.println("7.Purchase flight page displayed with flight details");
        }else {
        	System.out.println("7.Purchase flight page not displayed.Exiting Browser");
        	driver.quit();
        }

        //Enter personal details
        Thread.sleep(2000);
        driver.findElement(By.id("inputName")).sendKeys("Rathesh Prabu");
        Thread.sleep(2000);
        driver.findElement(By.id("address")).sendKeys("Valluvar Street");
        Thread.sleep(2000);
        driver.findElement(By.id("city")).sendKeys("Chennai");
        Thread.sleep(2000);
        driver.findElement(By.id("state")).sendKeys("Tamil Nadu");
        Thread.sleep(2000);
        driver.findElement(By.id("zipCode")).sendKeys("600106");
        System.out.println("8.Personal details entered successfully");
        Thread.sleep(2000);

        // Scroll down
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(2000);

        //Enter payment details
        WebElement cardDropdown = driver.findElement(By.id("cardType"));
        cardDropdown.click();
        Thread.sleep(2000);

        Select card = new Select(cardDropdown);
        card.selectByVisibleText("American Express");

        driver.findElement(By.id("creditCardNumber")).sendKeys("123456789");
        Thread.sleep(2000);
        // Clear month and year
        driver.findElement(By.id("creditCardMonth")).clear();
        Thread.sleep(2000);
        driver.findElement(By.id("creditCardMonth")).sendKeys("27");

        driver.findElement(By.id("creditCardYear")).clear();
        Thread.sleep(2000);
        driver.findElement(By.id("creditCardYear")).sendKeys("2026");
        Thread.sleep(2000);
        driver.findElement(By.id("nameOnCard")).sendKeys("Rathesh Prabu");

        System.out.println("9.Payment details entered successfully");
        Thread.sleep(2000);

        // Checkbox
        WebElement checkbox = driver.findElement(By.id("rememberMe"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        //Purchase Flight
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
        Thread.sleep(2000);
        System.out.println("10.Purchase Flight Clicked");

        // Verify booking confirmation
        boolean confirmation = driver.getPageSource().contains("Thank you for your purchase today!");

        if (confirmation) {
            System.out.println("11.Flight booking completed successfully");
        }

        Thread.sleep(3000);

        driver.quit();
        
        System.out.println("Browser closed");

        System.out.println("End Of Automation");
    }
}