package base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ScreenshotUtil;
import utils.SeleniumReportUtil;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void setupReport() {
        SeleniumReportUtil.initReport();
    }

    @BeforeClass
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Detect if running in CI (GitHub Actions sets CI=true)
        String ciEnv = System.getenv("CI");
        if (ciEnv != null && ciEnv.equalsIgnoreCase("true")) {
            options.addArguments("--headless");                // run without UI
            options.addArguments("--no-sandbox");              // required in CI
            options.addArguments("--disable-dev-shm-usage");   // avoid resource issues
            options.addArguments("--disable-gpu");             // safe for Linux runners
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(6000);
    }

    @BeforeMethod
    public void startExtentTest(Method method) {
        SeleniumReportUtil.startTest(method.getName());
    }

    @AfterMethod
    public void captureFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            SeleniumReportUtil.logFail("Test failed: " + result.getThrowable(), screenshotPath);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        SeleniumReportUtil.flushReport();
    }
}
