import java.util.Scanner;

public class Sam {
	public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
		TaskList tasks = new TaskList();
    boolean live = true;

    System.out.println(Assets.LOGO);
    talk("Hello, I am Sam!");

    while (live) {
      System.out.println();
      System.out.println(Assets.USER);
      System.out.print("> ");
      String input = scanner.nextLine();
      
      Command command = Command.ADD;
      for (Command c : Command.values())
        if (c.matches(input)) command = c;

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
          int id = getIntArg(input, 1);
          tasks.markTask(id, true);
          talk("Great! I'll check the task:\n    "
             + tasks.printTask(id));
          break;
        }
        case UNMARK: {
          int id = getIntArg(input, 1);
          tasks.markTask(id, false);
          talk("Okay, I'll uncheck the task:\n    "
             + tasks.printTask(id));
          break;
        }
        case ADD:
        default:
          Task task = new Task(input);
					tasks.addTask(task);
					talk("I've added \"" + input + "\" to your list");
			}
    }
    scanner.close();
  }

  private static void talk(String message) {
    System.out.println(Assets.SAM);
    System.out.println("┌───────────────────────────────────────────┐");
    System.out.println("  " + message);
    System.out.println("└───────────────────────────────────────────┘");
  }

  private static int getIntArg(String input, int i) {
    return Integer.parseInt(input.split(" ")[i]);
  }
}
