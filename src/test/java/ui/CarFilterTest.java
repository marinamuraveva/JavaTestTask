package ui;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ui.baseSettings.DriverSingleton;
import ui.pages.CarsSearchOptionsPage;
import ui.pages.Cookie;
import ui.pages.MainPage;
import ui.pages.SearchPage;

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
        carsSearchOptionsPage.setCityIndex(2);
        carsSearchOptionsPage.setBrandIndex(1);
        carsSearchOptionsPage.setModelIndex(1);
        carsSearchOptionsPage.discountCheckboxClick();
        carsSearchOptionsPage.setCityOption();
        carsSearchOptionsPage.setBrandOption();
        carsSearchOptionsPage.setModelOption();

    }
}
