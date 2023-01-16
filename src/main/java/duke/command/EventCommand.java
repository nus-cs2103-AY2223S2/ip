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
     * @throws DukeException Indicates missing start or end date/time or description in input.
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
     * @throws DukeException Indicates missing start or end date/time or description in input.
     */
    @Override
    protected Task createTask(String input) throws DukeException {
        input = input.replaceFirst("event ", "").trim();

        if (input.startsWith("/from ")) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        String[] args = input.split(" /from ", 2);
        if (args.length != 2 || args[1].trim().startsWith("/to ")) {
            throw new DukeException("The input of an event must include a start date/time.");
        }

        String description = args[0].trim();
        args[1] = args[1].trim();

        String[] startAndEnd = args[1].split(" /to ", 2);
        if (startAndEnd.length != 2) {
            throw new DukeException("The input of an event must include an end date/time.");
        }

        String start = startAndEnd[0].trim();
        String end = startAndEnd[1].trim();

        return new Event(false, description, start, end);
    }
}
