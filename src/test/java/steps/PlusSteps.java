package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

public class PlusSteps {

    int fNumber;
    int sNumber;
    int result;

    @Дано("два числа: {int} и {int}")
    public void initTwoDigits(int firstNumber, int secondNumber) {
        fNumber = firstNumber;
        sNumber = secondNumber;
    }

    @Когда("складываю их")
    public void plus() {
        result = fNumber + sNumber;
    }

    @Тогда("получаю {int}")
    public void result(int int1) {
        if (int1 != result) {
            throw new IllegalArgumentException("waiting " + int1 + ", but it was " + result);
        }
    }

}
