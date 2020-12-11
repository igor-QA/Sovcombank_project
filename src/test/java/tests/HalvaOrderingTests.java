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

public class HalvaOrderingTests extends TestBase {

    @Test
    @DisplayName("Проверить создание заявки на карту ХАЛВА")
    @Story("Пользовател должен создать заявку на карту Халва")
    @Tag("halva_tests")
    @Owner("Igor Pavlov")

    public void createNewApplicationCard() {
        HalvaBaseSteps steps = new HalvaBaseSteps();
        steps.openMainPage();
        steps.goToHalvaCardPage();
        steps.checkOrderPage();
        steps.orderCard();
        steps.fillClientForm();
        steps.checkCompletionApplication();
    }

}





