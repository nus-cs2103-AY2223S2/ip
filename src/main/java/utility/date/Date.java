package utility.date;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date implements Serializable {
    private static String format = "MMM dd yyyy";
    private LocalDate date;

    public Date(String dateStr) throws DateTimeParseException {
        this.date = LocalDate.parse(dateStr);
    }

    public String dateToString() {
        return this.date.format(DateTimeFormatter.ofPattern(format));
    }

    public String dateToFileText() {
        return this.date.toString();
    }

}
