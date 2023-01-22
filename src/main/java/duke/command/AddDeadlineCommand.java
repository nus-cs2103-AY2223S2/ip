package duke.command;

import duke.task.Deadline;

public class AddDeadlineCommand extends Command {
    private Deadline deadline;

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
