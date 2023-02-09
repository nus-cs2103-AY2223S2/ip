package ui;

import Command.Command;
import Command.Parser;
import Exceptions.DontKnow;
import Exceptions.DukeException;
import java.io.IOException;

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
