import features.DukeException;
import features.Parser;

/**
 * The project.
 */
public class Duke {
    /**
     * Runs the Duke process.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            return parser.parse(input);
        } catch (DukeException ex) {
            return (ex.printErrorMessage());
        }
    }

}

