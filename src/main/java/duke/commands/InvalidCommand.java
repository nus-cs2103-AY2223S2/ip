package duke.commands;

import duke.utils.DukeIo;
import duke.tasks.TaskList;
import duke.exception.InvalidCommandException;
/**
 * A wrapper command class when there are Invalid Commands.
 */
public class InvalidCommand extends Command {
    private final InvalidCommandException ERR;

    /**
     * Public constructor for InvalidCommand
     * @param err
     */
    public InvalidCommand(InvalidCommandException err) {
        this.ERR = err;
    }

    @Override
    public String exec(DukeIo dukeIo, TaskList tasks) {
        return dukeIo.showError(ERR);
    }
}
