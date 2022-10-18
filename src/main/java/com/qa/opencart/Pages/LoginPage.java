package com.qa.opencart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.qa.opencart.Constant.AppConstants;
import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.factory.DriverFactory;

public class LoginPage {

	private WebDriver driver; // All webdriver method are acces in pageobject class only
	private ElementUtil eleUtil;
	
	private static final Logger Log = Logger.getLogger(LoginPage.class);

	// 1. By locator

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By lognBtn = By.xpath("//input[@value='Login' ]");
	private By logoImgae = By.xpath("//img[@class='img-responsive' ]");
	private By forgetPasswordLink = By.linkText("Forgotten Password");
	
	private By registerLink  = By.linkText("Register");

	// 2. page constructor

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. page Actions

	public String getLoginPgaeTitle() {
		String title = eleUtil.waitFortitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("Page title is:" + title);
		Log.info("Page title is:" +title);
		return title;
	}

	public boolean getLoginPageUrl() {
		String url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL);
		System.out.println("Page url is:" + url);
		if (url.contains(AppConstants.LOGIN_PAGE_URL)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean getLoginForgetPwdLink() {
		return eleUtil.doEleIsDisplayed(forgetPasswordLink);
	}

	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("User credentials are:" + username + ":" + pwd);
		eleUtil.doSendKeysWithVisibleElement(emailId, AppConstants.DEFAULT_LARGE_TIME_OUT,username);
		eleUtil.doSendkeys(password, pwd);
		eleUtil.doClick(lognBtn);

		return new AccountsPage(driver);
	}
	
	
	public RegisterPage navigateToRegisterPage() {
		System.out.println("Navigating to register page .........");
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
		
		
	}

}
