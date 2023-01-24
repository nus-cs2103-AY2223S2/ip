import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
  private static final DateTimeFormatter RECEIVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy kkmm");
  private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-uuu,EEE,hh:mma");
  protected LocalDateTime by;

  public Deadline(String name, String by) throws DukeException {
    super(name);
    try {
      this.by = LocalDateTime.parse(by, RECEIVE_FORMAT);
    } catch(DateTimeParseException e) {
      throw new DukeException("Could not parse time");
    }
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), by.format(PRINT_FORMAT));
  }
}
