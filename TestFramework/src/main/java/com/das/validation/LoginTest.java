package com.das.validation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.das.core.DataDrivenExcel;
import com.das.core.demo.ecommerce.BaseEcommerce;
import com.das.pojo.CustomerInfo;
import com.das.pom.AccountPage;

public class LoginTest extends BaseEcommerce {

	@Test(dataProvider = "getData")
	public void validateCartpage(CustomerInfo customerInfo, CustomerInfo customerInfoCopy) throws Exception {

		loadloginPage();
		performLogin(customerInfo.getEmail());

		AccountPage ap = new AccountPage(getDriver());
		ap.getOrderLink().click();
		System.out.println("CURRENT THREAD End: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());

	}

	@DataProvider(parallel = true)
	public Object[][] getData() throws CloneNotSupportedException {

		DataDrivenExcel dataDrivenExcel = new DataDrivenExcel();
		String temp[] = dataDrivenExcel.fetchRangeDataFromSource();
		dataDrivenExcel.mapExcellDataInCollection(temp);
		return dataDrivenExcel.mapCollectionDataInPOJO();

	}

}
