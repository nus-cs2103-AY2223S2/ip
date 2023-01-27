package duke.command;

import java.time.format.DateTimeParseException;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.util.Ui;

public class DeadlineCommand extends Command {
    private String command;
    private TaskList taskList;
    private Ui ui;

    public DeadlineCommand(String command, TaskList taskList, Ui ui) {
        this.command = command;
        this.taskList = taskList;
        this.ui = ui;
    }
    
    /*
     * adds deadline base on the string command
     * deadline requires taskName and EndDate
     */
    @Override
    public boolean execute() throws DukeException {
        String taskName = getTaskName("deadline", command);
        String endDate = getEndDate("deadline", command);

        try {
            Deadline deadline = new Deadline(taskName, endDate);
            taskList.add(deadline);
            ui.printAddedTask(deadline, taskList);
        } catch (DateTimeParseException e) {
            ui.showInvalidTimeError();
        }
        return false;
    }
}
