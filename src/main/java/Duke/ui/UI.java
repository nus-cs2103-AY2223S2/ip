package Duke.ui;

import Duke.Command.Command;
import Duke.Command.Parser;
import Duke.Exceptions.DukeException;
import java.io.IOException;

/**
 * Class deals with interactions with the user.
 */
public class UI {
  public Parser parser;

  public UI(Parser parser) {
    this.parser = parser;
  }

  public String respondInput(String input) {
    String received = input.trim();
    String commandType = received.split(" ")[0];
    try {
      Command command = Command.searchCommand(commandType);
      return parser.execute(received,command);
    } catch (DukeException | IOException e) {
      return e.toString();
    }
  }
}
