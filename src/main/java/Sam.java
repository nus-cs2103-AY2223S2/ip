import java.util.HashMap;
import java.util.Scanner;

public class Sam {
  private static Scanner scanner = new Scanner(System.in);
  private static TaskList tasks = new TaskList();
  private static boolean live = true;
  private static HashMap<String, String> taskArgs = new HashMap<>();

	public static void main(String[] args) {
    System.out.println(Assets.LOGO);
    talk("Hello, I am Sam!");

    while (live) {
      System.out.println();
      System.out.println(Assets.USER);
      System.out.print("> ");
      String[] input = scanner.nextLine().split(" ", 2);
      
      Command command = Command.DEFAULT;
      for (Command c : Command.values())
        if (c.matches(input[0])) command = c;

			switch (command) {
				case BYE:
					live = false;
					talk("Goodbye!");
					break;
				case LIST:
          String list = tasks.generateList();
					talk("Here is your list:\n\n" + list);
					break;
        case MARK: {
          int id = Integer.parseInt(input[1]);
          tasks.markTask(id, true);
          talk("Great! I'll check the task:\n    "
             + tasks.printTask(id));
          break;
        }
        case UNMARK: {
          int id = Integer.parseInt(input[1]);
          tasks.markTask(id, false);
          talk("Okay, I'll uncheck the task:\n    "
             + tasks.printTask(id));
          break;
        }
        case TODO: {
          Task task = new ToDo(input[1]);
          tasks.addTask(task);
          newTask(task);
          break;
        }
        case EVENT: {
          String[] title = input[1].split(" /", 2);
          parseTaskArgs(title[1]);
          Task task = new Event(title[0], taskArgs.get("from"), taskArgs.get("to"));
          tasks.addTask(task);
          newTask(task);
          break;
        }
        case DEADLINE: {
          String[] title = input[1].split(" /", 2);
          parseTaskArgs(title[1]);
          Task task = new Deadline(title[0], taskArgs.get("by"));
          tasks.addTask(task);
          newTask(task);
          break;
        }
        default:
          talk("Sorry, I don't know what that means");
			}
      taskArgs.clear();
    }
    scanner.close();
  }

  private static void parseTaskArgs(String input) {
    for (String arg : input.split(" /")) {
      String[] keyValue = arg.split(" ", 2);
      System.out.println(input.split(" /"));
      taskArgs.put(keyValue[0], keyValue[1]);
    }
  }

  private static void newTask(Task task) {
    talk("Gotcha, I've added the task to your list:\n    "
       + task
       + "\n  Now you have " + tasks.count() + " tasks in the list");
  }

  private static void talk(String message) {
    System.out.println(Assets.SAM);
    System.out.println("┌───────────────────────────────────────────┐");
    System.out.println("  " + message);
    System.out.println("└───────────────────────────────────────────┘");
  }
}
