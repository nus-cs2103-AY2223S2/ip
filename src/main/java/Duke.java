import features.DukeException;
import features.Parser;

/**
 * The project.
 */
public class Duke {
    // PLAN: all commands that affect taskList now affect and save it from a static context!!!!
    // PLAN: Parser class now takes nothing in constructor.
    // PLAN: Parser.parse(String parseInput) will return the response String.
    // PLAN: all commands will only use method String handle(String[] userInput) throws DukeException.
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

