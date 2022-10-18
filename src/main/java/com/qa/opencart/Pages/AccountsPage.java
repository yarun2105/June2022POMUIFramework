package com.qa.opencart.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Constant.AppConstants;
import com.qa.opencart.Utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	
	
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchBtn = By.cssSelector("div#search button");
	private By accSecHeaders = By.cssSelector("div#content h2");
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	
	public String getAccPageTitle() {
		String title = eleUtil.waitFortitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_TITLE);
		System.out.println("Account page title is:" + title);
		return title;
	}
	
		 
		
	
	public boolean getAccPageUrl() {
		String Url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_URL);
		System.out.println("Account Page url is:" + Url);
		if (Url.contains(AppConstants.ACC_PAGE_URL)) {
			return true;
		} else {
			return false;
		}
	}
		
		
	
	public boolean isLogOutLinkExist() {
		return eleUtil.doEleIsDisplayed(logoutLink);
	}
	
	
		
		
	public boolean isSearchExist() {
		return eleUtil.doEleIsDisplayed(search);
	}
	
	public boolean isSearchBtnExist() {
		return eleUtil.doEleIsDisplayed(searchBtn);
	}
	
	public SearchResultsPage performSearch(String productName) {
		System.out.println("Product name is :" + productName);
		if(isSearchExist()) {
			eleUtil.doSendkeys(search, productName);
			eleUtil.doClick(searchBtn);
			return new SearchResultsPage(driver);
			
		}
		else {
			System.out.println("Search field is not present on the page....");
			return null;
		}
	}
	
	public ArrayList<String> getAccSecHeaders() {
		List<WebElement> secList = eleUtil.waitForElementsVisible(accSecHeaders, AppConstants.DEFAULT_LARGE_TIME_OUT);
		System.out.println("Total sections headers "+ secList.size());
		ArrayList<String> AccSecHeadersList = new ArrayList<String>();
		for(WebElement e : secList) {
			String accText = e.getText();
			AccSecHeadersList.add(accText);
		}
		return AccSecHeadersList;
		
	}
			
	
		
	
		
		
		
	

}
