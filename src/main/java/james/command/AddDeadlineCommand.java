package james.command;

import james.JamesException;
import james.task.Deadline;

/**
 * The command to add a deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    /** Starting time in d/MM/yyyy HHmm format */
    private Deadline deadline;

    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute() throws JamesException {
        taskList.addToTaskList(deadline);
        ui.addTask(deadline, taskList.getSize());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof addDeadlineCommand) {
            return deadline.equals(((addDeadlineCommand) obj).deadline);
        }
        return false;
    }

}
