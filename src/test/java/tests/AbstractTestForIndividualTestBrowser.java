package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTestForIndividualTestBrowser {

	protected WebDriver driver;

	@BeforeTest
	@Parameters({"browser"})
	public void setDriver(String browser) throws MalformedURLException {
		if (Boolean.getBoolean("selenium.grid.enabled")) {
			this.driver = getRemoteDriver(browser);
		} else {
			this.driver = getLocalDriver();
		}
	}

	private WebDriver getRemoteDriver(String browser) throws MalformedURLException {
		// http://localhost:4444/wd/hub
		Capabilities capabilities;
		//if (System.getProperty("browser").equalsIgnoreCase("chrome")) {
		if(browser.equalsIgnoreCase("chrome")) {
			capabilities = new ChromeOptions();
		} else {
			capabilities = new FirefoxOptions();
		}
		return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
	}

	private WebDriver getLocalDriver() {
		// WebDriverManager.chromedriver().clearDriverCache().setup();
		WebDriverManager.chromedriver().setup();
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		return this.driver;
	}

	@AfterTest
	public void quitDriver() {
		this.driver.quit();
	}
}
