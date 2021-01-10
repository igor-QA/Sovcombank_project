package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class HalvaBaseStep extends TestBase {

    @Step("Открыть главную страницу СовкомБанк")
    public void openMainPage() {
        open("https://sovcombank.ru");
        $(".CookieOffer__buttons").click();
    }

    @Step("Перейти на страницу заказа карты Халва")
    public void goToHalvaCardPage() {
        $$(byText("Карта рассрочки «Халва»")).find(visible).click();
        switchTo().window(1);
    }

    @Step("Проверить переход на страницу заказа")
    public void checkOrderPage() {
        $("html").shouldHave(text("Заказать карту"));
    }

    @Step("Заказать карту Халва")
    public void orderCard() {
        $(byText("Заказать карту")).click();
        $("h1.jss63.headline").shouldHave(text("Получи Халву сейчас!"));
        $("div.jss74").click();
        //$("h1.jss63.headline").scrollIntoView(true); 2-ой вариант=сразу скролл вниз.
    }

    @Step("Ввод данных потенциального клиента")
    public void fillClientForm() {
        $(byName("fio")).setValue(firstName + " " + lastName);
        $(byName("birthDate")).setValue(birthDate + year);
        $(byName("phone")).setValue(phoneNumber);
        $(byName("region")).click();
        $$(byText(region)).find(visible).click();
        $(byName("localityAddress")).setValue("А");
        $("#react-autowhatever-1--item-0").click();
        $("button.formBtn > span:nth-child(1)").click();

    }

    @Step("Проверить завершение процесса заявки на карту Халва")
    public void checkCompletionApplication() {
        //$("html").shouldHave(text("Вы уже подавали заявку на Халву!"));
        $("html").shouldHave(text("Выберите способ получения Вашей карты Халва"));
        //TODO Нужно подумать про проверку, так как результат отличается взависимости от региона.
    }

    @Test
    @DisplayName("Проверить раздел Тарифы МСБ - Вклад Успех")
    void  successfulOpenTariffPage() {
        step("Открыть главную страницу", () ->
                open("https://sovcombank.ru/business"));
        $(".CookieOffer__buttons").click();

        step("Перейти на страницу 'Подобрать тариф  для бизнеса' ", () ->
                $("#__next a:nth-child(2) h4").click());
        switchTo().window(1);

        step("Проверить наличие на страницы вкладов", () ->
                $("html").findAll("span.Products__item__inner").shouldHaveSize(4));

        step("Перейти во вклад Успех",()->
                $(byText("Успех")).click());

        step("Проверить успешность открытия страницы Вклада", () ->
                $("h2.Typography").shouldHave(text("Условия")));
    }

    @Test
    @DisplayName("Проверить раздел Тарифа МСБ - Вклад Доходный")
    void unsuccessfulOpenTariffPage() {
        step("Открыть главную страницу", () ->
                open("https://sovcombank.ru/business"));
        $(".CookieOffer__buttons").click();

        step("Перейти на страницу 'Подобрать тариф  для бизнеса' ", () ->
                $("#__next a:nth-child(2) h4").click());
        switchTo().window(1);

        step("Проверить наличие на страницы вкладов", () ->
                $("html").findAll("span.Products__item__inner").shouldHaveSize(4));

        step("Перейти во вклад Доходный",()->
                $(byText("Доходный")).click());

        step("Проверить успешность открытия страницы Вклада", () ->
                $("h1.Typography").shouldHave(text("Успех"))); //ДОХОДНЫЙ

    }
}
