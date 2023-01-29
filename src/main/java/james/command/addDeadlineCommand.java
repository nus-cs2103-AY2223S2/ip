package james.command;

import james.JamesException;
import james.task.Deadline;

public class addDeadlineCommand extends Command {
    private Deadline deadline;

    public addDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute() throws JamesException {
        taskList.addToTaskList(deadline);
        ui.addTask(deadline, taskList.getSize());
    }

}
