package ui.pages;
//Главная страница

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement carSearchOptionsButton =
            $x("//div[contains(@class, 'text-right')]/*[contains(@href, 'marketplace')]");

    public MainPage() {
    }

//    Нажимаем кнопку 'Подобрать по параметрам' и переходим на форму automall
    public void goToCarSearchOptions() {
        carSearchOptionsButton.click();
    }
}
