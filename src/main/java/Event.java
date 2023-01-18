public class Event extends Task {
  public Event(String task) {
    super(task);
  }

  @Override
  public String toString() {
    return String.format(
      "[E][%c] %s",
      getStatusIcon(), title
    );
  }
}
