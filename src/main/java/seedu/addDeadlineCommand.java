package seedu;

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
