package duke.command;

import duke.task.Deadline;

/**
 * The command to add a deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final Deadline deadline;

    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddDeadlineCommand) {
            return deadline.equals(((AddDeadlineCommand) obj).deadline);
        }
        return false;
    }

    @Override
    public void execute() {
        taskList.addTask(deadline);
        ui.printTaskAdded(deadline, taskList.getSize());
    }

    @Override
    public String toString() {
        return String.format("AddDeadlineCommand{deadline=%s}", deadline);
    }

}
