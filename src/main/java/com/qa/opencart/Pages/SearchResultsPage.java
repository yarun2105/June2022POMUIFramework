package com.qa.opencart.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Constant.AppConstants;
import com.qa.opencart.Utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By productSearchLayout  = By.cssSelector("div.product-layout");
	

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	public boolean  isSearchSuccessful() {
		List<WebElement> searchList = eleUtil.waitForElementsVisible(productSearchLayout, AppConstants.DEFAULT_LARGE_TIME_OUT);
		if(searchList.size()>0) {
			System.out.println("Search is successfully done ....");
			return true;
		}
		return false;
	}
	
	public ProductInfoPage selectProduct(String mainProuctName) {
		By mainProName = By.linkText(mainProuctName);
		eleUtil.doClick(mainProName);
		return new ProductInfoPage(driver);
	}
	
	

}
