package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.TaskList;

/**
 * The type Unknown command.
 */
public class UnknownCommand extends Command{

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

}
