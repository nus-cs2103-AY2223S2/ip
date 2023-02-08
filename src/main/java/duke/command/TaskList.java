package duke.command;

import duke.exceptions.DukeException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDo;
import duke.utilities.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

  private static <T, U> List<U> stringListToIntList(List<T> listOfString,
                                                    Function<T, U> function) {
    return listOfString.stream()
            .map(function)
            .collect(Collectors.toList());
  }

  /**
   * Gui add string.
   *
   * @param input the input
   * @return the string
   */
  private String add(Task input) {
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
  private String mark(int index) {
    try {

      String str;
      Task temp = tasks.get(index);
      if (!temp.done) {
        temp.marked();
        str = temp.messageMarked;
        storage.markAt(index);
      } else {
        str = index + 1 + ") " + temp.taskName
                + ": " + Parser.MARKED_DUPLICATE_TASKS;
      }
      return str;
    } catch (IndexOutOfBoundsException e) {
      return (index + 1) + " is Invalid Index Buddy\n";
    }
  }

  /**
   * Gui unmark string.
   *
   * @param index the index
   * @return the string
   */
  private String unmark(int index) {
    try {
      String str;
      Task temp = tasks.get(index);
      if (temp.done) {
        temp.unmarked();
        str = temp.messageUnmarked;
        storage.unmarkAt(index);
      } else {
        str = Parser.UNMARKED_DUPLICATE_TASKS;
      }
      return str;
    } catch (IndexOutOfBoundsException e) {
      return (index + 1) + " is Invalid Index Buddy\n";
    }
  }

  /**
   * Delete.
   * delete task at given index
   *
   * @param index the index
   * @return the string
   */
  private String delete(int index) {
    try {
      String str;
      Task temp = tasks.get(index);
      temp.delete();
      tasks.remove(index);
      str = temp.messageDelete
              + "\n Now you have " + tasks.size() + " tasks in the list\n";
      storage.deteleAt(index);
      return str;
    } catch (IndexOutOfBoundsException e) {
      return "Invalid Index buddy\n";
    }
  }

  /**
   * Init.
   * Init and Load in Tasks from Txt file
   */
  private void init() {
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

  /**
   * Mark list string x to y.
   *
   * @param start the start
   * @param end   the end
   * @return the string
   */
  private String markList(int start, int end) {
    StringBuilder str = new StringBuilder();
    for (int x = start; x <= end; x++) {
      str.append(mark(x));
    }
    String next = str.toString();
    return next.contains(Parser.MARKED_THESE_TASKS_AS_DONE)
            ? str.toString().replace(Parser.MARKED_THIS_TASK_AS_DONE, "\n")
            + "\n" + Parser.MARKED_THESE_TASKS_AS_DONE
            : next;
  }

  /**
   * Mark multi string x y .. z .
   *
   * @param a the a
   * @return the string
   */
  private String markMulti(String[] a) {
    StringBuilder str = new StringBuilder();
    if (a.length - 1 > tasks.size()) {
      str.append("Out of range Buddy \n");
    } else {
      for (int x = 1; x < a.length; x++) {
        str.append(mark(Integer.parseInt(a[x]) - 1));
      }
    }

    return str.toString();
  }

  /**
   * Unmark list string x to y.
   *
   * @param start the start
   * @param end   the end
   * @return the string
   */
  private String unmarkList(int start, int end) {
    StringBuilder str = new StringBuilder();
    for (int x = start; x <= end; x++) {
      str.append(unmark(x));
    }
    String next = str.toString();
    return next.contains(Parser.MARKED_THESE_TASKS_AS_DONE)
            ? str.toString().replace(Parser.MARKED_THIS_TASK_AS_DONE, "\n")
            + "\n" + Parser.MARKED_THESE_TASKS_AS_DONE
            : next;
  }

  /**
   * Unmark multi string x y .. z.
   *
   * @param a the a
   * @return the string
   */
  private String unmarkMulti(String[] a) {
    StringBuilder str = new StringBuilder();
    if (a.length - 1 > tasks.size()) {
      str.append("Out of range Buddy \n");
    } else {
      for (int x = 1; x < a.length; x++) {
        str.append(unmark(Integer.parseInt(a[x]) - 1));
      }
    }

    return str.toString();
  }

  /**
   * Delete list string x to y.
   *
   * @param start the start
   * @param end   the end
   * @return the string
   */
  private String deleteList(int start, int end) {
    StringBuilder str = new StringBuilder();
    for (int x = end; x >= start; x--) {
      str.append(delete(x));
    }
    return str.toString();
  }

  /**
   * Delete multi string x y ..z.
   * Convert String to List, then convert to list of integer,
   * sort in decreasing order, delete from behind
   * used private stream method for conversion
   *
   * @param a the a
   * @return the string
   */
  private String deleteMulti(String[] a) {

    List<String> strs = Arrays.asList(a);
    List<Integer> integers =
            stringListToIntList(strs, Integer::parseInt);
    integers.sort(Collections.reverseOrder());

    StringBuilder str = new StringBuilder();
    for (int x = 0; x < integers.size() - 1; x++) {
      str.append(delete(integers.get(x) - 1));
    }
    return str.toString();
  }

  /**
   * Ops mark string.
   *
   * @param tokens the tokens
   * @return the string
   */
  public String opsMark(String[] tokens) {
    if (tokens.length == 2) {
      //mark 1-3
      if (tokens[1].contains("-")) {
        String[] indexes = tokens[1].split("-");
        return markList(Integer.parseInt(indexes[0]) - 1,
                Integer.parseInt(indexes[1]) - 1);
      } else {
        return mark(Integer.parseInt(tokens[1]) - 1);
      }
    } else {
      return markMulti(tokens);
    }
  }

  /**
   * Ops unmark string.
   *
   * @param tokens the tokens
   * @return the string
   */
  public String opsUnmark(String[] tokens) {
    //unmark 1-3 or mark 1
    if (tokens.length == 2) {
      //unmark 1-3
      if (tokens[1].contains("-")) {
        String[] indexes = tokens[1].split("-");
        return unmarkList(Integer.parseInt(indexes[0]) - 1,
                Integer.parseInt(indexes[1]) - 1);
      } else {
        return unmark(Integer.parseInt(tokens[1]) - 1);
      }
    } else {
      return unmarkMulti(tokens);
    }
  }

  /**
   * Ops delete string.
   *
   * @param tokens the tokens
   * @param input  the input
   * @return the string
   */
  public String opsDelete(String[] tokens, String input) {
    if (tokens.length == 2) {
      //delete 1-3
      if (tokens[1].contains("-")) {
        String[] indexes = tokens[1].split("-");
        return deleteList(Integer.parseInt(indexes[0]) - 1,
                Integer.parseInt(indexes[1]) - 1);
      } else {
        return delete(Integer.parseInt(tokens[1]) - 1);
      }
    } else {
      String[] filtered = input.replace("delete", "-1").split(" ");
      return deleteMulti(filtered);
    }
  }

  /**
   * Ops add todo string.
   *
   * @param name the name
   * @return the string
   */
  public String opsAddTodo(String name) {
    ToDo todo = new ToDo(name, false);
    return add(todo);
  }

  /**
   * Ops add deadline string.
   *
   * @param name the name
   * @return the string
   */
  public String opsAddDeadline(String name) {
    Deadlines deadlines = new Deadlines(name, false);
    return add(deadlines);
  }

  /**
   * Ops add event string.
   *
   * @param name the name
   * @return the string
   */
  public String opsAddEvent(String name) {
    Events events = new Events(name, false);
    return add(events);
  }
}
