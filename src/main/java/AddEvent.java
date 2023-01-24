import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddEvent implements Command {
    private String fromString;
    private String toString;
    private String name;
    public AddEvent(String name, String fromString, String toString) {
        this.fromString = fromString;
        this.toString = toString;
        this.name = name;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        try {
            LocalDateTime from = DateTime.parseDateTimeString(this.fromString);
            LocalDateTime to = DateTime.parseDateTimeString(this.toString);
            Event ev = new Event(this.name, from, to);
            tl.addTask(ev);
            storage.add(storage.getStorageTaskString(ev));
            ui.showAddEventResult(ev.toString(), tl.getSize());
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for event, " +
                    "please follow deadline name /from datetime /to datetime(yyyy-mm-dd hh:mm)");
        }
    }
}
