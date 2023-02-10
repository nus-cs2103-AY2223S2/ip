package duke;

public class CommandToDo extends Command {

    private final TaskList taskList;
    private final String taskDetails;
    public CommandToDo(TaskList taskList, String taskDetails) {
        this.taskList = taskList;
        this.taskDetails = taskDetails;
    }
    @Override
    public String execute() throws DukeException {
        return Ui.getAddTaskConfirmationWithAttitude(this.taskList.addToDoTask(this.taskDetails));
    }
}
