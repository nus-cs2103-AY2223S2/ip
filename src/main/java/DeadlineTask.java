import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
  private final LocalDate date;

  public DeadlineTask(String name, LocalDate date) {
    super(name);
    this.date = date;
  }

  public DeadlineTask(String name, LocalDate date, Boolean isDone) {
    super(name, isDone);
    this.date = date;
  }

  @Override
  public String toString() {
    return "[D] " + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
  }
}
