package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response;
        try {
            response = taskList.unmark(index);
            storage.saveState(taskList);
            return response;
        } catch (DukeException e) {
            response = e.toString();
            return response;
        }
    }
}
