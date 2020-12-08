package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class TestBase {
    Faker faker = new Faker(new Locale("ru"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.");

    public final int year = ThreadLocalRandom.current().nextInt(1935, 2000);
    public final String birthDate = LocalDate.now().plusDays(4).format(formatter);
    public final String firstName = faker.name().firstName();
    public final String lastName = faker.name().lastName();
    public final String phoneNumber = "9" + faker.phoneNumber().phoneNumber();
    public final static String region = RegionSteps.getRegion();

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
        //Configuration.browser = "firefox";
    }

}

