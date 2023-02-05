package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date in a specified format.
 */
public class MyDateTime {
    protected static DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern(("dd/MM/yyyy HH:mm"));
    protected static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy MMM dd, HH:mm");

    protected LocalDateTime dateTime;
    public MyDateTime(String dateTime) {
        this.dateTime = getValidDateTime(dateTime);
    }

    public LocalDateTime getValidDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, MyDateTime.saveFormat);
    }

    /**
     * Checks if a given string is a valid date and time in the specified format.
     * @param dateTime The given string.
     * @return True if the string is a valid date and time, else false.
     */
    public static boolean isValidDateTime(String dateTime) {
        try {
            LocalDateTime.parse(dateTime, MyDateTime.saveFormat);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public String formatDateTimeForFile() {
        return this.dateTime.format(saveFormat);
    }

    public String formatDateTimeForPrint() {
        return this.dateTime.format((outputFormat));
    }

    public LocalDate dateOnly() {
        return this.dateTime.toLocalDate();
    }
}
