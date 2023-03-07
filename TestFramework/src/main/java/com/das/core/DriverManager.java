package com.das.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	// protected ThreadLocal<RemoteWebDriver> remoteDdriver = new ThreadLocal<>();

	public WebDriver initDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/property.txt");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		String dockerName = prop.getProperty("dockerName");

		if (StringUtils.trim(dockerName).equalsIgnoreCase("true")) {
			URL u = new URL("http://localhost:4444/wd/hub");
			switch (browserName) {

			case "chrome":
				DesiredCapabilities capChrome = DesiredCapabilities.chrome();
				driver.set(new RemoteWebDriver(u, capChrome));
				break;
			case "firefox":
				DesiredCapabilities capFirefox = DesiredCapabilities.firefox();
				driver.set(new RemoteWebDriver(u, capFirefox));
				break;
			default:
				throw new IllegalStateException("Unexpected value of Docker Browser type");
			}

		} else {

			switch (browserName) {

			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/Drivers/ChromeDriver/chromedriver.exe");
				driver.set(new ChromeDriver());
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/Drivers/FirefoxDriver/geckodriver.exe");
				driver.set(new FirefoxDriver());
				break;
			default:
				throw new IllegalStateException("Unexpected value of Browser type");

			}

		}

		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver.get();

	}
}
