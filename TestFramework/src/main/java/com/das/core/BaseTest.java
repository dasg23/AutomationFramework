package com.das.core;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.das.pojo.Configuration;

public abstract class BaseTest {
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	Semaphore semaphore = new Semaphore(10);

	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}

	protected WebDriver getDriver() {
		return this.driver.get();
	}

	@BeforeMethod
	public void startDriver() throws Exception {
		semaphore.acquire();
		setDriver(new DriverManager().initDriver());
	}

	@AfterMethod
	public void tearDownDriver() throws Exception {
		getDriver().quit();
		driver.remove();
		semaphore.release();
	}

	public abstract Configuration loadConfig() throws IOException;

}
