package duke.backend;

import java.io.IOException;

/**
 * Class encapsulating Duke.
 */
public class Duke {
    private TaskList tasklist;
    private Parser parser;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        try {
            this.tasklist = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.parser = new Parser(this.tasklist);
    }

    /**
     * Get Duke's response for a given input.
     * @param input Input taken from the user.
     * @return Output from Duke.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }
}
