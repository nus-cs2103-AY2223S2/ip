import java.util.Scanner;

public class Duke {

  public static void main(String[] args) {
    String logo =
      " ____        _        \n" +
      "|  _ \\ _   _| | _____ \n" +
      "| | | | | | | |/ / _ \\\n" +
      "| |_| | |_| |   <  __/\n" +
      "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println(
      "\n____________________________________________________________"
    );
    System.out.println("Hello! I'm Duke");
    System.out.println("What can I do for you?");
    System.out.println(
      "____________________________________________________________\n"
    );

    // parse user input
    Scanner scanner = new Scanner(System.in);
    String command;

    // level 1 functionality
    while (true) {
      command = scanner.nextLine();
      System.out.println(
        "____________________________________________________________"
      );
      if (command.equals("bye")) {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(
          "____________________________________________________________\n"
        );
        break;
      } else {
        System.out.println(command);
        System.out.println(
          "____________________________________________________________\n"
        );
      }
    }
  }
}
