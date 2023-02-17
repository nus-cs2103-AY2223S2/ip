package duke;

/**
 * Deals with making sense of the user command
 */
public class Parser {

    /**
     * Reads the user input
     *
     * @param userInput the string input
     * @return the string array
     */
    public String[] parseUserInput(String userInput) {
        return userInput.split(" ", 2);
    }
}
