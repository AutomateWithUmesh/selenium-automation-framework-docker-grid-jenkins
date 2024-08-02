package pages.flightreservations;

//import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.AbstractPage;

public class RegistrationCofirmationPage extends AbstractPage {

	@FindBy(id = "go-to-flights-search")
	private WebElement goToFlightsSearchButton;

	@FindBy(css = "#registration-confirmation-section p b")
	private WebElement firstNameElement;

	public RegistrationCofirmationPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearchButton));
		return this.goToFlightsSearchButton.isDisplayed();
	}
	
	public String getFirstName(){
        return this.firstNameElement.getText();
    }

	public void goToFlightSearch() {
		this.goToFlightsSearchButton.click();
	}

}
