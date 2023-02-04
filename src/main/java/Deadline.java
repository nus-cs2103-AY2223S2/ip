import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task1{
  public LocalDateTime time;
  public Deadline(String string, String time) {
    super(string);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    this.time = LocalDateTime.parse(time, formatter);
  }
  @Override
  public void printType() {
    System.out.print("[D]");
  }

  @Override
  public void printTime() {
    System.out.println(" (by: " + time + ")");
  }

  @Override
  public String toString() {
    return "deadline";
  }
}
