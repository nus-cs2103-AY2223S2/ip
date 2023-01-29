package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class Command {

    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        list.add(new Task(""));
        store.save(list);
    }

    public boolean isExit() {
        return false;
    }
}
