package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.Collections;


public class SortCommand extends Command {

    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        tl.sortTaskList(storage);
        return "Results sorted!\n" + ui.showGetAllTaskResult(tl);
    }
}
