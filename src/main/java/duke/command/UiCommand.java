package duke.command;
import duke.*;
public class UiCommand extends Command { 
    public UiCommand() {
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showList(taskList);
    }
}
