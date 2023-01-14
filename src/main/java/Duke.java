import java.util.*;

public class Duke {
  public static void main(String[] args) {
    ArrayList<String> taskList = new ArrayList();
    System.out.println("  ------------------------------------");
    System.out.println("  Hello! I'm Duke\n" + "  What can I do for you?");
    System.out.println("  ------------------------------------\n");
    Scanner sc = new Scanner(System.in);
    String userInput = sc.nextLine().trim();

    while (!userInput.equalsIgnoreCase("bye")) {
      System.out.println("  ------------------------------------");
      handleListInput(userInput, taskList);
      System.out.println("  ------------------------------------\n");
      userInput = sc.nextLine().trim();
    }

    System.out.println("  ------------------------------------");
    System.out.println("  Bye. Hope to see you again soon!");
    System.out.println("  ------------------------------------");
    sc.close();
  }

  private static void handleListInput(String userInput, ArrayList<String> taskList) {
    if (userInput.equalsIgnoreCase("list")) {
      if (taskList.isEmpty()) {
        System.out.println("  No tasks added yet");
      }
      for (String task : taskList) {
        System.out.println(task);
      }
    } else {
      if (taskList.size() < 100) {
        System.out.println(new StringBuilder("  added: ").append(userInput).toString());
        taskList.add(new StringBuilder("  ").append(taskList.size() + 1).append(". ").append(userInput).toString());
      } else {
        System.out.println("  List is full!");
      }
    }
  }
}
