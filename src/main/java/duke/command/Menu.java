package duke.command;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDo;
import duke.utilities.Parser;
import java.util.Locale;

/**
 * The type Menu.
 */
public class Menu {
  /**
   * The Manager.
   */
  static TaskList manager = new TaskList("/Users/s.f/ip/src/Data/duke.txt");

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
        manager.fileWriteAll();
        return Parser.BYE_MESSAGE;

      case "list":
        return manager.displayAll() + "\n";

      case "mark":
        return manager.mark(Integer.parseInt(tokens[1]) - 1);

      case "unmark":
        return manager.unmark(Integer.parseInt(tokens[1]) - 1);

      case "todo":
        ToDo todo = new ToDo(withoutKey, false);
        return manager.add(todo);

      case "deadline":
        Deadlines deadlines = new Deadlines(withoutKey, false);
        return manager.add(deadlines);


      case "event":
        Events events = new Events(withoutKey, false);
        return manager.add(events);


      case "delete":
        return manager.delete(Integer.parseInt(tokens[1]) - 1);

      case "find":
        return manager.find(withoutKey);
      default:
        return Parser.WRONG_INPUT;
    }
  }
}
