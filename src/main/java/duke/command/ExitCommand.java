package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The type Exit command.
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            //load tasks into storage file
            storage.save(tasks.getTasksToSave());
        } catch (IOException e) {
            throw new DukeException("unable to save tasks ^.~)");
        }
        //print exit message
        return ui.sayGoodbye();
    }
}
