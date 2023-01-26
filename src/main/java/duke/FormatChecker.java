package duke;

public class FormatChecker {
    public static boolean isCorrectDeadlineCmd(String command) {
        if (command.matches("^.+(\\s)/by(\\s).+$")) {
            return true;
        }
        return false;
    }

    public static boolean isCorrectEventCmd(String command) {
        if (command.matches("^.+(\\s)/from(\\s).+(\\s)/to.*$")) {
            return true;
        }
        return false;
    }

    public static boolean isCorrectDateInput(String dateString) {
        if (dateString.matches("^(\\d)+/(\\d)+/(\\d)+$")) {
            return true;
        }
        return false;
    }
}
