package duke.commands;

import duke.database.Database;
import duke.exception.TaskNumberNotFoundException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command{

    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws TaskNumberNotFoundException {
        Task task = taskList.getTask(this.taskNumber);
        task.complete();
        ui.response(FRAME +
                "     Nice! I've marked this task as done:\n" +
                "       [X] " + task.details + "\n" +
                FRAME);
    }

}
