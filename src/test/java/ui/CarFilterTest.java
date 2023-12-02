package ui;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ui.baseSettings.DriverSingleton;
import ui.pages.*;

import static com.codeborne.selenide.Selenide.$x;

public class CarFilterTest extends DriverSingleton {
    private final static String SEARCH_STRING = "СберЛизинг";
    private final static SelenideElement sberleasingLink = $x("//*[@href='https://www.sberleasing.ru/']");

    public CarFilterTest() {
        super();
    }

    @Test
    public void carBrandMatchingTest() {
        new SearchPage().openSiteByGoogleSearch(SEARCH_STRING, sberleasingLink);
        new Cookie().сloseCookie();
        new MainPage().goToCarSearchOptions();

        CarsSearchOptionsPage carsSearchOptionsPage = new CarsSearchOptionsPage();
        carsSearchOptionsPage.discountCheckboxClick();
        carsSearchOptionsPage.setCityIndex(2);
        carsSearchOptionsPage.setBrandIndex(1);
        carsSearchOptionsPage.setModelIndex(1);
        carsSearchOptionsPage.setCityOption();
        carsSearchOptionsPage.setBrandOption();
        carsSearchOptionsPage.setModelOption();
        carsSearchOptionsPage.setEnginePower();
        carsSearchOptionsPage.setTransmission();
        carsSearchOptionsPage.setTransmissionBox();
        carsSearchOptionsPage.setBodyType();
        carsSearchOptionsPage.setEngineCapacity();
        carsSearchOptionsPage.setFuelType();
        carsSearchOptionsPage.setColour();
        String filterBrandChoice = carsSearchOptionsPage.getFilterBrand();
        carsSearchOptionsPage.showAllButtonClick();

        CarSuggestionListPage carSuggestionListPage = new CarSuggestionListPage();
        carSuggestionListPage.setSuggestionIndex(1);
        String suggestionListBrand = carSuggestionListPage.getSuggestionListBrand();
        carSuggestionListPage.showSuggestionButtonClick();

        assert filterBrandChoice.equals(suggestionListBrand) : "Марка на форме фильтров - " + filterBrandChoice +
                ", а Марка в списке предложений - " + suggestionListBrand;

        String suggestionInfo = new CarSuggestion().getSuggestionInfo();

        assert suggestionInfo.contains(suggestionListBrand) : "Марка в списке предложений - " + suggestionListBrand +
              ", а авто на странице предложения - " + suggestionInfo;
    }
}
