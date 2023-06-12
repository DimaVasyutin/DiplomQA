package Tests;

import Data.DataHelper;
import Data.SqlHelper;
import Pages.DashboardPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDebitCard {
    private DashboardPage mainPage;

    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        mainPage = open("http://localhost:8080/", DashboardPage.class);
    }

//Позитивные
    //ApprovedCard
    @Test
    void shouldBuyDebitApprovedCard() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitSuccessedNotification();
        var expected = DataHelper.getApprovedCardInfo().getStatus();
        var actual = SqlHelper.getDebitPaymentStatus();
        assertEquals(expected, actual);
    }

    //DeclinedCard

    @Test
    void shouldBuyDebitDeclinedCard() {
        var infoApproveCard = DataHelper.getDeclinedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitFailedNotification();
        var expected = DataHelper.getApprovedCardInfo().getStatus();
        var actual = SqlHelper.getCreditPaymentStatus();
        assertEquals(expected, actual);
    }

//Негативные
    @Test
    void shouldByuNotApprovedDebitCard() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(DataHelper.getRandomCardNumber(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitFailedNotification();
    }

    @Test
    void shouldFillCardNumberEnSymbol() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(DataHelper.getRandomStringEn(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageNumberCard();
    }

    @Test
    void shouldFillCardNumberRuSymbol() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(DataHelper.getRandomStringRu(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageNumberCard();
    }

    @Test
    void shouldFillCardNumberSpecSymbol() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(DataHelper.getSpecSymbol(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageNumberCard();
    }

    @Test
    void shouldFillCardNumberShorter() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(DataHelper.getCardShorter(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageNumberCard();
    }

    @Test
    void shouldFillCardNumberSpaceBar() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(DataHelper.getSpaceBar(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageNumberCard();
    }

    @Test
    void shouldFillCardNumberEmpty() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm("", infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageEmptyNumberCard();
    }

//Негативные для поля месяц

    @Test
    void shouldFillMonthFromPast() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), DataHelper.getMonthFromPast(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageMonthsWrongFormat();
    }

    @Test
    void shouldFillMonthThenTwelve() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), DataHelper.getMonthOverTwelve(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageMonthsWrongFormat();
    }

    @Test
    void shouldFillZeroMonth() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), DataHelper.getZeroMonthOrYear(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageMonthsWrongFormat();
    }

    @Test
    void shouldFillMonthOneSymbol() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), DataHelper.getOneSymbol(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageMonthsWrongFormat();
    }

    @Test
    void shouldFillMonthRandomStringEn() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), DataHelper.getRandomStringEn(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageMonthsWrongFormat();
    }

    @Test
    void shouldFillMonthRandomStringRu() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), DataHelper.getRandomStringRu(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageMonthsWrongFormat();
    }

    @Test
    void shouldFillMonthRandomSpecSymbol() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), DataHelper.getSpecSymbol(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageMonthsWrongFormat();
    }

    @Test
    void shouldFillMonthSpaceBar() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), DataHelper.getSpaceBar(), infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageMonthsWrongFormat();
    }

    @Test
    void shouldFillMonthEmpty() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), "", infoApproveCard.getYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageEmptyMonths();
    }

//Негативные для поля Год

    @Test
    void shouldFillYearFromPast() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), DataHelper.getYearFromPast(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageYears();
    }

    @Test
    void shouldFillYearZero() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), DataHelper.getZeroMonthOrYear(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageYears();
    }

    @Test
    void shouldFillYearOneSymbol() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), DataHelper.getOneSymbol(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageYearsWrongFormat();
    }

    @Test
    void shouldFillYearRandomStringEn() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), DataHelper.getRandomStringEn(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageYearsWrongFormat();
    }

    @Test
    void shouldFillYearRandomStringRu() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), DataHelper.getRandomStringRu(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageYearsWrongFormat();
    }

    @Test
    void shouldFillYearRandomSpecSymbol() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), DataHelper.getSpecSymbol(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageYearsWrongFormat();
    }

    @Test
    void shouldFillYearSpaceBar() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), DataHelper.getSpaceBar(), infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageYearsWrongFormat();
    }

    @Test
    void shouldFillYearEmpty() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), "", infoApproveCard.getOwner(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageEmptyYears();
    }

//Негативные для поля Владелец карты

    @Test
    void shouldFillRuName() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), DataHelper.getRuName(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageNameWrongFormat();
    }

    @Test
    void shouldFillNameRandomSpecSymbol() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), DataHelper.getSpecSymbol(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageNameWrongFormat();
    }

    @Test
    void shouldFillNameSpaceBar() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), DataHelper.getSpaceBar(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageNameWrongFormat();
    }

    @Test
    void shouldFillNameSymbol() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), DataHelper.getSeveralSymbol(), infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageNameWrongFormat();
    }

    @Test
    void shouldFillNameEmpty() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), "", infoApproveCard.getCvccvv());
        mainPage.waitErorrMassageEmptyName();
    }

//Негативные для CVCCVV

    @Test
    void shouldFillCvcCvvLessThree() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), DataHelper.getLessThreeCVC());
        mainPage.waitErorrMassageCvccvvWrongFormat();
    }

    @Test
    void shouldFillCvcCvvZero() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), DataHelper.getZeroCVC());
        mainPage.waitErorrMassageCvccvvWrongFormat();
    }

    @Test
    void shouldFillCvcCvvStringEn() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), DataHelper.getRandomStringEn());
        mainPage.waitErorrMassageCvccvvWrongFormat();
    }

    @Test
    void shouldFillCvcCvvStringRu() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), DataHelper.getRandomStringRu());
        mainPage.waitErorrMassageCvccvvWrongFormat();
    }

    @Test
    void shouldFillCvcCvvRandomSpecSymbol() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), DataHelper.getSpecSymbol());
        mainPage.waitErorrMassageCvccvvWrongFormat();
    }

    @Test
    void shouldFillCvcCvvSpaceBar() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), DataHelper.getSpaceBar());
        mainPage.waitErorrMassageCvccvvWrongFormat();
    }

    @Test
    void shouldFillCvcCvvEmpty() {
        var infoApproveCard = DataHelper.getApprovedCardInfo();
        mainPage.PaymentFormDebit();
        mainPage.fillForm(infoApproveCard.getNumberCard(), infoApproveCard.getMonth(), infoApproveCard.getYear(), infoApproveCard.getOwner(), "");
        mainPage.waitErorrMassageEmptyCvccvv();
    }
}
