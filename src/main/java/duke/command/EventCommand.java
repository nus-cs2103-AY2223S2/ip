package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

import java.util.List;

/**
 * Represents an add event task command.
 */
public class EventCommand extends AddCommand {
    /**
     * {@inheritDoc}
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return {@inheritDoc}
     * @throws DukeException Indicates missing ' /from ' and/or ' /to ' in input.
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
     * @throws DukeException Indicates missing ' /from ' and/or ' /to ' in input.
     */
    @Override
    protected Task createTask(String input) throws DukeException {
        input = input.replaceFirst("event ", "");

        String[] args = input.split(" /from ", 2);
        if (args.length != 2) {
            throw new DukeException("The input of an event must include a ' /from '.");
        }

        String description = args[0];

        String[] startAndEnd = args[1].split(" /to ", 2);
        if (startAndEnd.length != 2) {
            throw new DukeException("The input of an event must include a ' /to '.");
        }

        String start = startAndEnd[0];
        String end = startAndEnd[1];

        return new Event(false, description, start, end);
    }
}
