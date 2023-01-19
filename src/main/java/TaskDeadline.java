/**
 * The class representing a TaskDeadline task.
 *
 * @author JamesLiuZX
 * AY2223-S2 CS2103T
 */

public class TaskDeadline extends Task {
  protected String time;
  /**
   * Given a task and a deadline, constructs a TaskDeadline task.
   *
   * @param task     The task to be completed.
   * @param time     The task's deadline.
   *
   * @author JamesLiuZX
   * AY2223-S2 CS2103T
   */

  public TaskDeadline(String task, String time) {
    super(task);
    this.time = time;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString()
            + String.format(" (%s) ", time);
  }
}