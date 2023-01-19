import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
          System.out.println("Hello! I'm Duke\nWhat can I do for you?");
          Scanner userInputObj = new Scanner(System.in);
          ArrayList<String> storage = new ArrayList<String>();
          String userInput = "";
          while (!userInput.equals("bye")) {
              userInput = userInputObj.nextLine();
              if (userInput.equals("list")) {
                  for (int i = 0; i < storage.size(); i++) {
                      System.out.println((i + 1) + ". " + storage.get(i));
                  }
              } else {
                  storage.add(userInput);
                  System.out.println("added: " + userInput);
              }
          }
          System.out.println("Bye. Hope to see you again soon!");
    }
}
