import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  protected static ArrayList<String> commandList = new ArrayList<>(Arrays.asList
                  ("todo", "deadline", "event", "mark", "unmark", "list", "bye", "delete"));
  public static void main(String[] args) throws EmptyInputException, InvalidCommandException {
    greet();
    run();
  }

  public static void run() throws EmptyInputException, InvalidCommandException{
    DukeList records = new DukeList();
    Scanner userInput = new Scanner(System.in);
      while (true) {
        try {
          String input = userInput.nextLine();
          if (input == "") {
            throw new EmptyInputException();
          }
          handleInput(input, records);
        }catch (EmptyInputException exception) {
          System.out.println("Empty input detected, please enter a value.");
          break;
        }
      }
  }

  public static void handleInput(String input, DukeList d) throws EmptyInputException, InvalidCommandException {
      if (input.contains("/")) {
        String[] split = input.split("/", 2);
        String[] secondSplit = split[0].split(" ", 2);

        String command = secondSplit[0];
        String name = secondSplit[1];
        String time = split[1];
        handleThreeInputs(command, name, time, d);
      } else {
        String[] split = input.split(" ", 2);
        String command = split[0];
        if ("todo".equalsIgnoreCase(command)) {
          d.insertToDo(split[1]);
        } else if (split.length > 1) {
          Integer index = Integer.parseInt(split[1]);
          handleTwoInputs(command, index, d);
        } else {
          try {
            handleSingleInput(command, d);
          }catch (InvalidCommandException exception) {
            System.out.println("Invalid command detected, please enter a value.");
          }
      }
    }
  }

  public static void handleSingleInput(String command, DukeList d) throws InvalidCommandException{
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
    String greeting = format("if it isn't your favourite astronaut lawyer doctor plumber cleaner, Johnny Sins."
    + "\n Ready to go on a self-exploration adventure?");
    System.out.println(greeting);
  }

  public static void exit(){
    String exitMsg = format("Bye. Come back soon!");
    System.out.println(exitMsg);
    System.exit(1);
  }
}
