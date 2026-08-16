package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScreenshotUtil;
import utils.SeleniumReportUtil;

public class EmployeePage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By pimMenu = By.xpath("//span[text()='PIM']");
	private By addEmployee = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	private By firstName = By.xpath("//input[@name='firstName']");
	private By lastName = By.xpath("//input[@name='lastName']");
	private By photoUpload = By.xpath("//input[@type='file']");
	private By empId = By.xpath(
			"//div[@class='oxd-input-group oxd-input-field-bottom-space']//input[@class='oxd-input oxd-input--active']");
	private By saveBtn = By.xpath("//button[@type='submit']");
	private By searchBox = By
			.xpath("//label[text()='Employee Id']/following::div//input[@class='oxd-input oxd-input--active']");
	private By searchBtn = By.xpath("//button[@type='submit']");
	private By job = By.xpath("//a[text()='Job']");
	private By jobTitleDropDown = By.xpath(
			"//div[label[text()='Job Title']]/following-sibling::div//div//div[@class='oxd-select-text oxd-select-text--active']");
	private By empStatus = By.xpath(
			"//div[label[text()='Employment Status']]/following-sibling::div//div//div[@class='oxd-select-text oxd-select-text--active']");
	private By deleteBtn = By.xpath("//i[@class='oxd-icon bi-trash']");
	private By confirmDeleteBtn = By.xpath("//button[@type='button' and text()=' Yes, Delete ']");

	public EmployeePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void addEmployee(String fName, String lName, String id, String photoPath) {
		try {

			SeleniumReportUtil.logInfo("Navigating to Add Employee page");
			wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
			wait.until(ExpectedConditions.elementToBeClickable(addEmployee)).click();

			SeleniumReportUtil.logInfo("Uploading employee photo");

			WebElement uploadField = wait.until(ExpectedConditions.presenceOfElementLocated(photoUpload));

			// Use JavaScript to set the file path
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].style.display='block'; arguments[0].style.visibility='visible';", uploadField);

			// Now send the file path
			uploadField.sendKeys(photoPath);

			String shot = ScreenshotUtil.captureScreenshot(driver, "UploadingImage");
			SeleniumReportUtil.logPass("Uploaded added successfully", shot);

			SeleniumReportUtil.logInfo("Entering employee details");
			wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fName);
			wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lName);

			WebElement empIdField = wait.until(ExpectedConditions.elementToBeClickable(empId));
			empIdField.sendKeys(Keys.CONTROL + "a");
			empIdField.sendKeys(Keys.DELETE);
			empIdField.sendKeys(id);

			wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();

			String shot1 = ScreenshotUtil.captureScreenshot(driver, "AddEmployee");
			SeleniumReportUtil.logPass("Employee added successfully", shot1);
		} catch (Exception e) {
			String shot = ScreenshotUtil.captureScreenshot(driver, "AddEmployeeError");
			SeleniumReportUtil.logFail("Failed to add employee: " + e.getMessage(), shot);
		}
	}

	public void searchEmployee(String id) {
		try {
			SeleniumReportUtil.logInfo("Searching employee with ID: " + id);
			wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(id);
			wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();

			String shot = ScreenshotUtil.captureScreenshot(driver, "SearchEmployee");
			SeleniumReportUtil.logPass("Employee search completed", shot);
		} catch (Exception e) {
			String shot = ScreenshotUtil.captureScreenshot(driver, "SearchEmployeeError");
			SeleniumReportUtil.logFail("Failed to search employee: " + e.getMessage(), shot);
		}
	}

	public void editEmployee(String newJobTitle, String newStatus) {
		try {
			SeleniumReportUtil.logInfo("Editing employee details");
			WebElement editBtn = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//button[.//i[contains(@class,'bi-pencil-fill')]]")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", editBtn);

			SeleniumReportUtil.logInfo("Opening Job section");
			wait.until(ExpectedConditions.visibilityOfElementLocated(job)).click();

			SeleniumReportUtil.logInfo("Selecting Job Title: " + newJobTitle);
			wait.until(ExpectedConditions.elementToBeClickable(jobTitleDropDown)).click();
			WebElement jobOption = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//div[@role='option' and normalize-space()='" + newJobTitle + "']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", jobOption);

			SeleniumReportUtil.logInfo("Selecting Employment Status: " + newStatus);
			wait.until(ExpectedConditions.presenceOfElementLocated(empStatus)).click();
			WebElement statusOption = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[@role='option' and normalize-space()='" + newStatus + "']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", statusOption);

			SeleniumReportUtil.logInfo("Saving changes");
			WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);

			String shot = ScreenshotUtil.captureScreenshot(driver, "EditEmployee");
			SeleniumReportUtil.logPass("Employee edited successfully", shot);
		} catch (Exception e) {
			String shot = ScreenshotUtil.captureScreenshot(driver, "EditEmployeeError");
			SeleniumReportUtil.logFail("Failed to edit employee: " + e.getMessage(), shot);
		}
	}

	public void deleteEmployee(String id) {
		try {
			SeleniumReportUtil.logInfo("Deleting employee with ID: " + id);
			searchEmployee(id);

			WebElement firstCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//span[@class='oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input']/i[@class='oxd-icon bi-check oxd-checkbox-input-icon'])[1]")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstCheckbox);

			SeleniumReportUtil.logInfo("Clicking delete button");
			wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();

			SeleniumReportUtil.logInfo("Confirming delete");
			WebElement confirmDeleteBtnElement = wait
					.until(ExpectedConditions.presenceOfElementLocated(confirmDeleteBtn));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmDeleteBtnElement);

			String shot = ScreenshotUtil.captureScreenshot(driver, "DeleteEmployee");
			SeleniumReportUtil.logPass("Employee deleted successfully", shot);
		} catch (Exception e) {
			String shot = ScreenshotUtil.captureScreenshot(driver, "DeleteEmployeeError");
			SeleniumReportUtil.logFail("Failed to delete employee: " + e.getMessage(), shot);
		}
	}
}
