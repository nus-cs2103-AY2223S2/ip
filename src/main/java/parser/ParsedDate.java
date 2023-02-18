package parser;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * This class is Duke's Date Time Object
 */
public class ParsedDate {

    private LocalDateTime ldt;

    /**
     * Creates Duke's version of LocalDateTime
     *
     * @param ldt parsed LocalDateTime
     */
    public ParsedDate(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    /**
     * Checks if date input is the same as this object's stored date
     *
     * @param date date input
     * @return true if equal, false otherwise
     */
    public boolean isEqualDate(LocalDate date) {
        return this.ldt.toLocalDate().isEqual(date);
    }

    /**
     * Checks if this object's stored date comes before the date input
     *
     * @param date date input
     * @return true if this date is strictly before input date, false otherwise
     */
    public boolean isBeforeDate(LocalDate date) {
        return this.ldt.toLocalDate().isBefore(date);
    }

    /**
     * Checks if this object's stored date comes after the date input
     *
     * @param date date input
     * @return true if this date is strictly after input date, false otherwise
     */
    public boolean isAfterDate(LocalDate date) {
        return this.ldt.toLocalDate().isAfter(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        Month month = this.ldt.getMonth();
        int year = this.ldt.getYear();
        int date = this.ldt.getDayOfMonth();
        DayOfWeek day = this.ldt.getDayOfWeek();
        int hour = this.ldt.getHour();
        String ampm = "AM";
        if (hour >= 12) {
            ampm = "PM";
        }
        int minute = this.ldt.getMinute();
        DecimalFormat df = new DecimalFormat("00");
        return month.toString().substring(0, 3)
                + " " + date + " " + year
                + ", " + day.toString().substring(0, 3)
                + ", " + df.format(hour % 12)
                + ":" + df.format(minute) + ampm;
    }
}
