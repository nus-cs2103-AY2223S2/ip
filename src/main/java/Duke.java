import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
  public static void main(String[] args) {
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

    System.out.println(logo);

    String introMsg = "Hello! I'm Duke.\n"
        + "What can I do for you today?";
    printMsg(introMsg);

    Scanner scanner = new Scanner(System.in);

    ArrayList<Task> tasks = new ArrayList<>();
    boolean cont = true;

    while (cont) {
      String input = scanner.nextLine();
      String[] inputSplit = input.split(" ", 2);
      try {
        switch (inputSplit[0]) {
          case "bye":
            cont = false;
            break;
          case "list":
            printTasks(tasks);
            break;
          case "mark":
            if (inputSplit.length < 2) {
              throw new DukeException("Mark command missing list numbering.");
            }
            execMarkTask(inputSplit[1], tasks);
            break;
          case "unmark":
            if (inputSplit.length < 2) {
              throw new DukeException("Unmark command missing list numbering.");
            }
            execUnmarkTask(inputSplit[1], tasks);
            break;
          case "todo":
            if (inputSplit.length < 2) {
              throw new DukeException("Todo command missing description.");
            }
            execTodo(inputSplit[1], tasks);
            break;
          case "deadline":
            if (inputSplit.length < 2) {
              throw new DukeException("Deadline command missing description.");
            }
            execDeadline(inputSplit[1], tasks);
            break;
          case "event":
            if (inputSplit.length < 2) {
              throw new DukeException("Event command missing description.");
            }
            execEvent(inputSplit[1], tasks);
            break;
          default:
            throw new DukeException("Sorry but I don't understand what this means.");
        }
      } catch (DukeException e) {
        printError(e);
      } catch (Exception e) {
        printMsg("Unknown command/error not caught!\n" +
            "Please try again!");
      }
    }

    String exitMsg = "Thank you for coming!\n"
        + "Hope to see you again soon!\n"
        + "~~Bye";
    printMsg(exitMsg);
  }

  static String spacer = "____________________"
      + "______________________";

  public static void printMsg(String msg) {
    System.out.println(spacer);
    System.out.println(msg);
    System.out.println(spacer);
  }

  public static void printError(DukeException e) {
    System.out.println(spacer);
    System.out.println(e.getMessage());
    System.out.println("Please try again!");
    System.out.println(spacer);
  }

  public static void printTasks(ArrayList<Task> tasks) {
    String msgHeader = "Current data in the list are:";

    System.out.println(spacer);
    System.out.println(msgHeader);
    for (int i = 0; i < tasks.size(); i++) {
      String output = String.format("%d. %s", i + 1, tasks.get(i));
      System.out.println(output);
    }
    System.out.println(spacer);
  }

  public static int isValidIndex(String indexStr, ArrayList<Task> tasks)
      throws DukeException {
    Pattern p = Pattern.compile("^[0-9]+$");
    boolean isNumber = p.matcher(indexStr).matches();

    if (!isNumber) {
      throw new DukeException("Index not an integer");
    }

    int index = Integer.parseInt(indexStr) - 1;
    if (index < 0 || index >= tasks.size()) {
      throw new DukeException("Index out of bounds of tasks list");
    }

    return index;
  }

  public static void execMarkTask(String indexStr, ArrayList<Task> tasks)
      throws DukeException {
    int index = isValidIndex(indexStr, tasks);

    tasks.get(index).markTask();

    String msgHeader = "I've marked this task as done:";

    System.out.println(spacer);
    System.out.println(msgHeader);
    System.out.println(tasks.get(index));
    System.out.println(spacer);
  }

  public static void execUnmarkTask(String indexStr, ArrayList<Task> tasks)
      throws DukeException {
    int index = isValidIndex(indexStr, tasks);

    tasks.get(index).unmarkTask();

    String msgHeader = "I've unmarked this task as not done:";

    System.out.println(spacer);
    System.out.println(msgHeader);
    System.out.println(tasks.get(index));
    System.out.println(spacer);
  }

  public static void printTask(Task task, int size) {
    String msgHeader = "I've added this task into the list:";
    String msgFooter = String.format("Now you have a total of %s tasks in the list", size);

    System.out.println(spacer);
    System.out.println(msgHeader);
    System.out.println(task);
    System.out.println(msgFooter);
    System.out.println(spacer);
  }

  public static void execTodo(String description, ArrayList<Task> tasks)
      throws DukeException {
    Task todo = new Todo(description);
    tasks.add(todo);
    printTask(todo, tasks.size());
  }

  public static void execDeadline(String data, ArrayList<Task> tasks)
      throws DukeException {
    String[] splitData = data.split(" /by ", 2);
    if (splitData.length < 2) {
      throw new DukeException("Deadline command format error. Missing /by");
    }

    Task deadline = new Deadline(splitData[0], splitData[1]);
    tasks.add(deadline);
    printTask(deadline, tasks.size());
  }

  public static void execEvent(String data, ArrayList<Task> tasks)
      throws DukeException {
    String[] splitData1 = data.split(" /from ", 2);
    if (splitData1.length < 2) {
      throw new DukeException("Event command format error. Missing /from");
    }

    String[] splitData2 = splitData1[1].split(" /to ", 2);
    if (splitData2.length < 2) {
      throw new DukeException("Event command format error. Missing /to");
    }

    Task event = new Event(splitData1[0], splitData2[0], splitData2[1]);
    tasks.add(event);
    printTask(event, tasks.size());
  }
}
