package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents an unmark command for marking a task as not done.
 */
public class UnmarkCommand extends SetDoneCommand {
    /**
     * Sets the done status of the task specified in the input to false and returns an acknowledgement message.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return {@inheritDoc}
     * @throws DukeException {@inheritDoc}
     */
    @Override
    public String run(String input, TaskList tasks) throws DukeException {
        return super.run(input, tasks);
    }

    @Override
    protected String getCommand() {
        return "unmark";
    }

    @Override
    protected boolean shouldBeDone() {
        return false;
    }

    @Override
    protected String getSuccessMessagePrefix() {
        return "YOU DARE LIE TO ME?!";
    }
}
