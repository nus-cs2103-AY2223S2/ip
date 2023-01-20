package duke.task;

/**
 * A simple task implementation with only a description and a status.
 */
public class ToDo extends Task {

  /**
   * Constructs a new ToDo with the given description.
   *
   * @param desc the description of the task
   */
  public ToDo(String desc) {
    super(desc);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
