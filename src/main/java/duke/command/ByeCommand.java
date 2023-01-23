package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A representation of the "bye" command. */
public class ByeCommand extends Command {

    private final String NAME = "bye";

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ui.addToMessage("Bye. Hope to see you again soon!", false);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
