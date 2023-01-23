import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Sam {
  private Ui ui;
  private Storage storage;
  private TaskList tasks;
  private HashMap<String, String> taskArgs;
  private boolean live;

  public Sam(String first, String ...more) {
    ui = new Ui();
    storage = new Storage(first, more);
    tasks = new TaskList();
    taskArgs = new HashMap<>();
    live = false;
  }

  public void run() {
    live = true;
    ui.showLogo();
    ui.talk("Hello, I am Sam!");
    try {
      storage.load(tasks);
    } catch (SamLoadFailedException e) {
      ui.talk(e.getMessage());
    }
    while (live) {
      String input = ui.acceptInput();
      String[] splitInput = input.split(" ", 2);
      try {
        processInput(splitInput);
      } catch (SamException e) {
        ui.talk(e.getMessage());
      } finally {
        taskArgs.clear();
      }
    }
  }

	public static void main(String[] args) {
    new Sam("data", "sam.txt").run();
  }

  private void processInput(String[] input)
    throws SamUnknownCommandException, SamMissingTaskException, SamInvalidTaskException,
      SamMissingTaskTitleException, SamMissingTaskValueException, SamMissingTaskArgException,
      SamSaveFailedException, SamInvalidDateException
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
        ui.talk("Goodbye!");
        break;
      case LIST:
      if (tasks.count() == 0) {
        ui.talk("Your list is empty!");
      } else {
          // "Here is your list:"
          String[] list = tasks.generateList();
          ui.talk(list);
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
        ui.talk("Great! I'll check the task:",
          tasks.printTask(id));
        storage.save(tasks);
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
        ui.talk("Okay, I'll uncheck the task:",
          tasks.printTask(id));
        storage.save(tasks);
        break;
      }
      case TODO: {
        if (input.length <= 1) {
          throw new SamMissingTaskTitleException();
        }
        Task task = new ToDo(input[1]);
        tasks.addTask(task);
        newTask(task);
        storage.save(tasks);
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
        storage.save(tasks);
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
        storage.save(tasks);
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
        ui.talk("Ok, I'll remove the task from your list:",
          task.toString());
        storage.save(tasks);
        break;
      }
    }
  }

  private void parseTaskArgs(String input) throws SamMissingTaskValueException {
    for (String arg : input.strip().split(" /")) {
      String[] keyValue = arg.split(" ", 2);
      if (keyValue.length <= 1) throw new SamMissingTaskValueException();
      taskArgs.put(keyValue[0], keyValue[1]);
    }
  }

  private void newTask(Task task) {
    ui.talk("Gotcha, I'll add the task to your list:",
      task.toString(),
      String.format("Now you have %d tasks in the list", tasks.count()));
  }
  
  public static LocalDate parseDate(String input) throws SamInvalidDateException {
    try {
      return LocalDate.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy"));
    } catch (DateTimeParseException e) {
      throw new SamInvalidDateException();
    }
  }
}
