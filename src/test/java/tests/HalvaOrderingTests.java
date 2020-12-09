package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class HalvaOrderingTests  extends TestBase {

    @Test
    @DisplayName("Проверить создание заявки на карту ХАЛВА")
    @Story("Пользовател должен создать заявку на карту Халва")
    @Tag("halva_tests")
    @Owner("Igor Pavlov")
    public void createNewApplicationCard() {
        BaseSteps steps = new BaseSteps();
        steps.openMainPage();
        steps.goToHalvaCardPage();
        steps.checkOrderPage();
        steps.orderCard();
        steps.fillClientForm();
        steps.checkCompletionApplication();

    }
    public class BaseSteps  {
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
            $("html").waitUntil(visible, 4000).shouldHave(text("Заказать карту"));
        }
        @Step("Заказать карту Халва")
        public void orderCard() {
            $(byText("Заказать карту")).click();
            $("div.jss62").shouldHave(text("Получи Халву сейчас!"));
            $("div.jss74").click();
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
            $("div.formBtnOuter button").click();

        }
        @Step("Проверить завершение процесса заявки на карту Халва")
        public void checkCompletionApplication () {
            //$("html").shouldHave(text("Вы уже подавали заявку на Халву!"));
            $("html").shouldHave(text("Выберите способ получения Вашей карты Халва"));
            //TODO Нужно подумать про проверку
        }
    }
}





