import java.util.*;  
public class Duke {
    public static void main(String[] args) {
      System.out.println("  ------------------------------------");
      System.out.println("  Hello! I'm Duke\n" + "  What can I do for you?");
      System.out.println("  ------------------------------------\n");
      Scanner sc = new Scanner(System.in);
      String userInput = sc.nextLine();
      while (!userInput.trim().toLowerCase().equals("bye")) {
        System.out.println("  ------------------------------------");
        System.out.println("  " + userInput);
        System.out.println("  ------------------------------------\n");
        userInput = sc.nextLine();
      }
      System.out.println("  ------------------------------------");
      System.out.println("  Bye. Hope to see you again soon!");
      System.out.println("  ------------------------------------");
      sc.close();
    }
}
