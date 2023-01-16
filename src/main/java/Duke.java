import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  static ArrayList<Task> taskList = new ArrayList<>();

  public static String seperator(String out) {
    String line = "____________________________________________________________\n";
    return line + " " + out + "\n" + line;
  }

  public static String greeting() {
    return seperator("Nyahello! I'm Nyako!\n What can I do for you nya?");
  }

  public static void addTask(String description) {
    taskList.add(new Task(description));
  }

  public static void listTasks() {
    String line = "____________________________________________________________\n";
    System.out.println(line);
    for (Task t : taskList) {
      System.out.println(t);
    }
    System.out.println(line);
  }

  public static String bye() {
    return seperator("Bye bye nya!");
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println(greeting());
    String command = sc.nextLine();
    while (!command.equals("bye")) {
      if (command.equals("add")) {
        addTask(description);
      }
      command = sc.next();
    }
    System.out.println(bye());
    sc.close();
  }
}
