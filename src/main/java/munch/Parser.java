package munch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Converts a string into a LocalDate object.
     * String has to be in the format of dd/MM/yyyy.
     * @param date String to be converted into LocalDate object.
     * @return LocalDate object.
     */
    public static LocalDate convertToDate(String date) {
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.from(sf.parse(date));
        return localDate;
    }

    public static LocalDate wordsToDate(String date) {
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate localDate = LocalDate.from(sf.parse(date));
        return localDate;
    }
}
