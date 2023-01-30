package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.datetime.DateTime;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class in charge of handling the case of adding Deadline Task
 */
public class AddDeadlineCommand extends Command {
    private String deadlineString;
    private String name;

    /**
     * Constructor to create a AddDeadlineCommand
     *
     * @param name deadline name
     * @param deadlineString Task's deadline in string form
     */
    public AddDeadlineCommand(String name, String deadlineString) {
        this.name = name;
        this.deadlineString = deadlineString;
    }

    /**
     * Adds the Task to TaskList and storage, and output result string
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     * @return output string
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        try {
            LocalDateTime deadline = DateTime.parseDateTimeString(this.deadlineString);
            Deadline dl = new Deadline(this.name, deadline);
            tl.addTask(dl);
            storage.add(storage.getStorageTaskString(dl));
            return ui.showAddDeadlineResult(dl.toString(), tl.getSize());
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for deadline, "
                    + "please follow deadline name /by datetime(yyyy-mm-dd HH:mm)");
        }
    }
}
