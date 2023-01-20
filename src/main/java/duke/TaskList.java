package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility used by Duke to manage tasks.
 *
 * @see Duke
 * @see Task
 * @see Storage
 */
public class TaskList {
  private final List<Task> tasks;
  private final Storage storage = new Storage();

  /**
   * Constructs a new TaskList with the given tasks.
   *
   * @param tasks the tasks to be managed
   */
  public TaskList(List<Task> tasks) {
    this.tasks = tasks;
    storage.save(tasks);
  }

  /**
   * Constructs a new TaskList with no tasks and stores it in the given file.
   *
   * @param fileName the name of the file to be used to store tasks.
   * @throws IOException            if the file cannot be found or created.
   * @throws ClassNotFoundException if the file cannot be deserialized.
   */
  public TaskList(String fileName) throws IOException, ClassNotFoundException, ClassCastException {
    List<Task> tasks = new ArrayList<>();
    try {
      tasks = storage.load(fileName);
    } catch (FileNotFoundException e) {
      storage.save(tasks, fileName);
    }
    this.tasks = tasks;
  }

  /**
   * Constructs a new TaskList with no tasks and stores it in the default location.
   *
   * @throws IOException            if the file cannot be found or created.
   * @throws ClassNotFoundException if the file cannot be deserialized.
   * @see Storage
   */
  public TaskList() throws IOException, ClassNotFoundException, ClassCastException {
    List<Task> tasks = new ArrayList<>();
    try {
      tasks = storage.load();
    } catch (FileNotFoundException e) {
      storage.save(tasks);
    }
    this.tasks = tasks;
  }

  /**
   * Returns a string representation of the tasks in the list.
   *
   * @return a string representation of the tasks in the list.
   * @see Ui#print(String)
   */
  public String[] stringify() {
    if (this.tasks.size() == 0) {
      return new String[]{"No tasks found."};
    }
    String[] outputs = new String[this.tasks.size()];
    for (int i = 0; i < this.tasks.size(); i++) {
      outputs[i] = addOrdinal(i, this.tasks.get(i));
    }
    return outputs;
  }

  /**
   * Creates a new {@link Task} from the given arguments and adds it to the list.
   *
   * @param arguments the arguments to be used to create the task.
   * @return a string representation of the task that was added.
   */
  public String[] add(String[] arguments) {
    Task newTask;
    switch (arguments[0]) {
      case "/todo" -> newTask = new ToDo(arguments[1]);
      case "/deadline" -> {
        String[] deadlineArgs = Parser.parseArgs(arguments[1], new String[]{" /by "});
        newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
      }
      case "/event" -> {
        String[] eventArgs = Parser.parseArgs(arguments[1], new String[]{" /from ", " /to "});
        newTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
      }
      default -> throw new IllegalArgumentException("Invalid task type: " + arguments[0]);
    }
    this.tasks.add(newTask);
    this.save();
    return new String[]{"added: " + newTask};
  }

  /**
   * Marks (toggle) the task(s) at the given index(es).
   *
   * @param indStr the index(es) of the task(s) to be marked as done.
   * @return a string representation of the task(s) that were (un)marked.
   */
  public String[] mark(String[] indStr) {
    List<Integer> indexes = new ArrayList<>();
    Consumer<Integer> mark = ind -> {
      this.tasks.get(ind).mark();
      indexes.add(ind);
    };
    this.consume(indStr, mark);
    this.save();
    if (indexes.size() == 0) {
      throw new IllegalArgumentException("No tasks found.");
    } else if (indexes.size() == 1) {
      Task task = this.tasks.get(indexes.get(0));
      return new String[]{(task.isDone() ? "marked: " : "unmarked: ")
          + addOrdinal(indexes.get(0), task)};
    } else {
      List<String> outputs = indexes.stream()
          .map(i -> "\t" + addOrdinal(i, this.tasks.get(i)))
          .collect(Collectors.toList());
      outputs.add(0, "marked:");
      return outputs.toArray(new String[0]);
    }
  }

  /**
   * Deletes the task(s) at the given index(es).
   *
   * @param indStr the index(es) of the task(s) to be deleted.
   * @return a string representation of the task(s) that were deleted.
   */
  public String[] delete(String[] indStr) {
    List<Integer> indexes = new ArrayList<>();
    Consumer<Integer> delete = ind -> {
      if (this.tasks.size() <= ind) {
        throw new IllegalArgumentException("Task not found: " + ind);
      }
      indexes.add(ind);
    };
    this.consume(indStr, delete);
    if (indexes.size() == 0) {
      throw new IllegalArgumentException("No tasks found.");
    } else if (indexes.size() == 1) {
      Task rmTask = this.tasks.remove((int) indexes.get(0));
      this.save();
      return new String[]{"deleted: " + addOrdinal(indexes.get(0), rmTask)};
    } else {
      indexes.sort(Collections.reverseOrder());
      List<String> outputs = new ArrayList<>();
      for (int i : indexes) {
        outputs.add("\t" + addOrdinal(i, this.tasks.remove(i)));
      }
      this.save();
      Collections.reverse(outputs);
      outputs.add(0, "deleted:");
      return outputs.toArray(new String[0]);
    }
  }

  private void consume(String[] argument, Consumer<Integer> consumer) {
    try {
      String[] inds = argument[0].split("\\s");
      for (String s : inds) {
        int ind = Integer.parseInt(s) - 1;
        consumer.accept(ind);
      }
    } catch (NumberFormatException | IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid index.");
    }
  }

  /**
   * Performs a search for partial matches on task descriptions.
   *
   * @param argument the search term.
   * @return a string representation of the tasks that matched the search term.
   */
  public String[] find(String[] argument) {
    String keyword = argument[0].toLowerCase();
    List<Integer> matchIndexes = IntStream.range(0, this.tasks.size())
        .parallel()
        .filter(i -> this.tasks.get(i).getDesc().toLowerCase().contains(keyword))
        .boxed().toList();
    if (matchIndexes.size() < 1) {
      return new String[]{"No matches found."};
    } else {
      List<String> outputs = matchIndexes.stream()
          .map(i -> addOrdinal(i, this.tasks.get(i)))
          .collect(Collectors.toList());
      outputs.add(0, matchIndexes.size()
          + String.format(" match%s found:", matchIndexes.size() > 1 ? "es" : ""));
      return outputs.toArray(String[]::new);
    }
  }

  private void save() {
    storage.save(this.tasks);
  }

  /**
   * Returns a string representation of the task with the given index.
   * Used for printing in ordered lists.
   *
   * @param index the index of the task.
   * @param task  the task.
   * @return a string representation of the task with the given index.
   */
  public String addOrdinal(int index, Task task) {
    return (index + 1) + ". " + task;
  }
}