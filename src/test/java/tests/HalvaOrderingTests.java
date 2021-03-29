package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class HalvaOrderingTests extends TestBase {

    @Test
    @DisplayName("Проверить создание заявки на карту ХАЛВА")
    @Story("Пользовател должен создать заявку на карту Халва")
    @Tag("halva_tests")
    @Owner("Igor Pavlov")

    public void createNewApplicationCard() {
        halva.openMainPage();
        halva.goToHalvaCardPage();
        halva.checkOrderPage();
        halva.orderCard();
        halva.fillClientForm();
        halva.checkCompletionApplication();
    }
}