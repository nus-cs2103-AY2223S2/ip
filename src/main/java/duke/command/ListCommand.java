package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand(String textCmd) {
        super(textCmd);
    }

    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        return ui.showList(taskList);
    }
}
