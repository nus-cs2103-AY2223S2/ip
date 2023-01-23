public class Event extends Task {
  protected String startTime;
  protected String endTime;

  public Event(String description, String startTime, String endTime) {
    super(description);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " +
        this.startTime + " to: " + this.endTime + ")";
  }

  @Override
  public String fileOutput() {
    return String.format("E|%d|%s|%s|%s", this.isDone ? 1 : 0,
        this.description, this.startTime, this.endTime);
  }
}
