package com.qa.opencart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Constant.AppConstants;
import com.qa.opencart.Utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subscriberYes  = By.xpath("(//input[@name='newsletter' ])[1]");
	private By subscriberNo  = By.xpath("(//input[@name='newsletter' ])[2]");
	
	private By privacyChkBox = By.xpath("//input[@name='agree' ]");
	private By continueBtn = By.xpath("//input[@value='Continue' ]");
	
	private By registerSuccesMsg = By.xpath("//div[@id='content']/h1");
	
	private By logOutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	public String userRegister(String firstName, String lastName,String emailId, 
			String telephone,String password,String subscriber )  {
		eleUtil.doSendKeysWithVisibleElement(this.firstName, AppConstants.DEFAULT_LARGE_TIME_OUT, firstName);
		eleUtil.doSendkeys(this.lastName, lastName);
		eleUtil.doSendkeys(this.emailId, emailId);
		eleUtil.doSendkeys(this.telephone, telephone);
		eleUtil.doSendkeys(this.password, password);
		eleUtil.doSendkeys(this.confirmPassword, password);
		
			if(subscriber.equalsIgnoreCase("yes")) {
				eleUtil.doClick(this.subscriberYes);
			}
			else {
				eleUtil.doClick(this.subscriberNo);
			}
			
			eleUtil.doClick(this.privacyChkBox);
			eleUtil.doClick(this.continueBtn);
			
			
			String successMsg = eleUtil.waitForElementVisible(registerSuccesMsg, AppConstants.DEFAULT_LARGE_TIME_OUT).getText();
			System.out.println("succes Mssgggg........"+successMsg);
			
			eleUtil.doClick(logOutLink);
			eleUtil.doClick(registerLink);
			
			
			return successMsg;
			
		
	}
	
	
	
	
	
	
}
