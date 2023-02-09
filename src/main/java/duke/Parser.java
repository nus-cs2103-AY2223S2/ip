package duke;

/**
 * This class handles the parsing of user input by breaking input strings into tokens.
 */
public class Parser {

    /**
     * Reads the next line, and breaks the result into tokens.
     * @return A <code>String[]</code> containing strings from input, with each token delimited in the input
     * by a whitespace.
     */
    String[] parseUserInput(String input) {
        return input.split(" ");
    }
}
