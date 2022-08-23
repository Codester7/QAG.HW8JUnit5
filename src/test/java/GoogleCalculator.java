import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static com.codeborne.selenide.Selenide.*;
public class GoogleCalculator {


        @DisplayName("Проверка сложения простых чисел в калькуляторе поисковика Google. (Ввод через клик по кнопкам)")
        @Test
        void plusTest(){
            open("https://www.google.com/");
            $("[name=q]").setValue("Калькулятор");
            $("[type=submit]").click();
           $$(".card-section tbody tr td").findBy(Condition.text("2")).click();
            $$(".card-section tbody tr td").findBy(Condition.text("+")).click();
            $$(".card-section tbody tr td").findBy(Condition.text("2")).click();
            $$(".card-section tbody tr td").findBy(Condition.text("=")).click();
            $("div .card-section div span#cwos").shouldHave(Condition.text("4"));
            $$(".ElumCf tbody tr td").findBy(Condition.text("CA")).click();
            $("div .card-section div span#cwos").shouldHave(Condition.text("0"));
        }


    static Stream<Arguments> ComplexPlusTest() {
        return Stream.of(
                Arguments.of("1", "1", "2"),
                Arguments.of("1", "9", "10"));
    }

    @MethodSource("ComplexPlusTest")
    @ParameterizedTest(name = "Проверка, что при сложении числа {0} и числа {1} результат равен {2}. (Ввод через клик по кнопкам)")
    void complexPlusTest(String digit1, String digit2, String result) {
        open("https://www.google.com/");
        $("[name=q]").setValue("Калькулятор");
        $("[type=submit]").click();
        $$(".card-section tbody tr td").findBy(Condition.text(digit1)).click();
        $$(".card-section tbody tr td").findBy(Condition.text("+")).click();
        $$(".card-section tbody tr td").findBy(Condition.text(digit2)).click();
        $$(".card-section tbody tr td").findBy(Condition.text("=")).click();
        $("div .card-section div span#cwos").shouldHave(Condition.text(result));
        $$(".card-section tbody tr td [aria-label='сбросить всё']").findBy(Condition.text("AC")).click();
        $("div .card-section div span#cwos").shouldHave(Condition.text("0"));
    }

    @CsvSource(value = {
            "2|3",
            "9|10"
    },
            delimiter = '|')
    @ParameterizedTest(name="Проверка сложения числа 1 с числом {0}. (Ввод через клик по кнопкам)")
    void parametrizePlusTest(String testNumber, String parametricResult) {
        open("https://www.google.com/");
        $("[name=q]").setValue("Калькулятор");
        $("[type=submit]").click();
        $$(".card-section tbody tr td").findBy(Condition.text("1")).click();
        $$(".card-section tbody tr td").findBy(Condition.text("+")).click();
        $$(".card-section tbody tr td").findBy(Condition.text(testNumber)).click();
        $$(".card-section tbody tr td").findBy(Condition.text("=")).click();
        $("div .card-section div span#cwos").shouldHave(Condition.text(parametricResult));
        $$(".card-section tbody tr td [aria-label='сбросить всё']").findBy(Condition.text("AC")).click();
        $("div .card-section div span#cwos").shouldHave(Condition.text("0"));
    }
}
