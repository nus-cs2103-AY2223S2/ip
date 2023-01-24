import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddDeadline implements Command{
    private String deadlineString;
    private String name;
    public AddDeadline(String name, String deadlineString) {
        this.name = name;
        this.deadlineString = deadlineString;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        try {
            LocalDateTime deadline = DateTime.parseDateTimeString(this.deadlineString);
            Deadline dl = new Deadline(this.name, deadline);
            tl.addTask(dl);
            storage.add(storage.getStorageTaskString(dl));
            ui.showAddDeadlineResult(dl.toString(), tl.getSize());
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for deadline, " +
                    "please follow deadline name /by datetime(yyyy-mm-dd HH:mm)");
        }
    }
}
