package command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class CommandDelete extends Command {

    private final TaskList taskList;
    private final String index;

    public CommandDelete(TaskList taskList, String index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public String execute() throws DukeException {
        return Ui.getDeleteMessageWithAttitude(this.taskList.deleteTask(this.index));
    }
}
