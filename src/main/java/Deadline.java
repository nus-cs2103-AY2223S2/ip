/**
 * A task with an associated deadline
 */
public class Deadline extends Task {
  /**
   * String representation of the deadline
   */
  private final String time;

  public Deadline(String task, String time) {
    super(task);
    this.time = time;
  }

  @Override
  public String getType() { return "D"; }

  @Override
  public String toString() {
    return String.format("%s (by: %s)", super.toString(), time);
  }
}
