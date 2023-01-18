public class Event extends Task {
  public Event(String title) {
    super(title);
  }

  @Override
  public String toString() {
    return String.format(
      "[E][%c] %s",
      getStatusIcon(), title
    );
  }
}
