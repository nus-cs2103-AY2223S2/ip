import java.time.temporal.TemporalAccessor;

/**
 * A task with an associated deadline
 */
public class Deadline extends Task {
  /**
   * String representation of the deadline
   */
  private final TemporalAccessor deadline;

  public Deadline(String task, TemporalAccessor time) {
    super(task);
    this.deadline = time;
  }

  @Override
  public String getType() { return "D"; }

  @Override
  public String toString() {
    return String.format("%s (by: %s)", 
      super.toString(), 
      Utils.timeToString(deadline)
    );
  }
}
