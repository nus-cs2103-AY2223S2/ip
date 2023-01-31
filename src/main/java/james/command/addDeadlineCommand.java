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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof addDeadlineCommand) {
            return deadline.equals(((addDeadlineCommand) obj).deadline);
        }
        return false;
    }

}
