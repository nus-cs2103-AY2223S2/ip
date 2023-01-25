import java.util.Scanner;

import database.Database;
import dukeexception.DukeException;
import dukeexception.EmptyTaskArgumentException;
import dukeexception.MarkIndexDoesNotExistException;
import dukeexception.RequestException;
import dukeexception.UnmarkIndexDoesNotExistException;
import request.DeadlineRequest;
import request.EventRequest;
import request.ToDoRequest;
import request.Request;
import request.Request.Commands;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Duke {
  static Database<Task> taskDB = new Database<Task>();

  public static void main(String[] args) {
    greet();
    handleRequest();
    bye();
  }

  public static void handleRequest() {
    Scanner in = new Scanner(System.in);

    loop: while (in.hasNextLine()) {
      try {
        String input = getUserInput(in);
        Request request = new Request(input);
        Commands cmd = request.getCommand();

        switch (cmd) {
        case LIST:
          listTasks();
          break;
        case UNMARK:
          unmarkTask(new Request(Commands.UNMARK, input));
          break;
        case MARK:
          markTask(new Request(Commands.UNMARK, input));
          break;
        case TODO:
          createTodo(new ToDoRequest(input));
          break;
        case DEADLINE:
          createDeadline(new DeadlineRequest(input));
          break;
        case EVENT:
          createEvent(new EventRequest(input));
          break;
        case DELETE:
          deleteTask(new Request(Commands.DELETE, input));
          break;
        case BYE:
          break loop;
        default:
          break;
        }
      } catch (DukeException error) {
        System.out.println(error);
      }
    }

    in.close();
  }

  public static void greet() {
    System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
  }

  public static void echo(String input) {
    System.out.println(input);
  }

  public static void bye() {
    System.out.println("toodeloo!\n");
  }

  public static String getUserInput(Scanner in) {
    String userInput = in.nextLine();

    return userInput;
  }

  public static void listTasks() {
    taskDB.listItems();
  }

  public static void unmarkTask(Request request) throws UnmarkIndexDoesNotExistException {
    try {
      int id = Integer.parseInt(request.unwrap()[0]);
      int index = id - 1;
      if (index >= Duke.taskDB.size()) {
        throw new UnmarkIndexDoesNotExistException("☹ OOPS!!! unmark index does not exist");
      }

      Task task = Duke.taskDB.getItem(index);
      task.markAsNotDone();

      System.out.println(String.format("OK, I've marked this task as not done yet:\n%s\n", task));
    } catch (RequestException error) {
      System.out.println("☹ OOPS!!! missing index to unmark");
    }
  }

  public static void markTask(Request request) throws MarkIndexDoesNotExistException {
    try {
      int id = Integer.parseInt(request.unwrap()[0]);
      int index = id - 1;
      if (index >= Duke.taskDB.size()) {
        throw new MarkIndexDoesNotExistException("☹ OOPS!!! mark index does not exist");
      }

      Task task = Duke.taskDB.getItem(index);
      task.markAsDone();

      System.out.println(String.format("Nice! I've marked this task as done:\n%s\n", task));
    } catch (RequestException error) {
      System.out.println("☹ OOPS!!! missing index to mark");
    }
  }

  public static Task createTodo(Request request) throws EmptyTaskArgumentException {
    try {
      String[] values = request.unwrap();
      String description = values[0];

      return new Todo(description);
    } catch (RequestException error) {
      throw new EmptyTaskArgumentException("☹ OOPS!!! Missing argument to create ToDo");
    }
  }

  public static Task createDeadline(Request request) throws EmptyTaskArgumentException {
    try {
      String[] values = request.unwrap();
      String description = values[0];
      String by = values[1];

      return new Deadline(description, by);
    } catch (RequestException error) {
      throw new EmptyTaskArgumentException("☹ OOPS!!! Missing argument to create Deadline");
    }
  }

  public static Task createEvent(Request request) throws EmptyTaskArgumentException {
    try {
      String[] values = request.unwrap();
      String description = values[0];
      String from = values[1];
      String to = values[2];

      return new Event(description, from, to);
    } catch (RequestException error) {
      System.out.println(error);
      throw new EmptyTaskArgumentException("☹ OOPS!!! Missing argument to create Event");
    }
  }

  public static void deleteTask(Request request) throws DukeException {
    try {
      int id = Integer.parseInt(request.unwrap()[0]);
      int index = id - 1;
      if (index >= Duke.taskDB.size()) {
        throw new MarkIndexDoesNotExistException("☹ OOPS!!! delete index does not exist");
      }

      Task task = Duke.taskDB.deleteItem(index);
      System.out.println("Noted. I've removed this task:");
      System.out.println(task);
      System.out.println("Now you have " + Duke.taskDB.size() + " tasks in the list.\n");

      System.out.println(String.format("Nice! I've marked this task as done:\n%s\n", task));
    } catch (RequestException error) {
      System.out.println("☹ OOPS!!! missing index to delete");
    }
  }
}
