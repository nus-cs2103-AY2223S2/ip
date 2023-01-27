package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MyDateTime {
    protected static DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern(("dd/MM/yyyy HH:mm"));
    protected static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy MMM dd, HH:mm");

    protected LocalDateTime dateTime;
    public MyDateTime(String dateTime) {
        this.dateTime = getValidDateTime(dateTime);
    }

    public LocalDateTime getValidDateTime(String dateTime) {
        Scanner s = new Scanner(System.in);

        while (!isValidDateTime(dateTime)) {
            System.out.println("Hey come on, give me a valid date time in this format(dd/MM/yyyy HH:mm): ");
            dateTime = s.nextLine();
        }
        return LocalDateTime.parse(dateTime, MyDateTime.saveFormat);
    }

    public boolean isValidDateTime(String dateTime) {
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
