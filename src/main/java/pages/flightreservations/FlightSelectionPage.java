package pages.flightreservations;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.AbstractPage;

public class FlightSelectionPage extends AbstractPage {

	@FindBy(name = "departure-flight")
	private List<WebElement> departureFlightsOptions;

	@FindBy(name = "arrival-flight")
	private List<WebElement> arrivalFlightsOptions;

	@FindBy(id = "confirm-flights")
	private WebElement confirmFlightsButton;

	public FlightSelectionPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsButton));
		return this.confirmFlightsButton.isDisplayed();
	}

	public void selectFlights() {
		int random = ThreadLocalRandom.current().nextInt(0, this.departureFlightsOptions.size());
		this.departureFlightsOptions.get(random).click();
		Actions action = new Actions(this.driver);
		WebElement selectedElement = this.arrivalFlightsOptions.get(random);
		action.moveToElement(selectedElement).build().perform();
		selectedElement.click();
		//this.arrivalFlightsOptions.get(random).click();
	}

	public void confirmFlights() {
		Actions action = new Actions(this.driver);
		action.moveToElement(this.confirmFlightsButton).build().perform();
		this.confirmFlightsButton.click();
	}
}
