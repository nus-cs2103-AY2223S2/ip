package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.Storage;
import duke.task.TaskList;
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
        assert this.description != null;
        tasks.addTask(newDeadline, storage);
        return ui.showTaskAdded(newDeadline, tasks);
    }
}
