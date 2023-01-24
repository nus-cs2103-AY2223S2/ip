import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final TaskCategory CATEGORY = TaskCategory.DEADLINE;
    static final String NAME_KEY = "name";
    static final String COMPLETED_KEY = "completed";
    static final String BY_KEY = "by";
    private static final DateTimeFormatter RECEIVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy kkmm");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-uuu,EEE,hh:mma");
    protected LocalDateTime by;

    public Deadline(String name, boolean completed, String by) throws DukeException {
        super(name, completed);
        try {
          this.by = LocalDateTime.parse(by, RECEIVE_FORMAT);
        } catch(DateTimeParseException e) {
          throw new DukeException("Could not parse time");
        }
    }

    @Override
    public String serialize() {
        TaskSerializer ts = new TaskSerializer(CATEGORY);
        ts.add(NAME_KEY, name);
        ts.add(COMPLETED_KEY, completed);
        ts.add(BY_KEY, by.format(RECEIVE_FORMAT));
        return ts.toString();
    }
    @Override
    public String toString() {
      return String.format("[D]%s (by: %s)", super.toString(), by.format(PRINT_FORMAT));
    }
}
