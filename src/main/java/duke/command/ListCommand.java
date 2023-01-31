package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand(String textCmd) {
        super(textCmd);
    }

    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        ui.showList(taskList);
    }
}
