package ui.pages;
//Плашка Cookie

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Cookie {
    private final SelenideElement cookieCloseButton =
            $x("//div[contains(text(), '«Сбербанк Лизинг» использует cookie')]/../button");

    public Cookie() {
    }

    public void сloseCookie() {
        cookieCloseButton.click();
    }
}
