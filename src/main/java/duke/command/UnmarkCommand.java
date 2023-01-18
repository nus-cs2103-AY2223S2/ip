package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents an unmark command for marking a task as not done.
 */
public class UnmarkCommand extends SetDoneCommand {
    /**
     * Set the done status of the task specified in the input to false and return an acknowledgement message.
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
        return "OK, I've marked this task as not done yet:";
    }
}
