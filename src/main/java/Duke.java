import java.util.Scanner;
import java.util.*;

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
          String userInput = "";
          while (!userInput.equals("bye")) {
              userInput = userInputObj.nextLine();
              System.out.println(userInput);
          }
          System.out.println("Bye. Hope to see you again soon!");
    }
}
