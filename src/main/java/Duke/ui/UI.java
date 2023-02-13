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
      return parser.execute(received,command);
    } catch (DukeException | IOException e) {
      return e.toString();
    }
  }
}
