package duke.command;
import duke.*;
public class UiCommand extends Command {
    public UiCommand() {
    }
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.showList(taskList);
    }
}
