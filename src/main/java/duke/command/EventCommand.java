package duke.command;

import duke.exceptions.DukeEmptyTaskException;
import duke.exceptions.DukeInvalidDateException;
import duke.exceptions.DukeStoreFullException;
import duke.DukeUtils;
import duke.task.EventTask;
import duke.TaskList;
import duke.Ui;

import java.time.format.DateTimeParseException;

/**
 * The command that creates an Event task.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class EventCommand extends Command {
    private final String slug;
    public EventCommand(String slug) {
        this.slug = slug;
    }

    public EventCommand() {
        this.slug = "";
    }

    @Override
    public void execute(TaskList ts, Ui ui) {
        if (this.slug.length() == 0) {
            ui.error(new DukeEmptyTaskException(DukeEmptyTaskException.TaskType.Event));
            return;
        }

        String[] tokens = slug.split(" /from ", 2);
        if (tokens.length != 2) {
            ui.guide(
                    "Usage: event {task} /from {start} /to {end}"
            );
            return;
        }
        String[] dates = tokens[1].split(" /to ", 2);
        if (dates.length != 2) {
            ui.guide(
                    "Usage: event {task} /from {start} /to {end}"
            );
            return;
        }

        try {
            EventTask event = new EventTask(tokens[0],
                    DukeUtils.parseDateTime(dates[0]),
                    DukeUtils.parseDateTime(dates[1]));
            ts.add(event);
        } catch (DateTimeParseException e) {
            ui.error(
                    new DukeInvalidDateException()
            );
        } catch (DukeStoreFullException e) {
            ui.error(e);
        }
    }
}
