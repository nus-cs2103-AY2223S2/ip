package munch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static LocalDate convertToDate(String date) {
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.from(sf.parse(date));
        return localDate;
    }

    public static String printFormat(String by) {
        String newFormat = by.format(String.valueOf(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return newFormat;
    }
}
