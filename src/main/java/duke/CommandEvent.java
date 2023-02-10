package duke;

public class CommandEvent extends Command {

    private final TaskList taskList;
    private final String taskDetails;
    public CommandEvent(TaskList taskList, String taskDetails) {
        this.taskList = taskList;
        this.taskDetails = taskDetails;
    }

    @Override
    public String execute() throws DukeException {
        return Ui.getAddTaskConfirmationWithAttitude(this.taskList.addEventTask(this.taskDetails));
    }
}
