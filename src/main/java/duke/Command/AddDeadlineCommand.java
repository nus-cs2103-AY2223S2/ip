package duke.Command;

import duke.Exceptions.DukeException;
import duke.Tasks.Deadline;
import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Deadline newDeadline = new Deadline(this.description, this.by);
        tasks.addingTask(newDeadline);
        ui.showTaskAdded(newDeadline, tasks);
        return newDeadline.toString();
    }
}
