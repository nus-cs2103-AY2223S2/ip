public class Event extends Task {
  protected String from;
  protected String to;

  public Event(String title, String from, String to) {
    super(title);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return String.format(
      "[E][%c] %s (from: %s to: %s)",
      getStatusIcon(), title, from, to
    );
  }
}
