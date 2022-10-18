package com.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constant.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitletest() {

		String actualTitle = loginPage.getLoginPgaeTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void loginPageUrlTest() {
		Assert.assertEquals(loginPage.getLoginPageUrl(), true);
	}

	@Test(priority = 3)
	public void isForgotPwdLinkExistTest() {
		Assert.assertEquals(loginPage.getLoginForgetPwdLink(), true);
	}

	@Test(priority = 4)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogOutLinkExist());

	}

}
