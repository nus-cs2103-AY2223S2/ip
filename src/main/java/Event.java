public class Event extends Task {
  protected String from;
  protected String to;

  public Event(String title, String from, String to) {
    this(title, from, to, false);
  }

  public Event(String title, String from, String to, boolean isDone) {
    super(title, isDone);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toSaveFormat() {
    return String.format(
      "E | %d | %s | %s | %s",
      getStatusNo() , title, from, to
    );
  }

  @Override
  public String toString() {
    return String.format(
      "[E][%c] %s (from: %s to: %s)",
      getStatusIcon(), title, from, to
    );
  }
}
