package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Exit extends Command {
    public Exit() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
        return ui.showBye();
    };

    @Override
    public boolean isExit() {
        return true;
    }
}
