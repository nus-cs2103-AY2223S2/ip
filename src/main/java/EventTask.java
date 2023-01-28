public class EventTask extends Task {
  private String startTime;
  private String endTime;

  public EventTask(String name, String startTime, String endTime) {
    super(name);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public EventTask(String name, String startTime, String endTime,Boolean isDone) {
    super(name, isDone);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public String toString() {
    return "[E] " + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
  }
}
