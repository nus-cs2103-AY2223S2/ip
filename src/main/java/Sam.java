import java.time.LocalDate;
import java.util.Map;

public class Sam {
  private Ui ui;
  private Storage storage;
  private TaskList tasks;
  private boolean live;

  public Sam(String first, String ...more) {
    ui = new Ui();
    storage = new Storage(first, more);
    tasks = new TaskList();
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
      try {
        processInput(Parser.splitFirst(input));
      } catch (SamException e) {
        ui.talk(e.getMessage());
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
    Command command = Parser.getCommand(input[0]);
    Map<String, String> taskArgs;
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
        boolean success = tasks.markTask(id, true);
        if (!success) {
          throw new SamInvalidTaskException();
        }
        ui.talk("Great! I'll check the task:",
          tasks.getTask(id).toString());
        storage.save(tasks);
        break;
      }
      case UNMARK: {
        if (input.length <= 1) {
          throw new SamMissingTaskException();
        }
        int id = Integer.parseInt(input[1]);
        boolean success = tasks.markTask(id, false);
        if (!success) {
          throw new SamInvalidTaskException();
        }
        ui.talk("Okay, I'll uncheck the task:",
          tasks.getTask(id).toString());
        storage.save(tasks);
        break;
      }
      case TODO: {
        if (input.length <= 1) {
          throw new SamMissingTaskTitleException();
        }
        taskArgs = Parser.parseTaskArgs(input[1]);

        String title = taskArgs.get("title");
        Task task = new ToDo(title);

        tasks.addTask(task);
        newTask(task);
        storage.save(tasks);
        break;
      }
      case EVENT: {
        if (input.length <= 1) {
          throw new SamMissingTaskTitleException();
        }
        taskArgs = Parser.parseTaskArgs(input[1]);
        if (!taskArgs.containsKey("from") || !taskArgs.containsKey("to")) {
          throw new SamMissingTaskArgException();
        }

        String title = taskArgs.get("title");
        LocalDate from = Parser.parseDate(taskArgs.get("from"));
        LocalDate to = Parser.parseDate(taskArgs.get("to"));
        Task task = new Event(title, from, to);

        tasks.addTask(task);
        newTask(task);
        storage.save(tasks);
        break;
      }
      case DEADLINE: {
        if (input.length <= 1) {
          throw new SamMissingTaskTitleException();
        }
        taskArgs = Parser.parseTaskArgs(input[1]);
        if (!taskArgs.containsKey("by")) {
          throw new SamMissingTaskArgException();
        }

        String title = taskArgs.get("title");
        LocalDate by = Parser.parseDate(taskArgs.get("by"));
        Task task = new Deadline(title, by);

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
        Task task = tasks.removeTask(id);
        if (task == null) {
          throw new SamInvalidTaskException();
        }
        ui.talk("Ok, I'll remove the task from your list:",
          task.toString());
        storage.save(tasks);
        break;
      }
    }
  }

  private void newTask(Task task) {
    ui.talk("Gotcha, I'll add the task to your list:",
      task.toString(),
      String.format("Now you have %d tasks in the list", tasks.count()));
  }
}
