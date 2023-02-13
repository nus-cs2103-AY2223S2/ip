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

        switch (month) {
        case "JAN":
            return convertToJan(s);

        case "FEB":
            return convertToFeb(s);

        case "MAR":
            return convertToMar(s);

        case "APR":
            return convertToApr(s);

        case "MAY":
            return convertToMay(s);

        case "JUN":
            return convertToJun(s);

        case "JUL":
            return convertToJul(s);

        case "AUG":
            return convertToAug(s);

        case "SEP":
            return convertToSep(s);

        case "OCT":
            return convertToOct(s);

        case "NOV":
            return convertToNov(s);

        case "DEC":
            return convertToDec(s);

        default:
            return impossibleFunction();
        }
    }

    private static String convertToJan(String s) {
        String date = s.substring(8, 10);
        String year = s.substring(12, 16);
        return year + "-01-" + date;
    }

    private static String convertToFeb(String s) {
        String date = s.substring(9, 11);
        String year = s.substring(13, 17);
        return year + "-02-" + date;
    }

    private static String convertToMar(String s) {
        String date = s.substring(6, 8);
        String year = s.substring(10, 14);
        return year + "-03-" + date;
    }

    private static String convertToApr(String s) {
        String date = s.substring(6, 8);
        String year = s.substring(10, 14);
        return year + "-04-" + date;
    }

    private static String convertToMay(String s) {
        String date = s.substring(4, 6);
        String year = s.substring(8, 12);
        return year + "-05-" + date;
    }

    private static String convertToJun(String s) {
        String date = s.substring(5, 7);
        String year = s.substring(9, 13);
        return year + "-06-" + date;
    }

    private static String convertToJul(String s) {
        String date = s.substring(5, 7);
        String year = s.substring(9, 13);
        return year + "-07-" + date;
    }

    private static String convertToAug(String s) {
        String date = s.substring(7, 9);
        String year = s.substring(11, 15);
        return year + "-08-" + date;
    }

    private static String convertToSep(String s) {
        String date = s.substring(10, 12);
        String year = s.substring(14, 18);
        return year + "-09-" + date;
    }

    private static String convertToOct(String s) {
        String date = s.substring(8, 10);
        String year = s.substring(12, 16);
        return year + "-10-" + date;
    }

    private static String convertToNov(String s) {
        String date = s.substring(9, 11);
        String year = s.substring(13, 17);
        return year + "-11-" + date;
    }

    private static String convertToDec(String s) {
        String date = s.substring(9, 11);
        String year = s.substring(13, 17);
        return year + "-12-" + date;
    }

    private static String impossibleFunction() {
        assert true : "Invalid date received";
        return "0000-00-00";
    }
}
