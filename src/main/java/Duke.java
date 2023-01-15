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
    String input = scanner.nextLine();

    String exitToken = "bye";
    String listToken = "list";
    ArrayList<String> data = new ArrayList<>();

    while (!input.equals(exitToken)) {
      if (input.equals(listToken)) {
        printMsg(data);
      } else {
        data.add(input);
        String msg = String.format("Added new entry: %s", input);
        printMsg(msg);
      }
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

  public static void printMsg(ArrayList<String> msg) {
    String spacer = "____________________"
        + "______________________";
    String msgHeader = "Current data in the list are:";

    System.out.println(spacer);
    System.out.println(msgHeader);
    for (int i = 0; i < msg.size(); i++) {
      String output = String.format("%d. %s", i + 1, msg.get(i));
      System.out.println(output);
    }
    System.out.println(spacer);
  }
}
