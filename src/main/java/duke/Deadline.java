import java.time.LocalDate;

public class Deadline extends Task {
  protected LocalDate by;

  public Deadline(String description, String by) {
    super(description);
    this.by = LocalDate.parse(by);
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s %s %s)", super.toString(), this.by.getMonth(),
        this.by.getDayOfMonth(), this.by.getYear());
  }

  @Override
  public String fileOutput() {
    return String.format("D|%d|%s|%s", this.isDone ? 1 : 0,
        this.description, this.by);
  }
}
