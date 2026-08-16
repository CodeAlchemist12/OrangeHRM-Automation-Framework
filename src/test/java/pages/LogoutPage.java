package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ScreenshotUtil;
import utils.SeleniumReportUtil;

public class LogoutPage {
	private WebDriver driver;
	private By profileMenu = By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");
	private By logout= By.xpath("//a[text()='Logout']");

	public LogoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public void performLogout() {
	    try {
	        
	        SeleniumReportUtil.logInfo("Clicking on profile menu to open dropdown");
	        driver.findElement(profileMenu).click();

	        
	        SeleniumReportUtil.logInfo("Clicking on Logout option");
	        driver.findElement(logout).click();
	        Thread.sleep(2000);

	        
	        String shot = ScreenshotUtil.captureScreenshot(driver, "LogoutSuccess");
	        SeleniumReportUtil.logPass("Logout performed successfully", shot);
	        
	        

	    } catch (Exception e) {
	        
	        String shot = ScreenshotUtil.captureScreenshot(driver, "LogoutError");
	        SeleniumReportUtil.logFail("Logout failed: " + e.getMessage(), shot);
	    }
	}

}
