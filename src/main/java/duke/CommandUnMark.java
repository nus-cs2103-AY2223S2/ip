package duke;

public class CommandUnMark extends Command {

    private final TaskList taskList;
    private final String index;

    public CommandUnMark(TaskList taskList, String index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public String execute() throws DukeException {
        return Ui.getUnMarkMessageWithAttitude(this.taskList.unMarkTask(this.index));
    }
}
