package parser;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
/**
 * This class parses LocalDateTime format into String 
 */
public class ParsedDate {
    
    private LocalDateTime ldt;

    public ParsedDate(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public boolean isEqualDate(LocalDate date) {
        return this.ldt.toLocalDate().isEqual(date);
    }

    public boolean isBeforeDate(LocalDate date) {
        return this.ldt.toLocalDate().isBefore(date);
    }

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
        return month.toString().substring(0,3) 
                + " " + date + " " + year 
                + ", " + day.toString().substring(0,3)
                + ", " + df.format(hour%12) 
                + ":" + df.format(minute) + ampm;
    }
}