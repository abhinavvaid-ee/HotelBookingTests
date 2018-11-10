package utils;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    private Faker faker = new Faker();

    public String getFirstName(){
        return faker.name().firstName();
    }

    public String getLastName(){
        return faker.name().lastName();
    }

    public String getRandomNumber() {
//      max, min can be parameterised
        int min = 1;
        int max = 1000;
        int range = max - min + 1;

        int randomInt = (int) (Math.random() * range) + min;
        return String.valueOf(randomInt);
    }

    public String getFutureDate(int daysInFuture) {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.now().plusDays(daysInFuture);
        return dateTimeFormatter.format(dateTime);
    }
}
