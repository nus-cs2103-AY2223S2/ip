public class Event extends Task {
  private final String start;
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
