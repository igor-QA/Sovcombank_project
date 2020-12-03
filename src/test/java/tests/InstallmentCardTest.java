package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class InstallmentCardTest {
    Faker faker = new Faker(new Locale("ru"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.1999");
    private final String birthDate = LocalDate.now().plusDays(4).format(formatter);
    private final String firstName = faker.name().firstName();
    private final String lastName = faker.name().lastName();
    private final String phoneNumber = faker.phoneNumber().cellPhone();
    private final static String region = "Астраханская область", //TODO Добавить рандомный выбор области
                                localityAddress = "г Астрахань";

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
        Configuration.browser = "firefox";
    }

    @Test
    @DisplayName("Проверить создание заявки на карту ХАЛВА")
    @Story("Пользовател должен создать заявку на карту Халва")
    public void createNewApplicationCard() {
        BaseSteps steps = new BaseSteps();
        steps.openMainPage();
        steps.goToHalvaCardPage();
        steps.checkOrderPage();
        steps.orderCard();
        steps.inputDataPage();
        steps.checkCompletionApplication();

    }

    public class BaseSteps {
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
            $("html").waitUntil(visible, 3000).shouldHave(text("Халва"));
        }
        @Step("Заказать карту Халва")
        public void orderCard() {
            $(byText("Заказать карту")).click();
            $("div.jss62").shouldHave(text("Получи Халву сейчас!"));
            $("div.jss74").click();
        }
        @Step("Ввод данных потенциального клиента")
        public void inputDataPage() {
            $(byName("fio")).setValue(firstName + " " + lastName);
            $(byName("birthDate")).setValue(birthDate);
            $(byName("phone")).setValue(phoneNumber);
            $(byName("region")).click();
            $$(byText(region)).find(visible).click();
            $(byName("localityAddress")).setValue("Астра");
            $$(byText(localityAddress)).find(visible).click();
            $("div.formBtnOuter button").click();

        }
        @Step("Проверить завершение процесса заявки на карту Халва")
        public void checkCompletionApplication () {
            //$("html").shouldHave(text("Вы уже подавали заявку на Халву!"));
            $("html").shouldHave(text("Выберите способ получения Вашей карты Халва"));
        }
    }
}





