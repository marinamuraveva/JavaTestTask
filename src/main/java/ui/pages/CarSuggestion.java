package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CarSuggestion {
    private final SelenideElement suggestionCarInfo = $x("//*[@class='h2']");
    public CarSuggestion() {
    }

    public String getSuggestionInfo() {
        return suggestionCarInfo.getText().trim();
    }

//    def get_suggestion_info(self):
//            return (self.driver.find_element(*CarSuggestionPage.SUGGESTION_CAR_INFO).get_attribute("innerText")).strip()
}
