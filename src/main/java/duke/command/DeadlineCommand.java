package duke.command;

import duke.exceptions.DukeEmptyTaskException;
import duke.exceptions.DukeInvalidDateException;
import duke.exceptions.TaskListFullException;
import duke.DukeUtils;
import duke.task.DeadlineTask;
import duke.TaskList;
import duke.Ui;

import java.time.format.DateTimeParseException;

/**
 * The command to create a Deadline task.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DeadlineCommand extends Command {
    private final String slug;

    /**
     * Formats the given slug as a Deadline task. Slugs should be provided in
     * this format: "{task} /by d/M/yy HHmm"
     * @param slug The provided slug.
     */
    public DeadlineCommand(String slug) {
        this.slug = slug;
    }

    /**
     * Formats an empty command.
     */
    public DeadlineCommand() {
        this.slug = "";
    }

    /**
     * Executes this command.
     * @param ts The TaskList instance.
     * @param ui The UI output.
     */
    @Override
    public void execute(TaskList ts, Ui ui) {
        if (slug.length() == 0) {
            ui.error(new DukeEmptyTaskException(DukeEmptyTaskException.TaskType.TYPE_Deadline));
            return;
        }

        String[] tokens = slug.split(" /by ", 2);
        if (tokens.length != 2) {
            ui.guide(
                    "Usage: deadline {task} /by {deadline}"
            );
            return;
        }

        try {
            DeadlineTask dl = new DeadlineTask(
                    tokens[0],
                    DukeUtils.parseDateTime(tokens[1]));
            ts.add(dl);
        } catch (DateTimeParseException e) {
            ui.error(
                    new DukeInvalidDateException()
            );
        } catch (TaskListFullException e) {
            ui.error(e);
        }
    }
}
