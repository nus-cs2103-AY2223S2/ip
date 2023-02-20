package duke.command;
import java.util.List;

import duke.*;
import duke.task.*;
public class UndoCommand extends Command {

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.undo();
    }
}

