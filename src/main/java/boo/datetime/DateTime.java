package boo.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

/**
 * This class provides methods to process dates given by the user, whether it is included with time or not.
 */
public abstract class DateTime {
    /**
     * Returns a {@code Temporal} that encapsulates date and or time information.
     *
     * @param rawDateString The raw String that contains date and or time information.
     * @return the {@code Temporal} with the date and or time information.
     * @throws DateTimeParseException if the raw String is not of the correct date format
     *                                as requested in the command list of the bot.
     */
    public static Temporal getDateTimeObject(String rawDateString)
            throws DateTimeParseException {
        //Possible formats, with and without time
        String timePatternOne = "yyyy-MM-dd HH:mm";
        String timePatternTwo = "yyyy-MM-dd";
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern(timePatternOne);
        DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern(timePatternTwo);

        //Determine which format
        boolean hasTime = (rawDateString.length() > timePatternTwo.length());
        DateTimeFormatter formatterToUse = (hasTime)
                ? formatterWithTime
                : formatterWithoutTime;
        if (hasTime) {
            //A date with time
            return LocalDateTime.parse(rawDateString, formatterToUse);
        } else {
            //A date without time
            return LocalDate.parse(rawDateString, formatterToUse);
        }
    }

    /**
     * Determines if two dates specify a valid duration.
     *
     * @param start The {@code Temporal} encapsulating the start date and time.
     * @param end The {@code Temporal} encapsulating the end date and time.
     * @return true if start happens before or is equal to end, else false.
     */
    public static boolean isValidDuration(Temporal start, Temporal end) {
        assert start != null : "Invalid start temporal.";
        assert end != null : "Invalid end temporal.";


        if (start instanceof LocalDateTime && end instanceof LocalDateTime) {
            boolean isEndAfterStart = ((LocalDateTime) end).isAfter((LocalDateTime) start);
            boolean isEndEqualToStart = end.equals(start);
            return isEndAfterStart || isEndEqualToStart;

        } else if (start instanceof LocalDate && end instanceof LocalDate) {
            boolean isEndAfterStart = ((LocalDate) end).isAfter((LocalDate) start);
            boolean isEndEqualToStart = end.equals(start);
            return isEndAfterStart || isEndEqualToStart;

        } else if (start instanceof LocalDate && end instanceof LocalDateTime) {
            //Convert
            int yearOfEndDate = ((LocalDateTime) end).getYear();
            int monthOfEndDate = ((LocalDateTime) end).getMonthValue();
            int dayOfEndDate = ((LocalDateTime) end).getDayOfMonth();
            LocalDate endDateOnly = LocalDate.of(yearOfEndDate, monthOfEndDate, dayOfEndDate);
            //Check validity
            boolean isEndAfterStart = endDateOnly.isAfter((LocalDate) start);
            boolean isEndEqualToStart = (endDateOnly.equals((LocalDate) start));
            return isEndAfterStart || isEndEqualToStart;

        } else if (start instanceof LocalDateTime && end instanceof LocalDate) {
            //Convert
            int yearOfStartDate = ((LocalDateTime) start).getYear();
            int monthOfStartDate = ((LocalDateTime) start).getMonthValue();
            int dayOfStartDate = ((LocalDateTime) start).getDayOfMonth();
            LocalDate startDateOnly = LocalDate.of(yearOfStartDate, monthOfStartDate, dayOfStartDate);
            //Check validity
            boolean isEndAfterStart = ((LocalDate) end).isAfter(startDateOnly);
            boolean isEndEqualToStart = ((LocalDate) end).equals(startDateOnly);
            return isEndAfterStart || isEndEqualToStart;
        }
        return true;
    }

    /**
     * Determines if one date is equal to another date, based on year, month and day.
     *
     * @param start The Temporal encapsulating the start date and time.
     * @param end The Temporal encapsulating the end date and time.
     * @return true if both refer to the same day, else false.
     */
    public static boolean isEqualDate(Temporal start, Temporal end) {
        assert start != null : "Start is an invalid temporal.";
        assert end != null : "End is an invalid temporal.";

        if (start instanceof LocalDateTime && end instanceof LocalDateTime) {
            return end.equals(start);
        } else if (start instanceof LocalDate && end instanceof LocalDate) {
            return (end).equals(start);
        } else if (start instanceof LocalDate && end instanceof LocalDateTime) {
            int yearOfEndDate = ((LocalDateTime) end).getYear();
            int monthOfEndDate = ((LocalDateTime) end).getMonthValue();
            int dayOfEndDate = ((LocalDateTime) end).getDayOfMonth();
            LocalDate endDateOnly = LocalDate.of(yearOfEndDate, monthOfEndDate, dayOfEndDate);
            return endDateOnly.equals(start);
        } else if (start instanceof LocalDateTime && end instanceof LocalDate) {
            int yearOfStartDate = ((LocalDateTime) start).getYear();
            int monthOfStartDate = ((LocalDateTime) start).getMonthValue();
            int dayOfStartDate = ((LocalDateTime) start).getDayOfMonth();
            LocalDate startDateOnly = LocalDate.of(yearOfStartDate, monthOfStartDate, dayOfStartDate);
            return end.equals(startDateOnly);
        }
        return true;
    }

    /**
     * Determines if one date falls between a given period, inclusive.
     * @param start The start date of the period.
     * @param end The end date of the period.
     * @param dateToCheck The date to check
     * @return true if the date falls between a given period, inclusive, else returns false.
     */
    public static boolean fallWithinPeriod(LocalDateTime start, LocalDateTime end, Temporal dateToCheck) {
        if (dateToCheck instanceof LocalDateTime) {
            boolean isAfterOrEqualToStart = start.isBefore((LocalDateTime) dateToCheck)
                    || start.isEqual((LocalDateTime) dateToCheck);
            boolean isBeforeOrEqualToEnd = end.isAfter((LocalDateTime) dateToCheck)
                    || end.isEqual((LocalDateTime) dateToCheck);
            return isAfterOrEqualToStart && isBeforeOrEqualToEnd;

        } else if (dateToCheck instanceof LocalDate) {
            //Compare date only

            int yearOfStartDate = start.getYear();
            int monthOfStartDate = start.getMonthValue();
            int dayOfStartDate = start.getDayOfMonth();
            LocalDate startDateOnly = LocalDate.of(yearOfStartDate, monthOfStartDate, dayOfStartDate);

            int yearOfEndDate = end.getYear();
            int monthOfEndDate = end.getMonthValue();
            int dayOfEndDate = end.getDayOfMonth();
            LocalDate endDateOnly = LocalDate.of(yearOfEndDate, monthOfEndDate, dayOfEndDate);

            boolean isAfterOrEqualToStart = startDateOnly.isBefore((LocalDate) dateToCheck)
                    || startDateOnly.isEqual((LocalDate) dateToCheck);
            boolean isBeforeOrEqualToEnd = endDateOnly.isAfter((LocalDate) dateToCheck)
                    || endDateOnly.isEqual((LocalDate) dateToCheck);
            return isAfterOrEqualToStart && isBeforeOrEqualToEnd;
        }
        return true;
    }


    /**
     * Determines if start happens strictly before end.
     *
     * @param start The {@code Temporal} encapsulating the start date and time.
     * @param end The {@code Temporal} encapsulating the end date and time.
     * @return true if start happens strictly before end, else false.
     */
    public static boolean isStrictlyBefore(Temporal start, Temporal end) {
        assert start != null : "Invalid start temporal.";
        assert end != null : "Invalid end temporal.";


        if (start instanceof LocalDateTime && end instanceof LocalDateTime) {
            boolean isEndAfterStart = ((LocalDateTime) end).isAfter((LocalDateTime) start);
            return isEndAfterStart;

        } else if (start instanceof LocalDate && end instanceof LocalDate) {
            boolean isEndAfterStart = ((LocalDate) end).isAfter((LocalDate) start);
            return isEndAfterStart;

        } else if (start instanceof LocalDate && end instanceof LocalDateTime) {
            //Convert
            int yearOfEndDate = ((LocalDateTime) end).getYear();
            int monthOfEndDate = ((LocalDateTime) end).getMonthValue();
            int dayOfEndDate = ((LocalDateTime) end).getDayOfMonth();
            LocalDate endDateOnly = LocalDate.of(yearOfEndDate, monthOfEndDate, dayOfEndDate);
            //Check validity
            boolean isEndAfterStart = endDateOnly.isAfter((LocalDate) start);
            return isEndAfterStart;

        } else if (start instanceof LocalDateTime && end instanceof LocalDate) {
            //Convert
            int yearOfStartDate = ((LocalDateTime) start).getYear();
            int monthOfStartDate = ((LocalDateTime) start).getMonthValue();
            int dayOfStartDate = ((LocalDateTime) start).getDayOfMonth();
            LocalDate startDateOnly = LocalDate.of(yearOfStartDate, monthOfStartDate, dayOfStartDate);
            //Check validity
            boolean isEndAfterStart = ((LocalDate) end).isAfter(startDateOnly);
            return isEndAfterStart;
        }
        return true;
    }




    /**
     * Formats a date, returning a string in either yyyy-MM-dd HH:mm or yyyy-MM-dd format.
     *
     * @return a string representing the formatted deadline.
     */
    public static String formatDate(Temporal date) {
        assert date != null : "Invalid temporal.";
        if (date instanceof LocalDateTime) {
            //Case 1: Got date and time
            LocalDateTime dateTimeObject = (LocalDateTime) date;
            String monthString = dateTimeObject.getMonth().toString().charAt(0)
                    + dateTimeObject.getMonth().toString().substring(1).toLowerCase();
            String dayString = (dateTimeObject.getDayOfMonth() < 10)
                    ? "0" + dateTimeObject.getDayOfMonth()
                    : Integer.toString(dateTimeObject.getDayOfMonth());
            String hourString = (dateTimeObject.getHour() < 10)
                    ? "0" + dateTimeObject.getHour()
                    : Integer.toString(dateTimeObject.getHour());
            String minuteString = (dateTimeObject.getMinute() < 10)
                    ? "0" + dateTimeObject.getMinute()
                    : Integer.toString(dateTimeObject.getMinute());

            return monthString + " " + dayString + " " + dateTimeObject.getYear() + " " + hourString + ":"
                    + minuteString;
        } else {
            //Case 2: Got date only
            LocalDate dateObject = (LocalDate) date;
            String monthString = dateObject.getMonth().toString().charAt(0)
                    + dateObject.getMonth().toString().substring(1).toLowerCase();
            String dayString = (dateObject.getDayOfMonth() < 10)
                    ? "0" + dateObject.getDayOfMonth()
                    : Integer.toString(dateObject.getDayOfMonth());

            return monthString + " " + dayString + " " + dateObject.getYear();
        }
    }
}
