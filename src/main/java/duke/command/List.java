package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class List extends Command {
    public List() {
        super();
    }
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showList(tasks);
    };
}
