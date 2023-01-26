package Duke.Command;
import Duke.DateTime.DateTime;
import Duke.DukeException.DukeException;
import Duke.Storage.Storage;
import Duke.Task.Deadline;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Class in charge of handling the case of adding Deadline Task
 */
public class AddDeadline implements Command{
    private String deadlineString;
    private String name;
    public AddDeadline(String name, String deadlineString) {
        this.name = name;
        this.deadlineString = deadlineString;
    }

    /**
     * Adds the Task to TaskList and storage, and output result using Ui
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     */
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
