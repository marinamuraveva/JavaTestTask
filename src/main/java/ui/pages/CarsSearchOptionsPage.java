package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CarsSearchOptionsPage {
    private final SelenideElement discontCheckbox = $x("//*[text()='Только авто со скидкой']/../label");
    private final ElementsCollection cityList =
            $$x("//label[@id='filter-city']/..//div[@class='sbl-filter-checkbox-block']/div");
    private final SelenideElement filterCity = $x("//*[@id='filter-city']/input");
    private final ElementsCollection brandList =
            $$x("//label[@id='filter-mark']/..//div[@class='sbl-filter-checkbox-block']/div");
    private final SelenideElement filterBrand = $x("//*[@id='filter-mark']/input");
    private final ElementsCollection modelList =
            $$x("//label[@id='filter-model']/..//div[@class='sbl-filter-checkbox-block']/div");
    private final SelenideElement filterModel = $x("//*[@id='filter-model']/input");
    private int cityIndex;
    private int brandIndex;
    private int modelIndex;

    public CarsSearchOptionsPage() {
    }

    public void setCityIndex(int cityIndex) {
        this.cityIndex = cityIndex;
    }

    public void setBrandIndex(int brandIndex) {
        this.brandIndex = brandIndex;
    }

    public void setModelIndex(int modelIndex) {
        this.modelIndex = modelIndex;
    }

    public void discountCheckboxClick() {
        discontCheckbox.shouldBe().isEnabled();
        discontCheckbox.click();
    }

    private SelenideElement getCityChoiceLocator() {
        return $x("//*[@id='filter-city']/input/../following-sibling::div//div[@role='option']["
                + this.cityIndex + "]");
    }

    private SelenideElement getBrandChoiceLocator() {
        return $x("//*[@id='filter-mark']/input/../following-sibling::div//div[@role='option']["
                + this.brandIndex + "]");
    }

    private SelenideElement getModelChoiceLocator() {
        return $x("//*[@id='filter-model']/input/../following-sibling::div//div[@role='option']["
                + this.modelIndex + "]");
    }

    private void updateDropdownList(ElementsCollection list, SelenideElement filterLocator) {
        boolean FilterDetected = WebDriverRunner.getWebDriver().getCurrentUrl().contains("set_filter");
        if (FilterDetected) {
            int CountBefore = list.size();
            filterLocator.click();
            int CountAfter = list.size();
            for(int i = 0; i < 10; i++) {
                if (CountBefore == CountAfter) {
                    filterLocator.doubleClick();
                    CountAfter = list.size();
                }
            }
        }
    }

    public void setCityOption() {
        updateDropdownList(cityList, filterCity);
        getCityChoiceLocator().click();
        filterCity.click();
    }

    public void setBrandOption() {
        updateDropdownList(brandList, filterBrand);
        filterBrand.click();
        getBrandChoiceLocator().click();
        filterBrand.click();
    }

    public void setModelOption() {
        updateDropdownList(modelList, filterModel);
        filterModel.click();
        getModelChoiceLocator().click();
        filterModel.click();
    }
}


//
//@allure.step("Выбираем Марку авто из списка")
//    def set_brand_option(self):
//            self.driver.find_element(*CarSearchOptions.FILTER_BRAND).click()
//            self.driver.find_element(*self.car.get_brand_choice_locator()).click()
//            self.driver.find_element(*CarSearchOptions.FILTER_BRAND).click()
//            time.sleep(2)
//
//@allure.step("Определяем выбранную Марку авто")
//    def get_filter_brand_choice(self):
//            return (self.driver.find_element(*CarSearchOptions.FILTER_BRAND_CHOISE).get_attribute("textContent")).strip()
//
//@allure.step("Выбираем Модель авто из списка")
//    def set_model_option(self):
//            self.driver.find_element(*CarSearchOptions.FILTER_MODEL).click()
//            self.driver.find_element(*self.car.get_model_choice_locator()).click()
//            self.driver.find_element(*CarSearchOptions.FILTER_MODEL).click()
//            time.sleep(2)
//
//@allure.step("Скролл блока выбора опций авто")
//    def car_options_block_scroll(self):
//            self.driver.find_element(*CarSearchOptions.FILTER_CITY).location_once_scrolled_into_view
//
//@allure.step("Указываем Мощность двигателя")
//    def set_engine_power(self):
//            slider = self.driver.find_element(*CarSearchOptions.ENGINE_POWER_MIN)
//            slider.click()
//            ActionChains(self.driver).drag_and_drop_by_offset(slider, 3, 0).perform()