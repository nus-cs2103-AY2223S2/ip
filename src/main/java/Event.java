import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task1{
  public LocalDateTime startTime;
  public LocalDateTime endTime;
  public String strStartTime;
  public String strEndTime;
  public Event(String string, String startTime, String endTime) {
    super(string);
    this.strStartTime = startTime;
    this.strEndTime = endTime;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    this.startTime = LocalDateTime.parse(startTime, formatter);
    this.endTime = LocalDateTime.parse(endTime, formatter);
  }
  @Override
  public void printType() {
    System.out.print("[E]");
  }
  @Override
  public void printTime() {
    System.out.println(" (from: " + startTime
        + " to: " + endTime + ")");
  }

  @Override
  public String toString() {
    return "event";
  }
}
