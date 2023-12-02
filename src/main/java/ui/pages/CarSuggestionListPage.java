package ui.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class CarSuggestionListPage {
    private int suggestion_index;
    private final SelenideElement suggestionListBrand = $x("//div[text()='Марка']");
    private final SelenideElement suggestionListBrandChoice = $x("//div[text()='Марка']/../..//label");

    public CarSuggestionListPage() {
    }

    public void setSuggestionIndex(int suggestion_index) {
        this.suggestion_index = suggestion_index;
    }

    private SelenideElement getSuggestionChoiceLocator() {
        return $x("(//*[contains(text(),'Смотреть предложения')])[" + suggestion_index + "]");
    }

    public String getSuggestionListBrand() {
        suggestionListBrand.click();
        suggestionListBrandChoice.shouldBe().isSelected();
        return suggestionListBrandChoice.getText().trim();
//        return suggestionListBrandChoice.getAttribute("textContent");
    }

    public void showSuggestionButtonClick() {
        getSuggestionChoiceLocator().click();
    }
}
