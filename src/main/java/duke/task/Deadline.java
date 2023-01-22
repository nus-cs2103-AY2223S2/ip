package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task with an associated deadline
 */
public class Deadline extends Task {
  /**
   * String representation of the deadline
   */
  private final LocalDateTime deadline;

  public Deadline(String task, LocalDateTime time) {
    super(task);
    this.deadline = time;
  }

  @Override
  public String getType() { return "D"; }

  @Override
  public String toString() {
    return String.format("%s (by: %s)", 
      super.toString(), 
      deadline.format(DateTimeFormatter.ofPattern("dd/MM kk:hh"))
    );
  }
}
