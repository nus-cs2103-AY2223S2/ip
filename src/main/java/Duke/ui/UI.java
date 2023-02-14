package Duke.ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import Duke.Command.Command;
import Duke.Command.Parser;
import Duke.Exceptions.DukeException;

/**
 * Class deals with interactions with the user.
 */
public class UI {
    private Parser parser;

    public UI(Parser parser) {
        this.parser = parser;
    }

    /**
   * method returns a String that responds to user's input.
   *
   * @param input receives the string typed in by the user.
   */
    public String respondInput(String input) {
        String received = input.trim();
        String commandType = received.split(" ")[0];
        try {
            Command command = Command.searchCommand(commandType);
            return parser.execute(received, command);
        } catch (DukeException | IOException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            return "oops! incorrect time format!"
                + "\nshould be yyyy-mm-dd HHmm";
        }
    }
}
