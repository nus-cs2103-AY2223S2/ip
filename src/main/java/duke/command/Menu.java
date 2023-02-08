package duke.command;

import duke.utilities.Parser;

import java.util.Locale;

/**
 * The type Menu.
 */
public class Menu {
  /**
   * The Manager.
   */
  private static final TaskList MANAGER =
          new TaskList("/Users/s.f/ip/src/Data/duke.txt");

  /**
   * In out string.
   *
   * @param input the input
   * @return the string
   */
  public static String inOut(String input) {
    String[] tokens = input.split(" ");
    String withoutKey = input.replace(tokens[0], "");
    switch (tokens[0].toLowerCase(Locale.ROOT)) {
      case "bye":
        MANAGER.fileWriteAll();
        return Parser.BYE_MESSAGE;

      case "list":
        return MANAGER.displayAll() + "\n";

      case "mark":
        return MANAGER.opsMark(tokens);

      case "unmark":
        return MANAGER.opsUnmark(tokens);

      case "todo":
        return MANAGER.opsAddTodo(withoutKey);

      case "deadline":
        return MANAGER.opsAddDeadline(withoutKey);

      case "event":
        return MANAGER.opsAddEvent(withoutKey);

      case "delete":
        return MANAGER.opsDelete(tokens, input);

      case "find":
        return MANAGER.find(withoutKey);

      default:
        return Parser.WRONG_INPUT;
    }
  }
}
