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
        String[] args = extractValidArgs(input);
        return createEvent(args);
    }

    private String[] extractValidArgs(String input) throws DukeException {
        String argStr = input.replaceFirst("event", "");

        String[] descriptionAndStartEnd = argStr.split(" /from ", 2);

        if (descriptionAndStartEnd.length != 2) {
            throw new DukeException("The start date/time of an event must be specified.");
        }

        descriptionAndStartEnd[0] = descriptionAndStartEnd[0].trim();
        descriptionAndStartEnd[1] = descriptionAndStartEnd[1].trim();

        if (descriptionAndStartEnd[0].isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        String[] startAndEnd = descriptionAndStartEnd[1].split(" /to ", 2);
        if (startAndEnd.length != 2) {
            throw new DukeException("The end date/time of an event must be specified.");
        }

        startAndEnd[0] = startAndEnd[0].trim();
        startAndEnd[1] = startAndEnd[1].trim();

        if (startAndEnd[0].isEmpty()) {
            throw new DukeException("The start date/time of an event must be specified.");
        }

        if (startAndEnd[1].isEmpty()) {
            throw new DukeException("The end date/time of an event must be specified.");
        }

        return new String[] {descriptionAndStartEnd[0], startAndEnd[0], startAndEnd[1]};
    }

    private Event createEvent(String[] args) {
        return new Event(false, args[0], args[1], args[2]);
    }
}
