public class Event extends Task {
  private String from;
  private String to;

  /**
   * Constructor for an Event task
   * 
   * @param name - The name of the event
   * @param from - The date the event begins on
   * @param to   - The date the event ends on
   */
  Event(String name, String from, String to) {
    super(name);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + from +
        " to: " + to + ")";
  }
}
