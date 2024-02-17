package demo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/");
        String currentUrl = driver.getCurrentUrl();
        if(currentUrl.contains("calendar.")){
            System.out.println(currentUrl);
        }
        System.out.println("end Test case: testCase01");
    }

        
    public void testCase02() {
    System.out.println("Start Test case: testCase02");
    System.out.println("switching to the month view");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='XyKLOd']//button"))).click();

    WebElement month = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='ucc-1']/ul/li)[3]")));
    String nameMonth = month.getText();
    if (nameMonth.contains("Month")) {
        month.click();
    }

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='MGaLHf elYzab-cXXICe-Hjleke'])[11]")))
        .click();
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='tabTask']")))
        .click();
    WebElement taskInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='DgKtsd']//input")));
    taskInput.sendKeys("Crio INTV Task Automation");

    WebElement descriptionInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@placeholder='Add description']")));
    descriptionInput.sendKeys("Crio INTV Calendar Task Automation");

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='BTotkb JaKw1']//button)[4]")))
        .click();

    System.out.println("end Test case: testCase02");
}


        
public void testCase03() throws InterruptedException {
    System.out.println("Start Test case: testCase03");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement dateCell = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='T3BIT']//div[@role='gridcell'])[11]")));
    dateCell.click();

    WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='pPTZAe']//span/button)[1]")));
    editButton.click();

    System.out.println("updating the description of the task");

    WebElement descriptionInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@placeholder='Add description']")));
    descriptionInput.clear();
    Thread.sleep(2000);
    descriptionInput.sendKeys("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");

    WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Save')]/parent::button")));
    saveButton.click();

    Thread.sleep(4000);
    dateCell.click();

    String descChUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(., 'Crio INTV Task Automation') and @class='toUqff D29CYb']"))).getText();

    String actualDescription = descChUp.replace("Description:", "").trim();


    System.out.println(actualDescription);
    Thread.sleep(3000);
    if (actualDescription.contains("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application")) {
        System.out.println("Ensured that we are checking the description is updated");

    }

    System.out.println("end Test case: testCase03");
}

       
    

public void testCase04() {
    System.out.println("Start Test case: testCase04");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // WebElement dateCell = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='T3BIT']//div[@role='gridcell'])[11]")));
    // dateCell.click();

    String expectedTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rAECCd"))).getText();
    if (expectedTitle.contains("Crio INTV Task Automation is a test suite designed for automating")) {
        System.out.println("Verified Title");
    }

    WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='pPTZAe']//span/button)[2]")));
    deleteButton.click();

    WebElement deleteMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(text(),'deleted')])[2]")));
    if (deleteMessage.getText().contains("Task deleted")) {
    System.out.println("Ensure that we are checking the alert text is equal to Task deleted");
}

    System.out.println("end Test case: testCase04");
}



}
