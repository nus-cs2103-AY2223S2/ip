/**
 * A task with an associated start and end time
 */
public class Event extends Task {
  /**
   * String representation of the start time
   */
  private final String start;

  /**
   * String representation of the end time
   */
  private final String end;

  public Event(String task, String start, String end) {
    super(task);
    this.start = start;
    this.end = end;
  }

  @Override
  public String getType() { return "E"; }

  @Override
  public String toString() {
    return String.format("%s (from: %s to: %s)", super.toString(), start, end);
  }
}
