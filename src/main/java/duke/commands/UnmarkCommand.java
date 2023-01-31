package duke.commands;

import duke.database.Database;
import duke.exception.TaskNumberNotFoundException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws TaskNumberNotFoundException {
        Task task = taskList.getTask(this.taskNumber);
        task.incomplete();
        ui.response(FRAME +
                "     OK, I've marked this task as not done yet:\n" +
                "       [ ] " + task.details + "\n" +
                FRAME);
    }
}
