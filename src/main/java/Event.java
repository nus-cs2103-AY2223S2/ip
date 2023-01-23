import java.time.LocalDate;

public class Event extends Task {
  protected LocalDate startTime;
  protected LocalDate endTime;

  public Event(String description, String startTime, String endTime) {
    super(description);
    this.startTime = LocalDate.parse(startTime);
    this.endTime = LocalDate.parse(endTime);
  }

  @Override
  public String toString() {
    return String.format("[E]%s (from: %s to: %s)", super.toString(),
        this.startTime, this.endTime);
  }
}
