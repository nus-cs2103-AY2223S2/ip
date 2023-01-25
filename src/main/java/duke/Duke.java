import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import dukeexception.DukeException;
import dukeexception.EmptyTaskArgumentException;
import dukeexception.MarkIndexDoesNotExistException;
import dukeexception.UnmarkIndexDoesNotExistException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Duke {
  static ArrayList<Task> taskStorage = new ArrayList<Task>();

  enum Commands {
    LIST, UNMARK, MARK, TODO, DEADLINE, EVENT, DELETE
  };

  public static void main(String[] args) {
    greetings();
    handleRequest();
    bye();
  }

  public static void handleRequest() {
    Scanner in = new Scanner(System.in);

    while (true) {
      try {
        String input = getUserInput(in);
        String command = input.split(" ")[0].toLowerCase();

        if (command.equals("bye")) {
          break;
        }

        switch (command) {
          case "list":
            listTasks();
            break;
          case "unmark":
            unmarkTask(input);
            break;
          case "mark":
            markTask(input);
            break;
          case "todo":
            // Fallthrough
          case "deadline":
            // Fallthrough
          case "event":
            Task task = handleTaskInput(input);
            storeTask(task);
            break;
          case "delete":
            deleteTask(input);
            break;
          default:
            throw new DukeException(
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
      } catch (DukeException error) {
        System.out.println(error);
      }
    }

    in.close();
  }

  public static void greetings() {
    System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
  }

  public static void bye() {
    System.out.println("toodeloo!\n");
  }

  public static void echo(String input) {
    System.out.println(input);
  }

  public static String getUserInput(Scanner in) {
    String userInput = in.nextLine();

    return userInput;
  }

  public static void listTasks() {
    int size = Duke.taskStorage.size();

    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < size; i++)
      System.out.println(
          (i + 1) + ". " + Duke.taskStorage.get(i));

    System.out.println();
  }

  public static void markTask(String item) throws MarkIndexDoesNotExistException {
    if (item.split(" ").length == 1) {
      throw new MarkIndexDoesNotExistException(
          "☹ OOPS!!! missing mark index");
    }
    item = item.split(" ")[1];
    int index = Integer.parseInt(item) - 1;
    if (index >= Duke.taskStorage.size()) {
      throw new MarkIndexDoesNotExistException(
          "☹ OOPS!!! mark index does not exist");
    }
    Task task = Duke.taskStorage.get(index);
    task.markAsDone();

    System.out.println(
        String.format("Nice! I've marked this task as done:\n%s\n", task));
  }

  public static void unmarkTask(String item) throws UnmarkIndexDoesNotExistException {
    if (item.split(" ").length == 1) {
      throw new UnmarkIndexDoesNotExistException(
          "☹ OOPS!!! missing unmark index");
    }
    item = item.split(" ")[1];
    int index = Integer.parseInt(item) - 1;
    if (index >= Duke.taskStorage.size()) {
      throw new UnmarkIndexDoesNotExistException(
          "☹ OOPS!!! unmark index does not exist");
    }
    Task task = Duke.taskStorage.get(index);
    task.markAsNotDone();

    System.out.println(
        String.format("OK, I've marked this task as not done yet:\n%s\n", task));
  }

  public static Task handleTaskInput(String input) throws DukeException {
    String[] inputSplit = input.split(" ");
    String command = inputSplit[0];
    Task task = null;
    String taskInfo = getTaskInfo(inputSplit);

    switch (command) {
      case "todo":
        task = createTodo(taskInfo);
        break;
      case "deadline":
        task = createDeadline(taskInfo);
        break;
      case "event":
        task = createEvent(taskInfo);
        break;
    }

    return task;
  }

  public static void storeTask(Task task) {
    Duke.taskStorage.add(task);

    System.out.println("Got it. I've added this task:");
    System.out.println(task);
    System.out.println(
        "Now you have " + Duke.taskStorage.size() + " tasks in the list.\n");
  }

  public static Task createTodo(String taskInfo)
      throws EmptyTaskArgumentException {
    if (taskInfo.isEmpty()) {
      throw new EmptyTaskArgumentException(
          "☹ OOPS!!! The description of a todo cannot be empty.");
    }

    String description = taskInfo.strip();

    return new Todo(description);
  }

  public static Task createDeadline(String taskInfo) throws EmptyTaskArgumentException {
    String[] taskInfoArray = taskInfo.split("/by");

    if (taskInfo.isEmpty()) {
      throw new EmptyTaskArgumentException(
          "☹ OOPS!!! The description of a deadline cannot be empty.");
    }
    if (!taskInfo.contains("/by")) {
      throw new EmptyTaskArgumentException(
          "☹ OOPS!!! deadline requires a /by.");
    }
    if (taskInfoArray.length != 2) {
      throw new EmptyTaskArgumentException(
          "☹ OOPS!!! The /by of a deadline cannot be empty.");
    }

    String description = taskInfoArray[0].strip();
    String by = taskInfoArray[1].strip();

    return new Deadline(description, by);
  }

  public static Task createEvent(String taskInfo) throws EmptyTaskArgumentException {
    String[] taskInfoArray = taskInfo.split("/from");
    String[] timing = taskInfoArray[1].split("/to");

    if (taskInfo.isEmpty()) {
      throw new EmptyTaskArgumentException(
          "☹ OOPS!!! The description of a event cannot be empty.");
    }
    if (!taskInfo.contains("/from")) {
      throw new EmptyTaskArgumentException(
          "☹ OOPS!!! event requires a /from.");
    }
    if (!taskInfo.contains("/to")) {
      throw new EmptyTaskArgumentException(
          "☹ OOPS!!! event requires a /to.");
    }
    if (taskInfo.indexOf("/to") < taskInfo.indexOf("/from")) {
      throw new EmptyTaskArgumentException(
          "☹ OOPS!!! wrong format. It should be event <DESCRIPTION> /from <FROM> /to <TO>");
    }
    if (taskInfoArray.length != 2) {
      throw new EmptyTaskArgumentException(
          "☹ OOPS!!! The /from of a deadline cannot be empty.");
    }
    if (timing.length != 2) {
      throw new EmptyTaskArgumentException(
          "☹ OOPS!!! The /to of a deadline cannot be empty.");
    }

    String description = taskInfoArray[0].strip();
    String from = timing[0].strip();
    String to = timing[1].strip();

    return new Event(description, from, to);
  }

  public static void deleteTask(String item) throws DukeException {
    if (item.split(" ").length == 1) {
      throw new DukeException(
          "☹ OOPS!!! you are missing the item to delete");
    }

    item = item.split(" ")[1];
    int index = Integer.parseInt(item) - 1;

    if (index >= Duke.taskStorage.size()) {
      throw new DukeException(
          "☹ OOPS!!! delete index does not exist");
    }

    Task task = Duke.taskStorage.remove(index);
    System.out.println("Noted. I've removed this task:");
    System.out.println(task);
    System.out.println(
        "Now you have " + Duke.taskStorage.size() + " tasks in the list.\n");
  }

  private static String getTaskInfo(String[] inputSplit) {
    String[] removedCommand = Arrays.copyOfRange(
        inputSplit,
        1,
        inputSplit.length);
    String taskInfo = String.join(" ", removedCommand);

    return taskInfo;
  }
}
