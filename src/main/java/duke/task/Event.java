package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task with an associated start and end time
 */
public class Event extends Task {
  /**
   * String representation of the start time
   */
  private final LocalDateTime start;

  /**
   * String representation of the end time
   */
  private final LocalDateTime end;

  public Event(String task, LocalDateTime start, LocalDateTime end) {
    super(task);
    this.start = start;
    this.end = end;
  }

  @Override
  public String getType() { return "E"; }

  @Override
  public String toString() {
    return String.format("%s (from: %s to: %s)",
     super.toString(), 
     start.format(DateTimeFormatter.ofPattern("dd/MM kk:hh")),
     end.format(DateTimeFormatter.ofPattern("dd/MM kk:hh"))
    );
  }
}
