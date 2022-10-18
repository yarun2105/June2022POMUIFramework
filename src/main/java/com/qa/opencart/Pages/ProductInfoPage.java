package com.qa.opencart.Pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Constant.AppConstants;
import com.qa.opencart.Utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	HashMap<String, String> productInfoMap;
	
	
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData  = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeader(String mainProductName) {
		String xpath = "//h1[text()='"+mainProductName+"']";
		String productHeader = eleUtil.doGetText(By.xpath(xpath));
		System.out.println("Product Header is :" +productHeader);
		return productHeader;
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_LARGE_TIME_OUT).size();
	}
	
	
	public String getProductPageTitle(String productTitle) {
		return eleUtil.waitFortitleIs(AppConstants.DEFAULT_TIME_OUT, productTitle);
	}
	
	public boolean getProductPageUrl(String searchKey) {
		String Url  = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, searchKey);
		if(Url.contains(searchKey)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public HashMap<String, String> getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		productInfoMap = new HashMap<String, String>();
		for(WebElement e:metaDataList) {
			String Metatext = e.getText();
			String meta[] = Metatext.split(":");
			String metaKey  = meta[0].trim();
			String metaValue  = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		productInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		return productInfoMap;
		
		
		
	}
	
	public String getProductPrice() {       
		WebElement ele = eleUtil.getElement(productPriceData);
		return ele.getText();
		
	}
		
	

}
