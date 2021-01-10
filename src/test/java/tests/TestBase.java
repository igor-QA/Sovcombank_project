package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import helpers.ConfigHelper;
import helpers.RandomRegions;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;

public class TestBase {

    Faker faker = new Faker(new Locale("ru"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.");

    public final int year = ThreadLocalRandom.current().nextInt(1935, 2000);
    public final String birthDate = LocalDate.now().plusDays(4).format(formatter);
    public final String firstName = faker.name().firstName();
    public final String lastName = faker.name().lastName();
    public final String phoneNumber = "9" + faker.phoneNumber().phoneNumber();
    public final static String region = RandomRegions.getRegion();

    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.remote = ConfigHelper.getURL();
        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;
        //Configuration.browser = "firefox";

    }

    @AfterEach
    @Step("Attachments")
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();

        closeWebDriver();
    }
}