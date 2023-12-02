package ui.pages;
//Страница фильтров

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import static com.codeborne.selenide.Selenide.*;

public class CarsSearchOptionsPage {
    private int cityIndex;
    private int brandIndex;
    private int modelIndex;
    private final SelenideElement discontCheckbox = $x("//*[text()='Только авто со скидкой']/../label");
    private final ElementsCollection cityList =
            $$x("//label[@id='filter-city']/..//div[@class='sbl-filter-checkbox-block']/div");
    private final SelenideElement filterCity = $x("//*[@id='filter-city']/input");
    private final ElementsCollection brandList =
            $$x("//label[@id='filter-mark']/..//div[@class='sbl-filter-checkbox-block']/div");
    private final SelenideElement filterBrand = $x("//*[@id='filter-mark']/input");
    private final SelenideElement filterBrandChoice = $x("//*[@id='filter-mark']/span");
    private final ElementsCollection modelList =
            $$x("//label[@id='filter-model']/..//div[@class='sbl-filter-checkbox-block']/div");
    private final SelenideElement filterModel = $x("//*[@id='filter-model']/input");
    private final SelenideElement enginePowerMin = $x( "(//div[text()=' Мощность двигателя ']" +
            "/..//*[contains(@class, 'button el-tooltip__trigger')])[1]");
    private final SelenideElement enginePowerValue =
            $x("//div[text()=' Мощность двигателя ']/..//div[@class='range-slider-values']");
    private final ElementsCollection activeTransmissionCheckboxes =
            $$x( "//*[text()=' Привод ']/..//input[not(@disabled)]");
    private final SelenideElement firstTransmissionCheckbox =
            $x( "//*[text()=' Привод ']/..//input[not(@disabled)][1]/../label");
    private final ElementsCollection activeTransmissionBoxCheckboxes =
            $$x( "//*[text()=' Коробка передач ']/..//input[not(@disabled)]");
    private final SelenideElement firstTransmissionBoxCheckbox =
            $x( "//*[text()=' Коробка передач ']/..//input[not(@disabled)][1]/../label");
    private final ElementsCollection activeBodyTypeCheckboxes =
            $$x( "//*[text()=' Тип кузова ']/..//input[not(@disabled)]");
    private final SelenideElement firstBodyTypeCheckbox = $x( "//*[text()=' Тип кузова ']" +
            "/..//input[not(@disabled)][1]/..//*[@class='checkboxes-body-type__label']");
    private final SelenideElement engineCapasityMax = $x( "(//div[text()=' Объём двигателя ']" +
            "/..//*[contains(@class, 'el-tooltip__trigger')])[2]");
    private final SelenideElement engineCapasityValue =
        $x("//div[text()=' Объём двигателя ']/..//div[@class='range-slider-values']");
    private final ElementsCollection activeFuelTypeCheckboxes =
            $$x( "//*[text()=' Тип топлива ']/..//input[not(@disabled)]");
    private final SelenideElement firstFuelTypeCheckbox =
            $x( "(//*[text()=' Тип топлива ']/..//input[not(@disabled)])[1]/../label");
    private final SelenideElement colourOption = $x( "//*[text()=' Цвет ']/..//input[@placeholder]");
    private final SelenideElement firstColour
            = $x( "(//*[text()=' Цвет ']/..//label[@class='sbl-filter-checkbox__title'])[1]");
    private final SelenideElement showAllButton = $x( "//*[text()=' Показать все предложения ']");

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
        getBrandChoiceLocator().click();
        filterBrand.click();
    }

    public String getFilterBrand() {
        return filterBrandChoice.getText().trim();
    }

    public void setModelOption() {
        updateDropdownList(modelList, filterModel);
        filterModel.click();
        getModelChoiceLocator().click();
        filterModel.click();
    }

    private void waitSliderApplying(SelenideElement sliderValue) {
        boolean FilterDetected = WebDriverRunner.getWebDriver().getCurrentUrl().contains("set_filter");
        if (FilterDetected) {
            String ValueBefore = sliderValue.getAttribute("textContent");
            String ValueAfter = sliderValue.getAttribute("textContent");
            for (int i = 0; i < 100; i++) {
                if (ValueBefore.equals(ValueAfter)) {
                    ValueAfter = sliderValue.getAttribute("textContent");
                } else
                    break;
            }
        }
    }

    public void setEnginePower() {
        waitSliderApplying(enginePowerValue);
        actions().moveToElement(enginePowerMin).clickAndHold().moveByOffset(300, 0)
                .release().perform();
    }

    private void firstActiveCheckboxClick(ElementsCollection activeCheckboxes, SelenideElement firstCheckbox) {
        if (activeCheckboxes.size() > 0) {
            firstCheckbox.click();
        }
    }

    public void setTransmission() {
        firstActiveCheckboxClick(activeTransmissionCheckboxes, firstTransmissionCheckbox);
    }

    public void setTransmissionBox() {
        firstActiveCheckboxClick(activeTransmissionBoxCheckboxes, firstTransmissionBoxCheckbox);
    }

    public void setBodyType() {
        firstActiveCheckboxClick(activeBodyTypeCheckboxes, firstBodyTypeCheckbox);
    }

    public void setEngineCapacity() {
        waitSliderApplying(engineCapasityValue);
        actions().moveToElement(engineCapasityMax).clickAndHold().moveByOffset(-150, 0)
                .release().perform();
    }

    public void setFuelType() {
        firstActiveCheckboxClick(activeFuelTypeCheckboxes, firstFuelTypeCheckbox);
    }

    public void setColour() {
        colourOption.click();
        firstColour.click();
        colourOption.click();
    }

    public void showAllButtonClick() {
        showAllButton.click();
    }
}



