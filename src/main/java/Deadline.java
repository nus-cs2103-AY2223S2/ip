import java.time.LocalDate;

public class Deadline extends Task {
  protected LocalDate by;

  public Deadline(String title, LocalDate by) {
    super(title);
    this.by = by;
  }

  @Override
  public String toString() {
    return String.format(
      "[D][%c] %s (by: %s)",
      getStatusIcon(), title, formatDate(by)
    );
  }
}
