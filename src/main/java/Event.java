import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
  private static final DateTimeFormatter RECEIVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy kkmm");
  private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-uuu,EEE,hh:mma");
  
  protected LocalDateTime from;
  protected LocalDateTime to;

  public Event(String name, String from, String to) throws DukeException {
    super(name);
    try {
      this.from = LocalDateTime.parse(from, RECEIVE_FORMAT);
      this.to = LocalDateTime.parse(to, RECEIVE_FORMAT);
    } catch (DateTimeParseException e) {
      throw new DukeException("Could not parse time");
    }
  }

  @Override
  public String toString() {
    return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(PRINT_FORMAT), to.format(PRINT_FORMAT));
  }
}
