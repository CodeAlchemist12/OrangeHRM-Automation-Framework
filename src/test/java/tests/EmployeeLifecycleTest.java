package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.EmployeePage;
import pages.LoginPage;
import pages.LogoutPage;
import utils.ApiHelper;

import org.testng.annotations.Test;
import java.util.Map;

import base.BaseTest;
import pages.LoginPage;
import pages.EmployeePage;
import pages.LogoutPage;
import utils.DataUtil;
import utils.ApiHelper;

public class EmployeeLifecycleTest extends BaseTest {

	@Test
	public void employeeLifecycleFlow() throws InterruptedException {
		// Login
		LoginPage login = new LoginPage(driver, wait);
		login.login("Admin", "admin123");

		// Load employee data from JSON
		Map<String, String> empData = DataUtil.getEmployeeData("src/test/resources/employee.json");

		EmployeePage empPage = new EmployeePage(driver, wait);
		String empId = empData.get("employeeId");

		// Add Employee (data-driven)
		String photoPath = System.getProperty("user.dir") + "/src/test/resources/photos/GoKu.jpg";

		empPage.addEmployee(empData.get("firstName"), empData.get("lastName"), empData.get("employeeId"), photoPath);

		// Search Employee
		empPage.searchEmployee(empId);

		// Edit Employee
		empPage.editEmployee("QA Engineer", "Full-Time Permanent");

		// Validate via API
		// ApiHelper.validateEmployee(empId);

		// Delete Employee
		empPage.deleteEmployee(empId);

		// Logout
		LogoutPage logout = new LogoutPage(driver);
		logout.performLogout();
	}
}
