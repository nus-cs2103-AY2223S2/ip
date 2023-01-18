package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a mark command for marking a task as done.
 */
public class MarkCommand extends SetDoneCommand {
    /**
     * Set the done status of the task specified in the input to true and return an acknowledgement message.
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
        return "mark";
    }

    @Override
    protected boolean shouldBeDone() {
        return true;
    }

    @Override
    protected String getSuccessMessagePrefix() {
        return "Nice! I've marked this task as done:";
    }
}
