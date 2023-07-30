package data;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import com.github.javafaker.Faker;

public class DataHelper {

    private static Faker fakerEn = new Faker(new Locale("us"));
    private static Faker fakerRu = new Faker(new Locale("ru"));
    private static LocalDate date = LocalDate.now();
    private static Random random = new Random();
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String numberCard;
        private String month;
        private String year;
        private String owner;
        private String cvccvv;
        private String status;
    }


    public static CardInfo getApprovedCardInfo() {
        var shiftedDate = date.plusMonths(fakerEn.number().numberBetween(12,50));
        return new CardInfo("4444444444444441", shiftedDate.format(DateTimeFormatter.ofPattern("MM")), shiftedDate.format(DateTimeFormatter.ofPattern("yy")), fakerEn.name().fullName(), String.valueOf(fakerEn.number().numberBetween(100, 999)),"APPROVED");
    }

    public static CardInfo getDeclinedCardInfo() {
        var shiftedDate = date.plusMonths(fakerEn.number().numberBetween(12,50));
        return new CardInfo("4444444444444442", shiftedDate.format(DateTimeFormatter.ofPattern("MM")), shiftedDate.format(DateTimeFormatter.ofPattern("yy")), fakerEn.name().fullName(), String.valueOf(fakerEn.number().numberBetween(100, 999)), "DECLINED");
    }

    public static String getRandomCardNumber() {
        return fakerRu.business().creditCardNumber();
    }

    public static String getRandomStringEn() {
        return fakerEn.letterify("????????");
    }

    public static String getRandomStringRu() {
        return fakerRu.letterify("????????");
    }

    public static String getCardShorter() {
        return fakerRu.number().digits(15);
    }
    public static String getSpecSymbol() {
        var symbols = "!@#&*/^%";
        return String.valueOf(symbols.charAt(random.nextInt(symbols.length())));
    }
    public static String getSpaceBar(){      //наверно лучше бы через .sendKeys
        return "    ";
    }

    public static String getMonthFromPast() {
        var shiftedDate = date.plusMonths(-1);//проблема с 01 месяцем
        return shiftedDate.format(DateTimeFormatter.ofPattern("MM"));
    }
    public static String getMonthOverTwelve() {
        return String.valueOf(fakerEn.number().numberBetween(13,99)) ;
    }

    public static String getOneSymbol() {
        return String.valueOf(fakerEn.number().numberBetween(1,9)) ;
    }
    public static String getZeroMonthOrYear() {
        return "00";
    }

    public static String getYearFromPast() {
        var shiftedDate = date.plusMonths(fakerEn.number().numberBetween(-99,-13));
        return shiftedDate.format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getThisYear() {
        return date.format(DateTimeFormatter.ofPattern("yy"));
    }



    public static String getRuName() {
        return String.valueOf(fakerRu.name().fullName());
    }

    public static String getSeveralSymbol() {
        return String.valueOf(fakerEn.number().numberBetween(10000,99999)) ;
    }

    public static String getZeroCVC() {
        return "000";
    }
    public static String getLessThreeCVC() {
        return String.valueOf(fakerEn.number().numberBetween(1,99));
    }
    //посмотреть можно ли больше трех




}




