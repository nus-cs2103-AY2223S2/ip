package duke;


public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showList(taskList);
    }
}
