package duke;

public class CommandDeadline extends Command {

    private final TaskList taskList;
    private final String taskDetails;

    public CommandDeadline(TaskList taskList, String taskDetails) {
        this.taskList = taskList;
        this.taskDetails = taskDetails;
    }

    @Override
    public String execute() throws DukeException {
        return Ui.getAddTaskConfirmationWithAttitude(this.taskList.addDeadlineTask(this.taskDetails));
    }
}
