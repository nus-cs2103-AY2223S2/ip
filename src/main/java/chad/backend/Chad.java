package chad.backend;

import java.io.IOException;

/**
 * Class encapsulating Chad.
 */
public class Chad {
    private final TaskList tasklist;
    private final Parser parser;

    /**
     * Constructor for Chad.
     */
    public Chad() {
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
     * @return Output from Chad.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }
}
