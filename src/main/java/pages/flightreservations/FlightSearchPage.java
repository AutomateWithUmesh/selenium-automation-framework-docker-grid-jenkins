package pages.flightreservations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import pages.AbstractPage;

public class FlightSearchPage extends AbstractPage {
	
    @FindBy(id = "passengers")
    private WebElement passengerSelect;

    @FindBy(id = "search-flights") 
    private WebElement searchFlightsButton;

	public FlightSearchPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.searchFlightsButton));
		return this.searchFlightsButton.isDisplayed();
	}
	
	public void selectPassengers(String noOfPassengers) {
		Select passengers = new Select(this.passengerSelect);
		passengers.selectByValue(noOfPassengers);
	}
	
	public void searchFlights() {
		Actions action = new Actions(this.driver);
		action.moveToElement(this.searchFlightsButton).build().perform();
		this.searchFlightsButton.click();
		//this.searchFlightsButton.click();
	}

}
