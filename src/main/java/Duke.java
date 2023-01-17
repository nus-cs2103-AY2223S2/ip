import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  static ArrayList<Task> taskList = new ArrayList<>();

  public static void print(String toPrint) {
    String line = "____________________________________________________________\n";
    System.out.println(line + " " + toPrint + line);
  }

  public static String greeting() {
    return "Nyahello! I'm Nyako!\n What can I do for you nya?\n";
  }

  public static String addTask(String input) {
    Task t = makeTask(input);
    taskList.add(t);
    return String.format("Added task nya!\n  %s\n" +
        " Nyow you have %d tasks in the list nya!\n", t, taskList.size());
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
    return "Here are your tasks nya!\n" + output;
  }

  public static String markTask(int id) {
    Task currTask = taskList.get(id - 1);
    currTask.setDone();
    return String.format("Good job for doing your task nya!\n  %s\n", currTask);
  }

  public static String unmarkTask(int id) {
    Task currTask = taskList.get(id - 1);
    currTask.setUndone();
    return String.format("Set your task to undone nya!\n  %s\n", currTask);
  }

  public static String bye() {
    return "Bye bye nya!\n";
  }

  public static String invalidCommand() {
    return "Invalid command nya! Do it again and I will scratch you!\n";
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    print(greeting());
    String command = sc.nextLine();
    while (!command.equals("bye")) {
      switch (command.split(" ")[0]) {
        case "list":
          print(listTasks());
          break;
        case "mark":
          print(markTask(Integer.parseInt(command.split(" ")[1])));
          break;
        case "unmark":
          print(unmarkTask(Integer.parseInt(command.split(" ")[1])));
          break;
        case "todo":
        case "deadline":
        case "event":
          print(addTask(command));
          break;
        default:
          print(invalidCommand());
      }
      command = sc.nextLine();
    }
    print(bye());
    sc.close();
  }
}
