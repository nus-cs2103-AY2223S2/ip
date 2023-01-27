package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MyDate {
    protected static DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern(("dd/MM/yyyy"));
    protected static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy MMM dd");

    protected LocalDate date;
    public MyDate(String date) {
        this.date = getValidDate(date);
    }

    public LocalDate getValidDate(String date) {
        Scanner s = new Scanner(System.in);

        while (!isValidDate(date)) {
            System.out.println("Hey come on, give me a valid date time in this format(dd/MM/yyyy): ");
            date = s.nextLine();
        }
        return LocalDate.parse(date, MyDate.saveFormat);
    }

    public boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, MyDate.saveFormat);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public String printDateTime() {
        return this.date.format((outputFormat));
    }

    public boolean isEqual(LocalDate current) {
        return this.date.isEqual(current);
    }

    public boolean isBetween(LocalDate f, LocalDate t) {
        return !this.date.isBefore(f) && !this.date.isAfter(t);
    }
}
