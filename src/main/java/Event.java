import java.time.LocalDate;

public class Event extends Task {
  protected LocalDate from;
  protected LocalDate to;

  public Event(String title, LocalDate from, LocalDate to) {
    super(title);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return String.format(
      "[E][%c] %s (from: %s to: %s)",
      getStatusIcon(), title, formatDate(from), formatDate(to)
    );
  }
}
