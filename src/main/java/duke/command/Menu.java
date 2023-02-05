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
        //mark 1-3 or mark 1
        if(tokens.length == 2){
          //mark 1-3
          if(tokens[1].contains("-")){
            String[] indexes  = tokens[1].split("-");
            return manager.markList(Integer.parseInt(indexes[0]) - 1, Integer.parseInt(indexes[1]) - 1);
          } else {
            return manager.mark(Integer.parseInt(tokens[1]) - 1);
          }
        } else {
          return manager.markMulti(tokens);
        }

      case "unmark":
        //unmark 1-3 or mark 1
        if(tokens.length == 2){
          //unmark 1-3
          if(tokens[1].contains("-")){
            String[] indexes  = tokens[1].split("-");
            return manager.unmarkList(Integer.parseInt(indexes[0]) - 1, Integer.parseInt(indexes[1]) - 1);
          } else {
            return manager.unmark(Integer.parseInt(tokens[1]) - 1);
          }
        } else {
          return manager.unmarkMulti(tokens);
        }


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
        if(tokens.length == 2){
          //delete 1-3
          if(tokens[1].contains("-")){
            String[] indexes  = tokens[1].split("-");
            return manager.deleteList(Integer.parseInt(indexes[0]) - 1, Integer.parseInt(indexes[1]) - 1);
          } else {
            return manager.delete(Integer.parseInt(tokens[1])-1);
          }
        } else {
          String [] filtered = input.replace("delete","-1").split(" ");
          return manager.deleteMulti(filtered);
        }

      case "find":
        return manager.find(withoutKey);
      default:
        return Parser.WRONG_INPUT;
    }
  }
}
