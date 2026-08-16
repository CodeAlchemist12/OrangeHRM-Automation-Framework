package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SeleniumReportUtil {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void initReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/AutomationReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static void startTest(String testName) {
        test = extent.createTest(testName);
    }

    
    public static void logPass(String message, String screenshotPath) {
        try {
            test.pass(message).addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            test.pass(message + " (screenshot not attached: " + e.getMessage() + ")");
        }
    }

   
    public static void logFail(String message, String screenshotPath) {
        try {
            test.fail(message).addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            test.fail(message + " (screenshot not attached: " + e.getMessage() + ")");
        }
    }
    public static void logInfo(String message) {
        test.info(message);
    }

    public static void flushReport() {
        extent.flush();
    }
}
