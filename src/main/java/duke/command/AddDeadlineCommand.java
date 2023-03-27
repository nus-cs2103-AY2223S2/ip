package duke.command;

import duke.task.Deadline;

/**
 * Adds a deadline to the task list.
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
    public String execute() {
        assert deadline != null : "Deadline cannot be null";
        assert taskList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        taskList.addTask(deadline);
        return ui.printTaskAdded(deadline, taskList.getSize());
    }

    @Override
    public String toString() {
        return String.format("AddDeadlineCommand{deadline=%s}", deadline);
    }

}
