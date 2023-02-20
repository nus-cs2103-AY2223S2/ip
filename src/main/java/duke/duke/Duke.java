package duke.duke;

/**
 * The main driver class to run the Duke program
 */
public class Duke {
    private TaskList data = Storage.populateList();
    private Parser parser;

    public Duke() {
        this.parser = new Parser(data);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }
}
