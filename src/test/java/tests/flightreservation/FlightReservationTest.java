package tests.flightreservation;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.flightreservations.FlightConfirmationPage;
import pages.flightreservations.FlightSearchPage;
import pages.flightreservations.FlightSelectionPage;
import pages.flightreservations.RegistrationCofirmationPage;
import pages.flightreservations.RegistrationPage;
import tests.AbstractTest;
import tests.flightreservation.model.FlightReservationTestData;
import util.Config;
import util.Constants;
import util.JsonUtil;

public class FlightReservationTest extends AbstractTest{
	
	private FlightReservationTestData testData;
	
	@BeforeTest
	 @Parameters("testDataPath")
	public void setParameters(String testDataPath) {
		this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
	}
	
	@Test
	public void  userRegistrationTest() {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		//registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
		registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
		Assert.assertTrue(registrationPage.isAt());
		
        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
        
		registrationPage.register();
	}

	@Test(dependsOnMethods = "userRegistrationTest")
	public void registrationConfirmationTest() {
		RegistrationCofirmationPage registrationConfirmationPage = new RegistrationCofirmationPage(driver);
		Assert.assertTrue(registrationConfirmationPage.isAt());
		Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
		registrationConfirmationPage.goToFlightSearch();
	}
	
	@Test(dependsOnMethods = "registrationConfirmationTest")
	public void flightSearchTest() {
		FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
		Assert.assertTrue(flightSearchPage.isAt());
		flightSearchPage.selectPassengers(testData.passengersCount());
		flightSearchPage.searchFlights();
	}
	
	@Test(dependsOnMethods = "flightSearchTest")
	public void flightSelectionTest() {
		FlightSelectionPage flightSelectionTest = new FlightSelectionPage(driver);
		Assert.assertTrue(flightSelectionTest.isAt());
		flightSelectionTest.selectFlights();
		flightSelectionTest.confirmFlights();
	}
	
	@Test(dependsOnMethods = "flightSelectionTest")
	public void flightReservationConfirmationTest() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		Assert.assertTrue(flightConfirmationPage.isAt());
		Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
	}
	
}
