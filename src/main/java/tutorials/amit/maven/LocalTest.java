package tutorials.amit.maven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocalTest
{
    private WebDriver driver;
    private WebDriverWait wait;

    public static void main(String[] args)
    {
        LocalTest localTest = new LocalTest();
        localTest.testForm();

    }

    public LocalTest()
    {
        System.setProperty("webdriver.chrome.driver","C:/selenium_drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    public void testForm()
    {
        String expectedTitle = "Selenium Test";
        String actualTitle = "";

        this.driver.get("http://phptest.local/");

        actualTitle = this.driver.getTitle();

        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        // testing jquery div generated on click event
        this.driver.findElement(By.xpath("//*[@id=\"internal-click-test\"]")).click();
        String dynamicDivTest = this.driver.findElement(By.xpath("/html/body/div/div")).getText();
        if (dynamicDivTest.contentEquals("Testing dynamic div")){
            System.out.println("Dynamic div test passed");
        } else {
            System.out.println("Dynamic div test Failed");
        }

        // testing data in modal generated on click event
        /*this.driver.findElement(By.xpath("/html/body/p[2]/a")).click();
        //String dynamicModalTest = this.driver.findElement(By.xpath("//*[@id=\"ex1\"]/p")).getText();
        if(this.driver.findElements(By.xpath("//*[@id=\"ex1\"]/p")).size() != 0) {
            System.out.println("Modal detected successfully");
        } else {
            System.out.println("Failed to detect the modal");
        }*/

        // testing for AJAX
        By ajaxContainer = By.xpath("/html/body/div[3]/ul");
        this.driver.findElement(By.xpath("//*[@id=\"ajax-test-button\"]")).click();
        this.wait = new WebDriverWait(this.driver, 10);
        this.wait.until(ExpectedConditions.presenceOfElementLocated(ajaxContainer));
        if(this.driver.findElements(ajaxContainer).size() != 0) {
        System.out.println("AJAX call success");
        } else {
            System.out.println("AJAX call failure");
        }

        this.driver.findElement(By.xpath("//*[@id=\"test-form\"]/div[1]/input")).sendKeys("Amit");
        this.driver.findElement(By.xpath("//*[@id=\"test-form\"]/div[2]/input")).sendKeys("Deshmukh");
        this.driver.findElement(By.xpath("//*[@id=\"test-form\"]/div[3]/input")).submit();

        // this navigates to http://phptest.local/test.php as that is the submit action on the form
        String expectedGreeting = "Hello, Amit Deshmukh";
        String actualGreeting = this.driver.findElement(By.xpath("/html/body/div")).getText();

        if (actualGreeting.contentEquals(expectedGreeting)){
            System.out.println("New page Greetings Test Passed!");
        } else {
            System.out.println("New page Greetings Test Failed");
        }

        //this.driver.close();
    }
}
