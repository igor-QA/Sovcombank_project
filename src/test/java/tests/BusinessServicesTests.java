package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class BusinessServicesTests extends TestBase {

    @Disabled("Изменения в UI сайта")
    @Test
    @Story("Пользовател должен успешно перейти по вкалдкам Вклады")
    @DisplayName("Проверить раздел Тарифы МСБ - Вклад Успех")
    void  successfulOpenTariffPage(){
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
    @Disabled("Изменения в UI сайта")
    @Test
    @DisplayName("Проверить раздел Тарифа МСБ - Вклад Доходный")
    void unsuccessfulOpenTariffPage(){
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
                $("h1.Typography").shouldHave(text("ДОХОДНЫЙ")));

    }
}