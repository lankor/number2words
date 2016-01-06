package com.lankorlab.translate;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


import static org.junit.Assert.*;

public class GenerateFileTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.ultralingua.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		driver.get(baseUrl + "/onlinedictionary/numbers");
		String pastNumber = "";
		long start = 1;
		boolean languaje = false;
		int offset = 1;

		for (int i = 0; i < 1000; i++) {
			start = nextTest(start, offset);
			driver.findElement(By.name("query")).clear();
			driver.findElement(By.name("query")).sendKeys(String.valueOf(start));
			if (!languaje) {
//				new Select(driver.findElement(By.id("langSelect"))).selectByVisibleText("German");
				new Select(driver.findElement(By.id("langSelect"))).selectByVisibleText("Italian");
				languaje = true;
			}

			driver.findElement(By.linkText("Search")).click();

			for (int second = 0;; second++) {
				if (second >= 60)
					fail("timeout");
				try {
					if (isElementPresent(By.id("numberTranslation")) && !pastNumber.equals(driver.findElement(By.id("numberTranslation")).getText())) {
						pastNumber = driver.findElement(By.id("numberTranslation")).getText();
						break;
					}
				} catch (Exception e) {
				}
				Thread.sleep(1000);
			}

			System.out.printf("%s=%s\n", start, driver.findElement(By.id("numberTranslation")).getText());

			if ((i > 1) && (i % 100 == 0)) {
				offset = offset * 10;
			}
		}
	}

	Random random = new Random();
	private long nextTest(long start, long offset) {
		return start + ((long)(random.nextInt(10) + 30)) + getOffset(offset);
	}

	private long getOffset(long offset) {
		long computedOffset = 0;

		if ((offset > 10) && (offset < Integer.MAX_VALUE)) {
			computedOffset = ((long) (random.nextInt((int) offset) + (offset * 3)));
		} else if (offset > Integer.MAX_VALUE){
			computedOffset = Integer.MAX_VALUE;
		}

		return computedOffset;
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
