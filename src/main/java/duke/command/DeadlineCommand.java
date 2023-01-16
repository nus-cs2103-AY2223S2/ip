package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;

import java.util.List;

/**
 * Represents an add deadline task command.
 */
public class DeadlineCommand extends AddCommand {
    /**
     * {@inheritDoc}
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return {@inheritDoc}
     * @throws DukeException Indicates missing ' /by ' in input.
     */
    @Override
    public CommandResponse run(String input, List<Task> tasks) throws DukeException {
        return super.run(input, tasks);
    }

    /**
     * {@inheritDoc}
     *
     * @param input {@inheritDoc}
     * @return {@inheritDoc}
     * @throws DukeException Indicates missing ' /by ' in input.
     */
    @Override
    protected Task createTask(String input) throws DukeException {
        input = input.replaceFirst("deadline ", "");
        String[] args = input.split(" /by ", 2);

        if (args.length != 2) {
            throw new DukeException("The input of a deadline must include a ' /by '.");
        }

        return new Deadline(false, args[0], args[1]);
    }
}
