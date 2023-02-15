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

    /**
     * Formats trivia messages.
     * @param fact The cool trivia fact.
     * @return The correctly formatted cool trivia fact.
     */
    public String formatTrivia(String fact) {
        return "[DID YOU KNOW?]\n" + cutFact(fact) + ".";
    }

    /**
     * Adds a newline characters to cut the cool trivia fact. (if too long)
     * @param fact The cool trivia fact.
     * @return The correctly cut cool trivia fact.
     */
    public String cutFact(String fact) {
        //@@author Tempura-Person-reused
        //Reused from https://stackoverflow.com/questions/10530102/
        // with minor modifications
        return fact.replaceAll("(.{80})", "$1\n");
        //@@author
    }
}
