package duke.commands;

import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;

/**
 * The class for the Event command which extends Command class.
 */
public class EventCommand extends Command {
    private String input;

    /**
     * EventCommand constructor.
     *
     * @param input The user's input.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int indexFrom = input.indexOf("/");
            int indexTo = input.lastIndexOf("/");

            if ((indexFrom + 6 > indexTo - 1)
                    || (indexTo + 4 > input.length())
                    || (indexFrom - 1 < 6)) {
                throw new DukeException("OOPS!!! Event must be in the format\n"
                        + "event <description> /from <date> /to <date>");
            }
            if (!(input.substring(indexFrom, indexFrom + 6).equals("/from ")
                    && input.substring(indexTo, indexTo + 4).equals("/to "))) {
                throw new DukeException("OOPS!!! Deadline should be followed by a /from and a /to command.");
            }
            Event e = new Event(input.substring(6, indexFrom - 1),
                    input.substring(indexFrom + 6, indexTo - 1),
                    input.substring(indexTo + 4, input.length()));
            tasks.add(e);
            storage.saveTaskList(tasks);
            return "Got it. I've added this task:\n"
                    + "  " + e
                    + "Now you have "
                    + tasks.size()
                    + " tasks in the list.";
        } catch (DukeException de) {
            return de.getMessage();
        } catch (DateTimeParseException date_time_e) {
            return "Event must have start and end dates of the following format:\n"
                    + "1. yyyy-MM-dd\n"
                    + "2. yyyy-MM-dd HHmm";
        }
    }
}
