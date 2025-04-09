/*
@author Kunal Soni
*/

package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import library.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBaseClass {

    public static Properties properties;
    public static org.openqa.selenium.WebDriver driver;
    public static WebDriver webdriver = new WebDriver();

    public static Date date = new Date();
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    public static String dt = formatter.format(date);
    public static FileReader fileReader;
    protected final Logger logger = LogManager.getLogger(getClass());

    public static ExtentSparkReporter spark;
    public static ExtentTest test;
    public static ExtentReports extent;
    public static JsonFormatter json;
    public static String reportDestination = "reports/report_" + dt + ".html";
    private static final String JSON_ARCHIVE = "target/json/jsonArchive.json";
    public static String username = System.getenv("LT_USERNAME");
    public static String access_key = System.getenv("LT_ACCESS_KEY");

    public void extentReportSpark() {
        spark = new ExtentSparkReporter(reportDestination);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        json = new JsonFormatter(JSON_ARCHIVE);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser Name", properties.getProperty("BrowserName"));
        extent.setSystemInfo("Environment", properties.getProperty("Environment"));

        spark.config().setDocumentTitle("WebApp Automation Testing Report");
        spark.config().setReportName("WebApp Automation Test Suite");
        spark.config().setTimelineEnabled(Boolean.TRUE);
        spark.config().setOfflineMode(Boolean.TRUE);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        spark.config().setTimelineEnabled(Boolean.TRUE);
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {

        propertiesLoad();
        extentReportSpark();
        autoOpenBrowser();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws EncryptedDocumentException {

        driver.quit();
        extent.flush();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getName() + " test case is failed. " + "<span class='badge badge-danger'> Fail </span>" + result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip(result.getName() + " test case is skipped." + "<span class='badge badge-warning'> Skip </span>");

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass(result.getName() + " test case is Passed." + "<span class='badge badge-success'> Success </span>");
        }
    }

    public void propertiesLoad() throws IOException {

        try {
            fileReader = new FileReader("src/test/resources/QA.properties");
            properties = new Properties();
            properties.load(fileReader);

        } catch (FileNotFoundException ex) {
            test.info("*************************************************");
            test.info("Property file you are looking for does not exist.");
            test.info("*************************************************");
        }
    }

    public void autoOpenBrowser() {

            String platformName = System.getenv("HYPEREXECUTE_PLATFORM") != null ? System.getenv("HYPEREXECUTE_PLATFORM") : "Windows 10";
        
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("build", "Smple Build");
        capabilities.setCapability("name", "Sample test");
        capabilities.setCapability("platform", System.getenv("HYPEREXECUTE_PLATFORM"));
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "127");

        capabilities.setCapability("tunnel",false);
        capabilities.setCapability("network",true);
        capabilities.setCapability("console",true);
        capabilities.setCapability("visual",true);
        capabilities.setCapability("selenium_version","4.18.0");

        try
        {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"), capabilities);
        }
        catch (MalformedURLException e)
        {
            System.out.println("Invalid grid URL");
        }
        System.out.println("Started session");
    }

}
