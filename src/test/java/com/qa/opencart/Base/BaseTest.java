package com.qa.opencart.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.Pages.AccountsPage;
import com.qa.opencart.Pages.LoginPage;
import com.qa.opencart.Pages.ProductInfoPage;
import com.qa.opencart.Pages.RegisterPage;
import com.qa.opencart.Pages.SearchResultsPage;
import com.qa.opencart.factory.DriverFactory;

public class BaseTest {

	DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginPage;
	public AccountsPage accPage;
	public SearchResultsPage searchResultsPage;
	public  ProductInfoPage productInfoPage;
	public RegisterPage registerPage;

	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
