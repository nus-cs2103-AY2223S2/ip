package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

public class UnmarkCommand extends Command {
    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            if (taskList.getTask(index).isDone()) {
                taskList.getTask(index).unmark();
                ui.printUnmark(taskList.getTask(index));
            } else {
                throw new DukeException("unmarked");
            }
        } catch (DukeException e) {
            e.MarkedException();
        }
    }
}