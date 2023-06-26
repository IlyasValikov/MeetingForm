package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MeetingFormTest {

    @BeforeEach
    void setUp() {
        open( "http://localhost:9999");

    }
    private String dates(int addedDays) {
        LocalDate date = LocalDate.of(2023,6,29);
        LocalDate newDate = date.plusDays(addedDays);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateAdd = newDate.format(dateTimeFormatter);
        return dateAdd;
    }

    @Test
    void shouldFillForm() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(dates(7));
        $("[data-test-id=name] input").setValue("Валиков Илья");
        $("[data-test-id=phone] input").setValue("+12345678909");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));


    }
}
