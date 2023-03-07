package com.das.core.demo.ecommerce;

import java.io.IOException;

import com.das.core.BaseTest;
import com.das.pojo.Configuration;
import com.das.pojo.CustomerInfo;
import com.das.pom.AccountPage;
import com.das.utils.JacksonUtils;

public class BaseEcommerce extends BaseTest {

	public Configuration loadConfig() throws IOException {
		Configuration configuration = JacksonUtils.deserializeJson("Configuration.json", Configuration.class);
		return configuration;
	}

	public CustomerInfo loadBillingInfo() throws IOException {
		CustomerInfo billingInformation = JacksonUtils.deserializeJson("BillingAddress.json", CustomerInfo.class);
		return billingInformation;
	}

	public void loadloginPage() throws IOException {
		getDriver().get(loadConfig().getAccountURL());
	}

	public void loadNonLoginPage() throws IOException {
		getDriver().get(loadConfig().getBaseURL());
	}

	public void performLogin(String userEmail) throws Exception {

		AccountPage ap = new AccountPage(getDriver());
		ap.getUsername().sendKeys(userEmail);
		ap.getPassword().sendKeys(loadConfig().getPassword());
		ap.getLoginButton().click();
	}

}
