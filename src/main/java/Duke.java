import java.util.Scanner;

public class Duke {

  public static void prettyPrint(String text) {
    System.out.println(
      "____________________________________________________________"
    );
    System.out.println(text);
    System.out.println(
      "____________________________________________________________\n"
    );
  }

  public static void main(String[] args) {
    String logo =
      " ____        _        \n" +
      "|  _ \\ _   _| | _____ \n" +
      "| | | | | | | |/ / _ \\\n" +
      "| |_| | |_| |   <  __/\n" +
      "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);

    Duke.prettyPrint("Hello! I'm Duke\nWhat can I do for you?");

    // parse user input
    Scanner scanner = new Scanner(System.in);
    String command;

    // level 1 functionality
    while (true) {
      command = scanner.nextLine();
      if (command.equals("bye")) {
        Duke.prettyPrint("Bye. Hope to see you again soon!");
        break;
      } else {
        Duke.prettyPrint(command);
      }
    }
  }
}
