package com.das.core;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}

	public void waitForOverlaysToDisappear(By overlay) {
		List<WebElement> overlays = driver.findElements(overlay);
		System.out.println("OVERLAY SIZE" + overlays.size());
		if (overlays.size() > 0) {
			wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
			System.out.println("OVERLAYS INVISIBLE");
		} else {
			System.out.println("OVERLAY NOT FOUND");
		}
	}

	public void selectByValue(By xpath, String value) {
		Select select = new Select(driver.findElement(xpath));
		select.selectByValue(value);
	}

	public void selectByText(By xpath, String value) {
		Select select = new Select(driver.findElement(xpath));
		select.selectByVisibleText(value);
	}

	public void selectByIndex(By xpath, int value) {
		Select select = new Select(driver.findElement(xpath));
		select.selectByIndex(value);
	}

	public String replaceTextInXpath(String xpath, String value) {
		String replacedText = xpath.replaceAll("REPLACETEXT", value);
		return replacedText;
	}

}
