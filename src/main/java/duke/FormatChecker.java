package duke;

/**
 * A class that contains methods that check whether a string follows different formats used in Duke.
 */
public class FormatChecker {

    /**
     * Returns true if the string follows the correct format of deadline command.
     * @param command the command to be checked
     * @return whether the command has the correct deadline command format
     */
    public static boolean isCorrectDeadlineCmd(String command) {
        return command.matches("^.+(\\s)/by(\\s).+$");
    }

    /**
     * Returns true if the string follows the correct format of event command.
     * @param command the command to be checked
     * @return whether the command has the correct event command format
     */
    public static boolean isCorrectEventCmd(String command) {
        return command.matches("^.+(\\s)/from(\\s).+(\\s)/to.*$");
    }

    /**
     * Returns true if the string follows the correct format of date representation.
     * @param dateString the date given in string to be checked
     * @return whether the command has the correct date format
     */
    public static boolean isCorrectDateInput(String dateString) {
        return dateString.matches("^(\\d)+/(\\d)+/(\\d)+(\\s)(\\d)+:(\\d)+$");
    }
}
