import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  static ArrayList<Task> taskList = new ArrayList<>();

  public static String separator(String out) {
    String line = "____________________________________________________________\n";
    return line + out + line;
  }

  public static String greeting() {
    return separator(" Nyahello! I'm Nyako!\n What can I do for you nya?\n");
  }

  public static String addTask(String input) {
    Task t = makeTask(input);
    taskList.add(t);
    return separator(String.format(" Added task nya!\n  %s\n" +
        " Nyow you have %d tasks in the list nya!\n", t, taskList.size()));
  }

  public static Task makeTask(String input) {
    String taskType = input.split(" ")[0];
    String rest = input.substring(input.indexOf(" ") + 1);

    if (taskType.equals("todo")) {
      return new ToDo(rest);
    } else if (taskType.equals("deadline")) {
      String[] words = rest.split("/by");
      return new Deadline(words[0].strip(), words[1].strip());
    } else {
      String description = rest.split("/from")[0].strip();
      String start = rest.split("/from")[1].split("/to")[0].strip();
      String end = rest.split("/to")[1].strip();
      return new Event(description, start, end);
    }
  }

  public static String listTasks() {
    String output = "";
    for (int i = 0; i < taskList.size(); i++) {
      output += " " + (i + 1) + ". " + taskList.get(i) + "\n";
    }
    return separator(output);
  }

  public static String markTask(int id) {
    Task currTask = taskList.get(id - 1);
    currTask.setDone();
    return separator(String.format(" Good job for doing your task nya!\n  %s\n", currTask));
  }

  public static String unmarkTask(int id) {
    Task currTask = taskList.get(id - 1);
    currTask.setUndone();
    return separator(String.format(" Set your task to undone nya!\n  %s\n", currTask));
  }

  public static String bye() {
    return separator(" Bye bye nya!\n");
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
