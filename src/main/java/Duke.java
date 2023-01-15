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
    String input = scanner.nextLine();
    String exitToken = "bye";

    while (!input.equals(exitToken)) {
      printMsg(input);
      input = scanner.nextLine();
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
}
