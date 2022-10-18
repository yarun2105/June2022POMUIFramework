package com.qa.opencart.Test;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constant.AppConstants;
import com.qa.opencart.Utils.ExcelUtil;

public class ProductPageTest extends BaseTest {
	
	@BeforeClass
	public void prodInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Samsung","Samsung SyncMaster 941BW"}};
		
	}
	
	
//	@DataProvider
//	public Object[][] getProductData() {
//		Object productData[][] = ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
//		return productData;
//	}
	
	
	@Test(dataProvider  = "getProductData")
	public void productHeaderTest(String searchKey, String mainProductName) {
		searchResultsPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		String actualProductHeader = productInfoPage.getProductHeader(mainProductName);
		Assert.assertEquals(actualProductHeader, mainProductName);
	}
	
	@DataProvider
	public Object [][] getProductInfoData(){
		return new Object[][] {
			{"Macbook","MacBook Pro",AppConstants.MACBOOK_PRO_IMAGES_COUNT},
			{"Macbook","MacBook Air",AppConstants.MACBOOK_AIR_IMAGES_COUNT},
			{"iMac","iMac",AppConstants.IMAC_IMAGES_COUNT},
			{"Samsung","Samsung SyncMaster 941BW",AppConstants.SAMSUNG_IMAGES_COUNT}};
			

	}

	@Test(dataProvider =  "getProductInfoData")
	public void proImagesCountTest(String searchKey, String mainProductName,int ImagesCount) {
		searchResultsPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		int accImagesCount = productInfoPage.getProductImagesCount();
		System.out.println("Actual Product Images Count is :" + accImagesCount);
		Assert.assertEquals(accImagesCount, ImagesCount);
	}

	@Test
	public void productPageTitleTest() {
		searchResultsPage = accPage.performSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String accProPageTitle = productInfoPage.getProductPageTitle("MacBook Pro");
		System.out.println("Product page tilte is :" + accProPageTitle);
		Assert.assertEquals(accProPageTitle, "MacBook Pro");
	}

	@Test
	public void productPageUrlTest() {
		searchResultsPage = accPage.performSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductPageUrl("Macbook"), true);
	}
	
	@Test
	public void productMetaDataTest() {
		searchResultsPage = accPage.performSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		HashMap<String,String> accMetaDataMap = productInfoPage.getProductMetaData();
		Assert.assertEquals(accMetaDataMap.get("Brand"), "Apple");
		Assert.assertEquals(accMetaDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(accMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(accMetaDataMap.get("Availability"), "In Stock");
		
	}
	
	@Test
	public void productPriceTest() {
		searchResultsPage = accPage.performSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String accProductPrice  = productInfoPage.getProductPrice();
		System.out.println("Product price is :"+accProductPrice);
		Assert.assertEquals(accProductPrice, "$2,000.00");
	}

}
