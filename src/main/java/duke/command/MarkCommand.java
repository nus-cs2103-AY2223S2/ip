package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

public class MarkCommand extends Command {
    int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            if (taskList.getTask(index).isDone()) {
                throw new DukeException("marked");
            } else {
                taskList.getTask(index).mark();
                ui.printMark(taskList.getTask(index));
            }
        } catch (DukeException e) {
            e.MarkedException();
        }
    }
}
