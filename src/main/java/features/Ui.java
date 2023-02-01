package features;

/**
 * Formats and prints UI elements.
 */
public class Ui {
    /**
     * Formats logic related error messages.
     * @param message The message to format.
     * @return The correctly formatted message.
     */
    public String formatLogicError(String message) {
        return "[ERROR]\nUh, " + message;
    }

    /**
     * Formats command format related error messages.
     * @param command The name of the command.
     * @param format The correct command format.
     * @return The correctly formatted message.
     */
    public String formatCommandError(String command, String format) {
        return "[ERROR]\nUh, " + command + " command format is used wrongly."
                + "\nCorrect format is as follows:\n" + "[ " + format + " ]";
    }
}
