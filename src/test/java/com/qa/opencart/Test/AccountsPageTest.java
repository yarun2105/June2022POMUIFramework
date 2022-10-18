package com.qa.opencart.Test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constant.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void accPagetitleTest() {
		String actualAccPageTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actualAccPageTitle, AppConstants.ACC_PAGE_TITLE);
	}
	@Test(priority=2)
	public void accPageUrlTest() {
		Assert.assertEquals(accPage.getAccPageUrl(), true);
	}
	@Test(priority=3)
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	
	@Test(priority=4)
	public void logOutLinkExistTest() {
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}
	
	@Test(priority=5)
	public void accPageHeadersTest() {
		ArrayList<String> actualHeadersList = accPage.getAccSecHeaders();
		System.out.println("ActualAccount page Headers:"+actualHeadersList);
		Assert.assertEquals(actualHeadersList, AppConstants.ACC_PAGE_SECTIONS_HEADERS);
	}
	
	
	
	@DataProvider
	public Object[][] getProductKey() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"}};
		
	}
	
	@Test(priority = 6, dataProvider = "getProductKey" )
	public void searchCheckTest(String productSearchKey) {
		searchResultsPage = accPage.performSearch(productSearchKey);
		Assert.assertTrue(searchResultsPage.isSearchSuccessful());
	}
	
	@DataProvider
	public Object [][] getProductData(){
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"}};
		
	}
	
	@Test(priority = 7,dataProvider = "getProductData" )
	public void searchTest(String searchKey, String mainProductName) {
		searchResultsPage = accPage.performSearch(searchKey);
		if(searchResultsPage.isSearchSuccessful()) {
			productInfoPage = searchResultsPage.selectProduct(mainProductName);
			String actualProductheader  = productInfoPage.getProductHeader(mainProductName);
			Assert.assertEquals(actualProductheader, mainProductName);
			
		}
	}
	
	
	
	
		
		
	
		
	
		
		
		
		
		
	

}
