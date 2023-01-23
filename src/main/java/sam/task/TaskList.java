package sam.task;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
  /** The internal list of tasks */
  private ArrayList<Task> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
  }

  /**
   * Returns the task with the specified id.
   * If the id is invalid, returns null.
   * 
   * @param id The id of the task to return.
   * @return The Task object with the specified id.
   */
  public Task getTask(int id) {
    if (isValidId(id)) {
      return tasks.get(id - 1);
    }
    return null;
  }

  /**
   * Appends the specified task to the list.
   * 
   * @param task The task to be appended.
   * @return {@code true}
   */
  public boolean addTask(Task task) {
    return tasks.add(task);
  }

  /**
   * Removes the task with the specified id from the list and return it.
   * If the id is invalid, returns null.
   * 
   * @param id The id of the task to remove.
   * @return The task that was removed from the list.
   */
  public Task removeTask(int id) {
    if (isValidId(id)) {
      return tasks.remove(id - 1);
    }
    return null;
  }

  /**
   * Marks the task with the specified id as done or not done.
   * 
   * @param id The id of the task to mark.
   * @param done Indicates whether the task is done or not.
   * @return {@code true} if successful.
   */
  public boolean markTask(int id, boolean done) {
    if (isValidId(id)) {
      getTask(id).mark(done);
      return true;
    }
    return false;
  }

  /**
   * Returns the number of tasks in the list.
   * 
   * @return The number of tasks in the list.
   */
  public int count() {
    return tasks.size();
  }

  /**
   * Generates a numbered string array of the list.
   * 
   * @return A string array of the list.
   */
  public String[] generateList() {
    String[] list = new String[count()];
    for (int i = 0; i < count(); i++) {
      Task t = tasks.get(i);
      list[i] = String.format("%d: %s", i + 1, t);
    }
    return list;
  }

  private boolean isValidId(int id) {
    return !(id <= 0 || id > count());
  }
}
