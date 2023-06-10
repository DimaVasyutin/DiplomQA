package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private SelenideElement buyButton = $x("//*[@class='button__content']//*[text()='Купить']");
    private SelenideElement buyButtonInCredit = $x("//*[@class='button__content']//*[text()='Купить в кредит']");

    private SelenideElement numberCard = $x("//*[text()='Номер карты']/ancestor::span/child::span[@class='input__box']/input");
    private SelenideElement years = $x("//*[text()='Год']/ancestor::span/child::span[@class='input__box']/input");
    private SelenideElement months = $x("//*[text()='Месяц']/ancestor::span/child::span[@class='input__box']/input");
    private SelenideElement owner = $x("//*[text()='Владелец']/ancestor::span/child::span[@class='input__box']/input");
    private SelenideElement cvccvv = $x("//*[text()='CVC/CVV']/ancestor::span/child::span[@class='input__box']/input");

    private SelenideElement continueButton = $x("//*[text()='Продолжить']");

    private SelenideElement successedNotification = $x(("//*[text()='Операция одобрена Банком.']"));
    private SelenideElement failedNotification = $x(("//*[text()='Ошибка! Банк отказал в проведении операции.']"));

    private SelenideElement erorrMassageNameWrongFormat = $x("//*[text()='Владелец']/ancestor::span/child::span[text()='Неверный формат']");
    private SelenideElement erorrMassageEmptyName = $x("//*[text()='Владелец']/ancestor::span/child::span[text()='Поле обязательно для заполнения']");
    private SelenideElement erorrMassageNumberCard = $x("//*[text()='Номер карты']/ancestor::span/child::span[text()='Неверный формат']");
    private SelenideElement erorrMassageEmptyNumberCard = $x("//*[text()='Номер карты']/ancestor::span/child::span[text()='Поле обязательно для заполнения']");
    private SelenideElement erorrMassageYearsWrongFormat = $x("//*[text()='Год']/ancestor::span/child::span[text()='Неверный формат']");
    private SelenideElement erorrMassageEmptyYears = $x("//*[text()='Год']/ancestor::span/child::span[text()='Поле обязательно для заполнения']");
    private SelenideElement erorrMassageYears = $x("//*[text()='Год']/ancestor::span/child::span[text()='Истёк срок действия карты']");
    private SelenideElement erorrMassageMonthsWrongFormat= $x("//*[text()='Месяц']/ancestor::span/child::span[text()='Неверный формат']");
    private SelenideElement erorrMassageEmptyMonths= $x("//*[text()='Месяц']/ancestor::span/child::span[text()='Поле обязательно для заполнения']");
    private SelenideElement erorrMassageCvccvvWrongFormat= $x("//*[text()='CVC/CVV']/ancestor::span/child::span[text()='Неверный формат']");
    private SelenideElement erorrMassageEmptyCvccvv= $x("//*[text()='CVC/CVV']/ancestor::span/child::span[text()='Поле обязательно для заполнения']");


    public void PaymentFormDebit(){
        buyButton.click();
    }

    public void PaymentFormCredit(){
        buyButtonInCredit.click();
    }

    public void fillForm(String cardNumber, String month, String year, String cardOwner, String code){
        numberCard.setValue(cardNumber);
        months.setValue(month);
        years.setValue(year);
        owner.setValue(cardOwner);
        cvccvv.setValue(code);
        continueButton.click();
    }
    public void waitFailedNotification(){
        failedNotification.shouldBe(Condition.visible, Duration.ofSeconds(11));
    }

    public void waitErorrMassageNameWrongFormat(){
        erorrMassageNameWrongFormat.shouldBe(Condition.visible);
    }
    public void waitErorrMassageEmptyName(){
        erorrMassageEmptyName.shouldBe(Condition.visible);
    }

    public void waitErorrMassageNumberCard(){
        erorrMassageNumberCard.shouldBe(Condition.visible);
    }
    public void waitErorrMassageEmptyNumberCard(){
        erorrMassageEmptyNumberCard.shouldBe(Condition.visible);
    }

    public void waitErorrMassageYears(){
        erorrMassageYears.shouldBe(Condition.visible);
    }
    public void waitErorrMassageEmptyYears(){
        erorrMassageEmptyYears.shouldBe(Condition.visible);
    }

    public void waitErorrMassageYearsWrongFormat(){
        erorrMassageYearsWrongFormat.shouldBe(Condition.visible);
    }

    public void waitErorrMassageEmptyMonths(){
        erorrMassageEmptyMonths.shouldBe(Condition.visible);
    }
    public void waitErorrMassageMonthsWrongFormat(){
        erorrMassageMonthsWrongFormat.shouldBe(Condition.visible);
    }

    public void waitErorrMassageEmptyCvccvv(){
        erorrMassageEmptyCvccvv.shouldBe(Condition.visible);
    }
    public void waitErorrMassageCvccvvWrongFormat(){
        erorrMassageCvccvvWrongFormat.shouldBe(Condition.visible);
    }

    public void waitSuccessedNotification(){
        successedNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }


}
