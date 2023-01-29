package rick.command;

import rick.exceptions.RickEmptyTaskException;
import rick.exceptions.RickInvalidDateException;
import rick.exceptions.TaskListFullException;

import rick.RickUtils;
import rick.task.EventTask;
import rick.TaskList;
import rick.Ui;

import java.time.format.DateTimeParseException;

/**
 * The command that creates an Event task.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class EventCommand extends Command {
    private final String slug;

    /**
     * Given a valid slug, creates an Event task when executed.
     * Slugs are of this format:
     * "{task} /from d/M/yy HHmm /to d/M/yy HHmm"
     * @param slug The provided slug.
     */
    public EventCommand(String slug) {
        this.slug = slug;
    }

    /**
     * Formats an empty command that raises an Empty Task Exception
     */
    public EventCommand() {
        this.slug = "";
    }

    /**
     * Executes this command.
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
