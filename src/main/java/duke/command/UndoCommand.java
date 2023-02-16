package duke.command;

import duke.exception.DukeException;
import duke.storage.History;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.IoHandler;

/**
 * Represents the undo function of Duke.
 */
public class UndoCommand extends Command {
    private static History history = History.getInstance();

    /** Constructs the priority command. */
    public UndoCommand() {}

    @Override
    public String execute(TaskList tasks, IoHandler ui, Storage store) throws DukeException {
        ui.throwAwayInput();
        tasks.setState(history.undoTaskState());
        return ui.undoOutput();
    }

}
