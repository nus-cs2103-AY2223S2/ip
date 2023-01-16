/**
 * This represents a Task
 */
public class Task {
  private String name;
  private boolean isDone;

  /**
   * Constructor for a Task
   * 
   * @param name - The name of a task
   */
  Task(String name) {
    this.name = name;
    this.isDone = false;
  }

  /**
   * Marks the task as done
   * 
   * @return true when marked done
   */
  public boolean markDone() {
    return this.isDone = true;
  }

  /**
   * Unmarks the task, making the task not done.
   * 
   * @return true when successfully unmarked as done
   */
  public boolean unmarkDone() {
    return !(this.isDone = false);
  }

  /**
   * Gets the done status of the task
   * 
   * @return true if done, false is not done
   */
  public boolean isMarkedDone() {
    return this.isDone;
  }

  @Override
  public String toString() {
    if (isDone) {
      return "[X] " + this.name;
    } else {
      return "[ ] " + this.name;
    }
  }

}
