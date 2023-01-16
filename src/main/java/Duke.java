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
    for (int i = 0; i < taskList.size(); i++) {
      output += " " + (i + 1) + ". " + taskList.get(i);
    }
    return seperator(output);
  }

  public static String markTask(int id) {
    Task currTask = taskList.get(id - 1);
    currTask.setDone();
    return seperator(String.format(" Good job for doing your task nya!\n  %s\n", currTask));
  }

  public static String unmarkTask(int id) {
    Task currTask = taskList.get(id - 1);
    currTask.setUndone();
    return seperator(String.format(" Set your task to undone nya!\n  %s\n", currTask));
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
      } else if (command.split(" ")[0].equals("mark")) {
        System.out.println(markTask(Integer.parseInt(command.split(" ")[1])));
      } else if (command.split(" ")[0].equals("unmark")) {
        System.out.println(unmarkTask(Integer.parseInt(command.split(" ")[1])));
      } else {
        System.out.println(addTask(command));
      }
      command = sc.nextLine();
    }
    System.out.println(bye());
    sc.close();
  }
}
