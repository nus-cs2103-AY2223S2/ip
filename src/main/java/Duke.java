import java.util.ArrayList;
import java.util.Scanner;

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

    String listOutOfBounds = "You are trying to access"
        + " a task not in the list.\n"
        + "Please try again!";
    String numberFormatException = "You entered an invalid"
        + " number.\n"
        + "Please try again!";

    while (cont) {
      String input = scanner.nextLine();
      String[] inputSplit = input.split(" ");
      int index;
      switch (inputSplit[0]) {
        case "bye":
          cont = false;
          break;
        case "list":
          printTasks(tasks);
          break;
        case "mark":
          try {
            index = Integer.parseInt(inputSplit[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
              printMsg(listOutOfBounds);
            } else {
              tasks.get(index).markTask();
              printMarkTask(tasks.get(index));
            }
          } catch (NumberFormatException e) {
            printMsg(numberFormatException);
          }
          break;
        case "unmark":
          try {
            index = Integer.parseInt(inputSplit[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
              printMsg(listOutOfBounds);
            } else {
              tasks.get(index).unmarkTask();
              printUnmarkTask(tasks.get(index));
            }
          } catch (NumberFormatException e) {
            printMsg(numberFormatException);
          }
          break;
        default:
          tasks.add(new Task(input));
          String msg = String.format("Added new entry: %s", input);
          printMsg(msg);
          break;
      }
    }

    String exitMsg = "Thank you for coming!\n"
        + "Hope to see you again soon!\n"
        + "~~Bye";
    printMsg(exitMsg);
  }

  public static void printMsg(String msg) {
    String spacer = "____________________"
        + "______________________";

    System.out.println(spacer);
    System.out.println(msg);
    System.out.println(spacer);
  }

  public static void printTasks(ArrayList<Task> tasks) {
    String spacer = "____________________"
        + "______________________";
    String msgHeader = "Current data in the list are:";

    System.out.println(spacer);
    System.out.println(msgHeader);
    for (int i = 0; i < tasks.size(); i++) {
      String output = String.format("%d. %s", i + 1, tasks.get(i));
      System.out.println(output);
    }
    System.out.println(spacer);
  }

  public static void printMarkTask(Task task) {
    String spacer = "____________________"
        + "______________________";
    String msgHeader = "I've marked this task as done:";

    System.out.println(spacer);
    System.out.println(msgHeader);
    System.out.println(task);
    System.out.println(spacer);
  }

  public static void printUnmarkTask(Task task) {
    String spacer = "____________________"
        + "______________________";
    String msgHeader = "I've unmarked this task as not done:";

    System.out.println(spacer);
    System.out.println(msgHeader);
    System.out.println(task);
    System.out.println(spacer);
  }
}
