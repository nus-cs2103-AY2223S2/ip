package duke.command;

import duke.datetime.DateTime;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command{
    private String deadlineString;
    private String name;
    public AddDeadlineCommand(String name, String deadlineString) {
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
