package sam.task;
import java.time.LocalDate;

public class Deadline extends Task {
  protected LocalDate by;

  public Deadline(String title, LocalDate by) {
    this(title, by, false);
  }

  public Deadline(String title, LocalDate by, boolean isDone) {
    super(title, isDone);
    this.by = by;
  }

  @Override
  public String toSaveFormat() {
    return String.format(
      "D | %d | %s | %s",
      getStatusNo() , title, formatDateSave(by)
    );
  }

  @Override
  public String toString() {
    return String.format(
      "[D][%c] %s (by: %s)",
      getStatusIcon(), title, formatDateDisplay(by)
    );
  }
}
