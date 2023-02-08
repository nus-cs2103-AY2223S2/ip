package support;

/**
 * Converts an output format string into an input format string.
 */

public class DateConverter {

    /**
     * Takes a string from output format and converts it into the accepted input format
     * @param s output format string
     * @return input format string
     */
    public static String dateFormatter(String s) {
        assert s.length() >= 3 : "Invalid date received";

        String month = s.substring(0, 3);
        String date;
        String year;
        switch (month) {
        case "JAN":
            date = s.substring(8, 10);
            year = s.substring(12, 16);
            return year + "-01-" + date;

        case "FEB":
            date = s.substring(9, 11);
            year = s.substring(13, 17);
            return year + "-02-" + date;

        case "MAR":
            date = s.substring(6, 8);
            year = s.substring(10, 14);
            return year + "-03-" + date;

        case "APR":
            date = s.substring(6, 8);
            year = s.substring(10, 14);
            return year + "-04-" + date;

        case "MAY":
            date = s.substring(4, 6);
            year = s.substring(8, 12);
            return year + "-05-" + date;

        case "JUN":
            date = s.substring(5, 7);
            year = s.substring(9, 13);
            return year + "-06-" + date;

        case "JUL":
            date = s.substring(5, 7);
            year = s.substring(9, 13);
            return year + "-07-" + date;

        case "AUG":
            date = s.substring(7, 9);
            year = s.substring(11, 15);
            return year + "-08-" + date;

        case "SEP":
            date = s.substring(10, 12);
            year = s.substring(14, 18);
            return year + "-09-" + date;

        case "OCT":
            date = s.substring(8, 10);
            year = s.substring(12, 16);
            return year + "-10-" + date;

        case "NOV":
            date = s.substring(9, 11);
            year = s.substring(13, 17);
            return year + "-11-" + date;

        case "DEC":
            date = s.substring(9, 11);
            year = s.substring(13, 17);
            return year + "-12-" + date;

        default:
            assert true : "Invalid date received";
            return "0000-00-00";
        }
    }
}
