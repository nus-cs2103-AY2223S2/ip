package duke.commands;

import duke.database.Database;
import duke.exception.TaskNumberNotFoundException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    int taskNumber;


    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws TaskNumberNotFoundException {
        String taskDescription = taskList.getTask(taskNumber).status();
        taskList.deleteTask(taskNumber);
        ui.response(FRAME +
                " Noted. I've removed this task:\n" +
                "       " + taskDescription +
                "     Now you have " + taskList.length() + " tasks in the list." + "\n"
                + FRAME);
    }
}
