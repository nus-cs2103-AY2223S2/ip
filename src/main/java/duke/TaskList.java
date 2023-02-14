package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Tasklist object that allows abstraction to track and edit the list of tasks.
 */
public class TaskList {
  /**
   * The list of tasks.
   */
  private final ArrayList<Task> tasks;

  /**
   * Constructor for a new empty list of tasks.
   */
  public TaskList() { tasks = new ArrayList<>(); }

  /**
   * Constructor for a list of tasks with those provided.
   *
   * @param tasks The list of tasks.
   */
  public TaskList(ArrayList<Task> tasks) { this.tasks = tasks; }

  /**
   * Returns the list of tasks.
   *
   * @return The list of tasks
   */
  public ArrayList<Task> getTasks() { return tasks; }

  /**
   * Adds the provided task into the list of tasks.
   *
   * @param task New task to be added into the list
   */
  public void addTask(Task task) { tasks.add(task); }

  /**
   * Returns the number of tasks in the list of tasks.
   *
   * @return The number of tasks in the list
   */
  public int size() { return tasks.size(); }
}
