import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WorkPracticeForm {
    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";

    }

    @Test
    void workPracticeForm() {
        String firstName = "Artur";
        String lastName = "Cherepanov";
        String userEmail = "art.cherepanov@yandex.ru";
        String userNumber = "79128877287";
        String currentAddress = "Big Willy Road 155/2";

        //Открыть страницу Practice Form
        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));
        //Убрать рекламу и футер
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //Внести данные в форму
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--007:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Physic").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("Pictures/фин_юмор2.jpg");
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#state").$(byText("Haryana")).click();
        $("#city").click();
        $("#city").$(byText("Panipat")).click();
        $("#submit").click();

        //Проверка заполненной формы
        $(".modal-content").shouldBe(visible);
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(text(firstName),
                text(lastName),
                text(userEmail),
                text("Male"),
                text("7912887728"),
                text("07 February,1990"),
                text("Physics"),
                text("Reading"),
                text(currentAddress),
                text("Haryana Panipat"));

    }
}

