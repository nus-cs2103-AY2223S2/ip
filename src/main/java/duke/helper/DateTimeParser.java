package duke.helper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//remember to fix this shit and account for all the different ways that people can fuck up entering the date
//only can handle date in DD/MM/YYYY HHHH

/**
 * Helper Class that contains functions to parse dates.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class DateTimeParser {

    //static method to take a string and create a local date time object with it

    /**
     * Parses through a string of date.
     * Returns LocalDate object of the date.
     *
     * @param rawDate Input of date to be parsed.
     * @return LocalDate object encapsulating the date.
     */

    public static LocalDate parse(String rawDate) {


        String[] splitDate = rawDate.split("-");
        String year = splitDate[0];
        String month = splitDate[1];
        String day = splitDate[2];


        String newTime = year +"-" + month + "-" + day;

        try {
            return LocalDate.parse(newTime);
        } catch (DateTimeParseException exception) {
            System.out.println("wake up and key in the date and time properly");
            System.out.println(exception.getMessage());
        }

        return null;




        /**
        String[] splitDate = rawDate.split("/");
        String[] splitTime = splitDate[2].split(" ");

        //add a day out of bounds exception here
        int day = Integer.valueOf(splitDate[0]);
        int month = Integer.valueOf(splitDate[1]);
        int year = Integer.valueOf(splitTime[0]);
        int time = Integer.valueOf(splitTime[1]);
        int hour = (int) Math.round((Math.floor(time / 100)));
        int minute = time % 100;


        //date out of bounds
        if (day < 0 |  day > 31) {
            throw new duke.exceptions.DateOutOfBoundsException("bro wakeup this day got exist meh");
        } else if (month < 0 | month > 12) {
            throw new duke.exceptions.DateOutOfBoundsException("bro wakeup this month got exist meh");
        } else if (time < 0 | time > 2400) {
            throw new duke.exceptions.DateOutOfBoundsException("wakeup and move on its the next day already");
        } else {
            return LocalDateTime.of(year, month, day, hour, minute);
        }
         */
    }
}
