package command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class CommandDeadline extends Command {

    private final TaskList taskList;
    private final String taskDetails;

    public CommandDeadline(TaskList taskList, String taskDetails) {
        this.taskList = taskList;
        this.taskDetails = taskDetails;
    }

    @Override
    public String execute() throws DukeException {
        return Ui.getAddTaskConfirmationWithAttitude(this.taskList.addDeadlineTask(this.taskDetails));
    }
}
