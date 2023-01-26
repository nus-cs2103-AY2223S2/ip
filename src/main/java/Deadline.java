import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

  protected LocalDate by;

  public Deadline(String description, String by) {
    super(description);
    try {
      this.by = LocalDate.parse(by);
    } catch (DateTimeParseException e) {
      throw DukeException.DATETIME_FORMAT;
    } catch (Exception e) {
      throw new DukeException("Unknown error occurred when parsing datetime.");
    }
  }

  @Override
  public String toString() {
    String formattedBy = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    return String.format("[D]%s (by: %s)", super.toString(), formattedBy);
  }
}
