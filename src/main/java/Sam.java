import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class Sam {
  private static Scanner scanner;
  private static TaskList tasks;
  private static HashMap<String, String> taskArgs;
  private static boolean live;

	public static void main(String[] args) {
    initSam();
    startSam();
    closeSam();
  }

  private static void initSam() {
    scanner = new Scanner(System.in);
    tasks = new TaskList();
    taskArgs = new HashMap<>();
    live = true;
  }

  private static void closeSam() {
    scanner.close();
  }

  private static void startSam() {
    System.out.println(Assets.LOGO);
    talk("Hello, I am Sam!");
    while (live) {
      System.out.println();
      System.out.println(Assets.USER);
      System.out.print("> ");
      String[] input = scanner.nextLine().strip().split(" ", 2);
      try {
        processInput(input);
      } catch (SamException e) {
        talk(e.getMessage());
      } finally {
        taskArgs.clear();
      }
    }
  }

  private static void processInput(String[] input)
    throws SamUnknownCommandException, SamMissingTaskException, SamInvalidTaskException,
      SamMissingTaskTitleException, SamMissingTaskValueException, SamMissingTaskArgException,
      SamInvalidDateException
  {
    Command command = null;
    for (Command c : Command.values())
      if (c.matches(input[0])) command = c;

    if (command == null) {
      throw new SamUnknownCommandException();
    }
    
    switch (command) {
      case BYE:
        live = false;
        talk("Goodbye!");
        break;
      case LIST:
      if (tasks.count() == 0) {
        talk("Your list is empty!");
      } else {
          // "Here is your list:"
          String[] list = tasks.generateList();
          talk(list);
        }
        break;
      case MARK: {
        if (input.length <= 1) {
          throw new SamMissingTaskException();
        }
        int id = Integer.parseInt(input[1]);
        if (id <= 0 || id > tasks.count()) {
          throw new SamInvalidTaskException();
        }
        tasks.markTask(id, true);
        talk("Great! I'll check the task:",
          tasks.printTask(id));
        break;
      }
      case UNMARK: {
        if (input.length <= 1) {
          throw new SamMissingTaskException();
        }
        int id = Integer.parseInt(input[1]);
        if (id <= 0 || id > tasks.count()) {
          throw new SamInvalidTaskException();
        }
        tasks.markTask(id, false);
        talk("Okay, I'll uncheck the task:",
          tasks.printTask(id));
        break;
      }
      case TODO: {
        if (input.length <= 1) {
          throw new SamMissingTaskTitleException();
        }
        Task task = new ToDo(input[1]);
        tasks.addTask(task);
        newTask(task);
        break;
      }
      case EVENT: {
        if (input.length <= 1 || input[1].strip().charAt(0) == '/') {
          throw new SamMissingTaskTitleException();
        }
        String[] title = input[1].strip().split(" /", 2);
        if (title.length > 1) parseTaskArgs(title[1]);
        if (!taskArgs.containsKey("from") || !taskArgs.containsKey("to")) {
          throw new SamMissingTaskArgException();
        }

        LocalDate from = parseDate(taskArgs.get("from"));
        LocalDate to = parseDate(taskArgs.get("to"));
        Task task = new Event(title[0], from, to);

        tasks.addTask(task);
        newTask(task);
        break;
      }
      case DEADLINE: {
        if (input.length <= 1 || input[1].strip().charAt(0) == '/') {
          throw new SamMissingTaskTitleException();
        }
        String[] title = input[1].strip().split(" /", 2);
        if (title.length > 1) parseTaskArgs(title[1]);
        if (!taskArgs.containsKey("by")) {
          throw new SamMissingTaskArgException();
        }


        System.out.println(taskArgs.get("by"));
        LocalDate by = parseDate(taskArgs.get("by"));
        Task task = new Deadline(title[0], by);

        tasks.addTask(task);
        newTask(task);
        break;
      }
      case DELETE: {
        if (input.length <= 1) {
          throw new SamMissingTaskException();
        }
        int id = Integer.parseInt(input[1]);
        if (id <= 0 || id > tasks.count()) {
          throw new SamInvalidTaskException();
        }
        Task task = tasks.removeTask(id);
        talk("Ok, I'll remove the task from your list:",
          task.toString());
        break;
      }
    }
  }

  private static void parseTaskArgs(String input) throws SamMissingTaskValueException {
    for (String arg : input.strip().split(" /")) {
      String[] keyValue = arg.split(" ", 2);
      if (keyValue.length <= 1) throw new SamMissingTaskValueException();
      taskArgs.put(keyValue[0], keyValue[1]);
    }
  }

  private static void newTask(Task task) {
    talk("Gotcha, I'll add the task to your list:",
      task.toString(),
      String.format("Now you have %d tasks in the list", tasks.count()));
  }

  private static void talk(String ...messages) {
    System.out.println(Assets.SAM);
    System.out.println("┌───────────────────────────────────────────┐");
    for (String message : messages) {
      System.out.println("  " + message);
    }
    System.out.println("└───────────────────────────────────────────┘");
  }

  private static LocalDate parseDate(String input) throws SamInvalidDateException {
    try {
      return LocalDate.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy"));
    } catch (DateTimeParseException e) {
      throw new SamInvalidDateException();
    }
  }
}
