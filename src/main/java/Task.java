import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract class Task {
  protected String title;
  protected boolean isDone;

  public Task(String title) {
    this.title = title;
    this.isDone = false;
  }

  public void mark(boolean done) {
    this.isDone = done;
  }

  protected char getStatusIcon() {
    return isDone ? 'X' : ' ';
  }

  protected String formatDate(LocalDate date) {
    return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
  }
}
