package sam.task;
import java.time.LocalDate;

/**
 * Represents a task with a start and end date.
 */
public class Event extends Task {
  protected LocalDate from;
  protected LocalDate to;

  public Event(String title, LocalDate from, LocalDate to) {
    this(title, from, to, false);
  }

  public Event(String title, LocalDate from, LocalDate to, boolean isDone) {
    super(title, isDone);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toSaveFormat() {
    return String.format(
      "E | %d | %s | %s | %s",
      getStatusNo() , title, formatDateSave(from), formatDateSave(to)
    );
  }

  @Override
  public String toString() {
    return String.format(
      "[E][%c] %s (from: %s to: %s)",
      getStatusIcon(), title, formatDateDisplay(from), formatDateDisplay(to)
    );
  }
}
