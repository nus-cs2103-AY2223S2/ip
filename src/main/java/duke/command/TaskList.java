package duke.command;

import duke.exceptions.DukeException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDo;
import duke.utilities.Parser;
import java.util.ArrayList;

/**
 * The type Task list.
 */
public class TaskList {
  /**
   * The List of tasks.
   */
  ArrayList<Task> tasks;
  /**
   * The Storage.
   */
  Storage storage;

  /**
   * Instantiates a new Task list.
   *
   * @param path the path
   */
  public TaskList(String path) {
    tasks = new ArrayList<>();
    storage = new Storage(path);
    init();
  }

  /**
   * Gui add string.
   *
   * @param input the input
   * @return the string
   */
  public String add(Task input) {
    try {
      String str;
      if (input.taskName.equals("") || input.taskName.isBlank()) {
        throw new DukeException("OOPS!!! "
                + "The description of a todo cannot be empty.\n");
      } else {
        if (input instanceof ToDo) {
          storage.write("T|" + input.done + "|" + ((ToDo) input).rawInput);
        } else if (input instanceof Deadlines) {
          if (((Deadlines) input).endDate == null) {
            throw new DukeException("OOPS!!! "
                    + "The date of a deadline cannot be empty.\n");
          }
          storage.write("D|" + input.done + "|" + ((Deadlines) input).rawInput);
        } else if (input instanceof Events) {
          if (((Events) input).start == null || ((Events) input).end == null) {
            throw new DukeException("OOPS!!! "
                    + "The time of event cannot be empty.\n");
          }
          storage.write("E|" + input.done + "|" + ((Events) input).rawInput);
        } else {
          System.out.println("Unspecific type");
        }
        tasks.add(input);
        input.add();
        str = input.messageAdd + "\n Now you have "
                + tasks.size() + " tasks in the list";
      }
      return str;
    } catch (DukeException e) {
      if (input instanceof ToDo) {
        return "OOPS!!! The description of a todo cannot be empty.\n";
      } else if (input instanceof Deadlines) {
        return "OOPS!!! The description of a deadline cannot be empty.\n";
      } else {
        return "OOPS!!! The description of a event cannot be empty.\n";
      }
    }
  }

  /**
   * Gui display all string.
   *
   * @return the string
   */
  public String displayAll() {
    StringBuilder str = new StringBuilder(Parser.THE_TASKS_IN_YOUR_LIST);
    for (int x = 0; x < tasks.size(); x++) {
      tasks.get(x).display();
      str.append(x + 1).append(". ").append(
              tasks.get(x).messageDisplay).append("\n");
    }
    return str.toString();
  }

  /**
   * Gui mark string.
   *
   * @param index the index
   * @return the string
   */
  public String mark(int index) {
    try {
      String str;
      Task temp = tasks.get(index);
      temp.marked();
      str = temp.messageMarked;
      storage.markAt(index);
      return str;
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Invalid Index");
    }
    return null;
  }

  /**
   * Gui unmark string.
   *
   * @param index the index
   * @return the string
   */
  public String unmark(int index) {
    try {
      String str;
      Task temp = tasks.get(index);
      temp.unmarked();
      str = temp.messageUnmarked;
      storage.unmarkAt(index);
      return str;
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Invalid Index");
    }
    return null;
  }

  /**
   * Delete.
   * delete task at given index
   *
   * @param index the index
   * @return the string
   */
  public String delete(int index) {
    try {
      String str;
      Task temp = tasks.get(index);
      temp.delete();
      tasks.remove(index);
      str = temp.messageDelete
              + "\n Now you have " + tasks.size() + " tasks in the list";
      storage.deteleAt(index);
      return str;
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Invalid Index");
    }
    return null;
  }

  /**
   * Init.
   * Init and Load in Tasks from Txt file
   */
  void init() {
    storage.read();
    if (!storage.records.isEmpty()) {
      for (int x = 0; x < storage.records.size(); x++) {
        String[] tokens = storage.records.get(x).split("\\|");
        boolean status = Boolean.parseBoolean(tokens[1]);
        if (tokens[0].equals("T")) {
          tasks.add(new ToDo(tokens[2], status));
        } else if (tokens[0].equals("D")) {
          tasks.add(new Deadlines(tokens[2], status));
        } else {
          tasks.add(new Events(tokens[2], status));
        }
      }
    }
  }

  /**
   * File write all.
   */
  public void fileWriteAll() {
    storage.writeAll();
  }

  /**
   * Gui find string.
   *
   * @param key the key
   * @return the string
   */
  public String find(String key) {
    int[] indexes = new int[tasks.size()];
    int count = 0;
    StringBuilder output = new StringBuilder();
    for (int x = 0; x < tasks.size(); x++) {
      if (tasks.get(x).taskName.contains(key)) {
        indexes[count] = x;
        count++;
      }
    }
    for (int x = 0; x < count; x++) {
      tasks.get(indexes[x]).display();
      output.append(x + 1).append(".").append(
              tasks.get(indexes[x]).messageDisplay).append("\n");
    }
    return Parser.FIND_MESSAGE + output;
  }


}
