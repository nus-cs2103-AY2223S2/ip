package duke.commands;

import duke.exception.InvalidCommandException;
import duke.tasks.TaskList;
import duke.utils.DukeIo;
/**
 * A wrapper command class when there are Invalid Commands.
 */
public class InvalidCommand extends Command {
    private final InvalidCommandException err;

    /**
     * Public constructor for InvalidCommand
     * @param err InvalidCommandException caught
     */
    public InvalidCommand(InvalidCommandException err) {
        this.err = err;
    }

    @Override
    public String exec(DukeIo dukeIo, TaskList tasks) {
        return dukeIo.showError(err);
    }
}
