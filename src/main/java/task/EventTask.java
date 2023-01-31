package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
  private final LocalDate startTime;
  private final LocalDate endTime;

  public EventTask(String name, LocalDate startTime, LocalDate endTime) {
    super(name);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public EventTask(String name, LocalDate startTime, LocalDate endTime,Boolean isDone) {
    super(name, isDone);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public String toString() {
    return "[E] " + super.toString() + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
  }
}
