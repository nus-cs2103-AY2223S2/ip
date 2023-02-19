package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Wrapper class that can store either a LocalDate object or
 * a LocalDateTime object but not both.
 */
public class Date {
    private LocalDate localDate;
    private LocalDateTime localDateTime;

    /**
     * Constructor for a Date object using a LocalDate.
     *
     * @param localDate LocalDate object
     */
    public Date(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Constructor for a Date object using a LocalDateTime.
     *
     * @param localDateTime LocalDateTime object
     */
    public Date(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * Returns whether Date object is storing a LocalDate object.
     *
     * @return True if instance is storing a LocalDate object and false otherwise
     */
    public boolean isDate() {
        return this.localDate != null;
    }

    /**
     * Returns whether Date object is storing a LocalDateTime object.
     *
     * @return True if instance is storing a LocalDateTime object and false otherwise.
     */
    public boolean isDateTime() {
        return !isDate();
    }

    /**
     * Returns a formatted date string according to what the instance is storing.
     * e.g. 2023-02-01 if it is storing a LocalDate object
     * e.g. 2023-02-01 17:00 if it is storing a LocalDateTime object.
     *
     * @return Formatted date string according to the above specifications
     */
    public String asFileDate() {
        return this.isDate()
                ? this.localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                : this.localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a String object that is formatted depending on what is being stored.
     * e.g. If it is storing a LocalDate object with value 2023-02-01, it will return Feb 1 2023
     * e.g. If it is storing a LocalDateTime object with value 2023-02-01 17:00, it will return Feb 1 2023 17:00
     *
     * @return A string representation of the Date object according to the above specifications.
     */
    @Override
    public String toString() {
        return this.isDate()
                ? this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : this.localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }
}
