package duke;

public class CommandFind extends Command {

    private final TaskList taskList;
    private final String phrase;

    public CommandFind(TaskList taskList, String phrase) {
        this.taskList = taskList;
        this.phrase = phrase;
    }

    @Override
    public String execute() throws DukeException {
        return Ui.getAllTaskFoundMessageWithAttitude(this.taskList.findTaskWith(this.phrase));
    }
}
