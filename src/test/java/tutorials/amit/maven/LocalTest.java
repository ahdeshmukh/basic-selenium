package tutorials.amit.maven;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.AfterTest;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import java.util.List;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class LocalTest
{

    private WebDriver driver;

    @BeforeTest
    public void beforeTest()
    {
        System.setProperty("webdriver.chrome.driver","C:/selenium_drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.get("http://phptest.local/test1.php");
    }

    @Test
    public void testDiv()
    {
        ////*[@id="test-div"]
        String helloDivText = this.driver.findElement(By.id("test-div")).getText();
        Assert.assertEquals("Test 1", helloDivText);
    }

    @Test
    public void testUl()
    {
        By testUl = By.id("test-ul");
        Integer testUlSize = this.driver.findElements(testUl).size();
        Assert.assertTrue(testUlSize > 0);
    }


    // check if the ul contains at least one li
    @Test
    public void testUlLi()
    {
        List<WebElement> li_All = this.driver.findElements(By.xpath("//*[@id=\"test-ul\"]/li"));
        Assert.assertTrue(li_All.size() > 0);
    }

    @Test
    public void assertTrue123()
    {
        Assert.assertTrue(false);
    }

    /*@Test
    public void testJson()
    {
        String str = "{ \"name\": \"Alice\", \"age\": 20 }";
        try {
            JSONObject obj = new JSONObject(str);
            String n = obj.getString("name");
            int a = obj.getInt("age");
            System.out.println(n + " " + a);
        } catch(JSONException e) {
            System.out.println(e.getMessage());
        }
    }*/

    @AfterMethod
    public void takeScreenshot(ITestResult result)
    {
        /*if(ITestResult.FAILURE==result.getStatus()) {
            System.out.println("Results for " + result.getName());
            System.out.println("ITestResult.FAILURE => " + ITestResult.FAILURE);
            System.out.println("result.getStatus() => " + result.getStatus());
            System.out.println("======================================");
        }*/

        if(ITestResult.FAILURE==result.getStatus()){
            try {
                TakesScreenshot screenshot = (TakesScreenshot)this.driver;
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                //FileUtils.copyFile(src, new File("C:\\Users\\Amit\\Desktop\\home\\"+result.getName()+".png"));
                FileUtils.copyFile(src, new File("/tmp/"+result.getName()+".png"));
                System.out.println("Successfully captured a screenshot");
            } catch(Exception e) {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }
    }

    @AfterTest
    public void closeDriver()
    {
        this.driver.close();
    }

}
