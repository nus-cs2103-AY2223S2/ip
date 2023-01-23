import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Sam {
  private Ui ui;
  private TaskList tasks;
  private HashMap<String, String> taskArgs;
  private boolean live;
  private Path savePath;

  public Sam() {
    ui = new Ui();
    tasks = new TaskList();
    taskArgs = new HashMap<>();
    live = false;
    savePath = Path.of("data", "sam.txt");
  }

  public void run() {
    live = true;
    ui.showLogo();
    ui.talk("Hello, I am Sam!");
    try {
      load();
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
    new Sam().run();
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
        save();
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
          save();
        break;
      }
      case TODO: {
        if (input.length <= 1) {
          throw new SamMissingTaskTitleException();
        }
        Task task = new ToDo(input[1]);
        tasks.addTask(task);
        newTask(task);
        save();
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
        save();
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
        save();
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
        save();
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

  private void save() throws SamSaveFailedException {
    try {
      if (!Files.exists(savePath.getParent())) {
        Files.createDirectory(savePath.getParent());
      }
      if (!Files.exists(savePath)) {
        Files.createFile(savePath);
      }

      String[] list = new String[tasks.count()];
      for (int i = 0; i < tasks.count(); i++) {
        Task t = tasks.getTask(i + 1);
        list[i] = t.toSaveFormat();
      }

      if (list.length > 0) {
        Files.writeString(savePath, String.join("\n", list));
      }
    } catch (IOException e) {
      throw new SamSaveFailedException();
    }
  }

  private void load() throws SamLoadFailedException {
    try {
      if (!Files.exists(savePath)) {
        return;
      }
      List<String> lines = Files.readAllLines(savePath);
      for (String line : lines) {
        String[] arr = line.split(" [|] ");
        Task t = null;
        boolean isDone = arr[1].equals("1");
        switch (arr[0]) {
          case "T":
            t = new ToDo(arr[2], isDone);
            break; 
          case "E": {
            LocalDate from = parseDate(arr[3]);
            LocalDate to = parseDate(arr[4]);
            t = new Event(arr[2], from, to, isDone);
            break; 
          }
          case "D": {
            LocalDate by = parseDate(arr[3]);
            t = new Deadline(arr[2], by, isDone);
            break;
          }
        }
        if (t != null) {
          tasks.addTask(t);
        }
      }
    } catch (IOException | SamInvalidDateException e) {
      throw new SamLoadFailedException();
    }
  }
  
  private LocalDate parseDate(String input) throws SamInvalidDateException {
    try {
      return LocalDate.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy"));
    } catch (DateTimeParseException e) {
      throw new SamInvalidDateException();
    }
  }
}
