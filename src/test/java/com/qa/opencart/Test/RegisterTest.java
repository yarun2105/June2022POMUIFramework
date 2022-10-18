package com.qa.opencart.Test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constant.AppConstants;
import com.qa.opencart.Utils.ExcelUtil;

public class RegisterTest extends BaseTest{
	
	
	@BeforeClass
	public void regSetUp() {
		registerPage =  loginPage.navigateToRegisterPage();
		
	}
	
	
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "automationtest"+random.nextInt(10000)+"@gmail.com";
		return email;
	}
	
	
	@Test(dataProvider = "getRegTestData")
	public void registerUserTest(String firstName, String lastName,String emailId,String telephone, String password, String subscribe) {
				
		String actSuccMessg = registerPage.userRegister(firstName, lastName, getRandomEmail(), telephone, password, subscribe);
		Assert.assertEquals(actSuccMessg, AppConstants.ACC_CREATE_SUCC_MSG);
		
	}
	
	
	
	
	

}
