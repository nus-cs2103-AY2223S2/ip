import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  static ArrayList<Task> taskList = new ArrayList<>();

  public static String seperator(String out) {
    String line = "____________________________________________________________\n";
    return line + out + line;
  }

  public static String greeting() {
    return seperator(" Nyahello! I'm Nyako!\n What can I do for you nya?\n");
  }

  public static String addTask(String description) {
    taskList.add(new Task(description));
    return seperator(" added: " + description + "\n");
  }

  public static String listTasks() {
    String output = "";
    for (Task t : taskList) {
      output += " " + t;
    }
    return seperator(output);
  }

  public static String bye() {
    return seperator(" Bye bye nya!\n");
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println(greeting());
    String command = sc.nextLine();
    while (!command.equals("bye")) {
      if (command.equals("list")) {
        System.out.println(listTasks());
      } else {
        System.out.println(addTask(command));
      }
      command = sc.nextLine();
    }
    System.out.println(bye());
    sc.close();
  }
}
