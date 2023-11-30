package ui.pages;
//Поисковая страница Google

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {
    private final SelenideElement googleSearchInput = $x("//*[@type='search']");
    private final static String GOOGLE_URL = "https://www.google.com/";

    public SearchPage() {
        Selenide.open(GOOGLE_URL);
    }

    public void openSiteByGoogleSearch(String searchString, SelenideElement sberleasingLink) {
        googleSearchInput.sendKeys(searchString + "\n");
        sberleasingLink.click();
    }
}
