# OrangeHRM Automation Framework

![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-Build-orange)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-green)
![TestNG](https://img.shields.io/badge/TestNG-7.11-red)
![ExtentReports](https://img.shields.io/badge/ExtentReports-Reporting-yellow)

## Overview
This project automates the OrangeHRM Demo Application using:
- Java 21
- Selenium WebDriver
- TestNG for test execution
- ExtentReports for reporting with screenshots
- Page Object Model (POM) design pattern

It covers the Employee Lifecycle:
- Add Employee
- Search Employee
- Edit Employee
- Delete Employee
- Logout

## Setup Instructions
1.Clone the repository
   ```bash
   git clone https://github.com/CodeAlchemist12/OrangeHRM-Automation-Framework.git
   cd OrangeHRM-Automation-Framework

   
   
   
2.Install dependencies
- Java 21+
- Maven
- Chrome browser

3.Run the tests(Bash)
- mvn clean test

4.View the report after execution,open:
-  /reports/AutomationReport.html-in browser


Project Structure
src
 ├── main
 │    └── java
 │         └── pages          # Page Object classes
 │         └── utils          # Screenshot & Report utilities
 └── test
      └── java
           └── base           # BaseTest setup
           └── tests          # Test classes

Sample Test Flow
Login to OrangeHRM

Add new employee

Search employee by ID

Edit job details

Delete employee

Logout

Screenshots & Reports
ExtentReport: /reports/AutomationReport.html

Screenshots: /screenshots/ (captured on pass/fail)


### Recent Updates
- Added photo upload functionality in Add Employee automation


Author
Ankit Kumar  
Senior Automation Test Engineer | Infosys
Bengaluru, India















