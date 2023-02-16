package duke;

/**
 * Class that parses the inputs given by the user
 */
public class Parser {
    public static String[] parseCommand(String userInput) {
        return userInput.split("\\s", 2);
    }
}
