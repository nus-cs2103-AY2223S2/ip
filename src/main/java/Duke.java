import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  public static void main(String[] args) {
    greet();
    run();
  }

  public static void run() {
    DukeList records = new DukeList();
    Scanner userInput = new Scanner(System.in);
    while (true) {
      String input = userInput.nextLine();
      handleInput(input, records);
    }
  }

  public static void handleInput(String input, DukeList d) {
    if (input.contains("/")) {
      String[] split = input.split("/", 2);
      String[] secondSplit = split[0].split(" ", 2);

      String command = secondSplit[0];
      String name = secondSplit[1];
      String time = split[1];
      handleThreeInputs(command, name, time, d);
    }
    else {
      String[] split = input.split(" ", 2);
      String command = split[0];
      if ("todo".equalsIgnoreCase(command)) {
        d.insertToDo(split[1]);
      }
      else if (split.length > 1) {
        Integer index = Integer.parseInt(split[1]);
        handleTwoInputs(command, index, d);
      } else {
        handleSingleInput(command, d);
      }
    }
  }

  public static void handleSingleInput(String command, DukeList d) {
    switch (command) {
      case "bye":
        exit();
        break;
      case "list":
        System.out.println(d.toString());
        break;
      default:
        d.insert(command);
    }
  }

  public static void handleTwoInputs(String command, Integer index, DukeList d) {
    switch (command) {
      case "mark":
        d.mark(index);
        break;
      case "unmark":
        d.unMark(index);
        break;
    }
  }

  public static void handleThreeInputs(String command, String name, String time, DukeList d) {
    switch (command) {
      case "deadline":
        d.insertDeadline(name, time);
        break;
      case "event":
        d.insertEvent(name, time);
        break;
      case "todo":
        d.insertToDo(name);
        break;
    }
  }

  public static String format(String input) {
    return "____________________________________________________________\n" +
            input +
            "\n____________________________________________________________";
  }

  public static void greet() {
    String greeting = "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";
    System.out.println(greeting);
  }

  public static void exit() {
    String exitMsg = format("Bye. Hope to see you again soon!");
    System.out.println(exitMsg);
    System.exit(1);
  }
}
