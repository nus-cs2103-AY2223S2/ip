package duke.task;

import java.time.LocalDate;

public class Event extends Task {
  private final LocalDate from;
  private final LocalDate to;

  public Event(String desc, String from, String to) {
    super(desc);
    this.from = LocalDate.parse(from);
    this.to = LocalDate.parse(to);
  }

  @Override
  public String toString() {
    return String.format("[E]%s (from: %s) (to: %s)",
      super.toString(),
      from.format(format),
      to.format(format));
  }
}
