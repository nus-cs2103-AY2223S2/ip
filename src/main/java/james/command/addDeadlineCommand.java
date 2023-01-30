package james.command;

import james.JamesException;
import james.task.Deadline;


public class addDeadlineCommand extends Command {
    /** Starting time in d/MM/yyyy HHmm format */
    private Deadline deadline;

    /**
     * The command to add a deadline to the task list.
     */
    public addDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute() throws JamesException {
        taskList.addToTaskList(deadline);
        ui.addTask(deadline, taskList.getSize());
    }

}
