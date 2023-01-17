package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;

/**
 * Represents an unmark command for marking a task as not done.
 */
public class UnmarkCommand extends SetDoneCommand {
    /**
     * Returns a CommandResponse object containing an acknowledgement message and an updated task list with the
     * specified task's done status set to false.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return CommandResponse object containing an acknowledgement message and an updated task list with the specified
     * task's done status set to false.
     * @throws DukeException {@inheritDoc}
     */
    @Override
    public CommandResponse run(String input, List<Task> tasks) throws DukeException {
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
