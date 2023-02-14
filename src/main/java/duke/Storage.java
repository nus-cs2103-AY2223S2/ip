package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage interface that deals with the loading and storing of the tasks' data.
 */
public class Storage {
  public static final String DATA_READ_ERROR = "Unknown entry in data file";
  public static final String UNEXPECTED_ERROR_GETTING_DATA =
      "Unexpected error when getting data.";
  public static final String ERROR_CREATING_NEW_FILE =
      "Error when trying to create new file.";
  /**
   * The file path where the tasks data are stored.
   */
  private final String filePath;

  /**
   * Constructor for a new storage interface with the file path of the tasks
   * data provided.
   *
   * @param filePath The file path where the tasks data are stored
   */
  public Storage(String filePath) { this.filePath = filePath; }

  private static void checkError(IOException e) {
    if (!(e instanceof FileNotFoundException)) {
      throw new DukeException(UNEXPECTED_ERROR_GETTING_DATA);
    }
  }

  /**
   * Attempt to load the tasks based on the data of the tasks provided. If the
   * file does not exist, then a new blank file is created instead. Throws an
   * exception if the program cannot create a new file or there is an error
   * obtaining the data from the file path.
   *
   * @return A list of tasks loaded from the file path
   */
  public ArrayList<Task> load() throws DukeException {
    ArrayList<Task> tasks = new ArrayList<>();
    try {
      loadTasks(tasks);
    } catch (IOException e) {
      checkError(e);
      makeFile();
    }
    return tasks;
  }

  private void makeFile() {
    File file = new File(filePath);
    try {
      // noinspection ResultOfMethodCallIgnored
      file.getParentFile().mkdirs();
      // noinspection ResultOfMethodCallIgnored
      file.createNewFile();
    } catch (IOException ex) {
      throw new DukeException(ERROR_CREATING_NEW_FILE);
    }
  }

  private void loadTasks(ArrayList<Task> tasks) throws FileNotFoundException {
    File file = new File(filePath);
    Scanner scanner = new Scanner(file);

    while (scanner.hasNextLine()) {
      String data = scanner.nextLine();
      Task task = readFromData(data);
      if (!task.isEmpty()) {
        tasks.add(task);
      }
    }

    scanner.close();
  }

  /**
   * Saves all the tasks into their corresponding string representation to be
   * loaded in the future. Throws an exception if there is an error accessing or
   * saving the data to the file path.
   *
   * @param tasks The list of tasks to be saved
   * @throws DukeException If there is an error saving the data of the tasks
   */
  public void updateData(TaskList tasks) throws DukeException {
    try {
      FileWriter fileWriter = new FileWriter(this.filePath);
      String data = toData(tasks);
      fileWriter.write(data);
      fileWriter.close();
    } catch (IOException e) {
      throw new DukeException("Error has occurred when saving data.");
    }
  }

  /**
   * Converts the list of tasks provided to the corresponding string
   * representation to be saved.
   *
   * @param taskList The list of tasks
   * @return The string representation of all the tasks
   */
  private String toData(TaskList taskList) {
    StringBuilder output = new StringBuilder();
    for (Task task : taskList.getTasks()) {
      output.append(task.toData());
      output.append("\n");
    }
    return output.toString();
  }

  /**
   * Converts a string representation of the task into its respective task
   * object and is returned.
   *
   * @param data The string representation of the task data
   * @return A corresponding task to the provided data
   * @throws DukeException If the string representation does not match any
   *     format of tasks
   */
  private Task readFromData(String data) throws DukeException {
    String[] typeSplit = data.split(" \\| ", 2);
    switch (typeSplit[0]) {
    case "Todo":
      return Todo.readFromData(typeSplit[1]);
    case "Deadline":
      return Deadline.readFromData(typeSplit[1]);
    case "Event":
      return Event.readFromData(typeSplit[1]);
    default:
      throw new DukeException(DATA_READ_ERROR);
    }
  }
}
