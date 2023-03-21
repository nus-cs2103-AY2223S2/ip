package duke.helper;

import java.time.LocalDate;

/**
 * Helper Class that contains functions to parse dates.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class DateTimeParser {


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


        String newTime = year + "-" + month + "-" + day;

        return LocalDate.parse(newTime);

    }
}
