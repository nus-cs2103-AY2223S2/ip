package rick.command;

import java.time.format.DateTimeParseException;

import rick.RickUtils;
import rick.TaskList;
import rick.Ui;
import rick.exceptions.RickEmptyTaskException;
import rick.exceptions.RickInvalidDateException;
import rick.exceptions.TaskListFullException;
import rick.task.EventTask;

/**
 * Represents the command that creates an Event task.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class EventCommand extends Command {
    private final String slug;

    /**
     * Constructs an Event task when executed, with the provided slug.
     * Slugs are of this format:
     * "{task} /from d/M/yy HHmm /to d/M/yy HHmm"
     *
     * @param slug The provided slug.
     */
    public EventCommand(String slug) {
        this.slug = slug;
    }

    /**
     * Constructs an empty command that raises an Empty Task Exception
     */
    public EventCommand() {
        this.slug = "";
    }

    /**
     * Executes this command with the given TaskList and UI output, and
     * returns the UI to output to the user.
     *
     * @param ts The TaskList instance.
     * @param ui The UI output.
     */
    @Override
    public String execute(TaskList ts, Ui ui) {
        if (this.slug.length() == 0) {
            return ui.error(
                    new RickEmptyTaskException(
                            RickEmptyTaskException.TaskType.TYPE_Event
                    )
            );
        }

        String[] tokens = slug.split(" /from ", 2);
        if (tokens.length != 2) {
            return ui.guide(
                    "Usage: event {task} /from {start} /to {end}"
            );
        }
        String[] dates = tokens[1].split(" /to ", 2);
        if (dates.length != 2) {
            return ui.guide(
                    "Usage: event {task} /from {start} /to {end}"
            );
        }

        try {
            EventTask event = new EventTask(tokens[0],
                    RickUtils.parseDateTime(dates[0]),
                    RickUtils.parseDateTime(dates[1]));
            return ts.add(event);
        } catch (DateTimeParseException e) {
            return ui.error(
                    new RickInvalidDateException()
            );
        } catch (TaskListFullException e) {
            return ui.error(e);
        }
    }
}
