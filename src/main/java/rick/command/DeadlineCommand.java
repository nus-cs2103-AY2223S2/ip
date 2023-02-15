package rick.command;

import java.time.format.DateTimeParseException;

import rick.RickUtils;
import rick.TaskList;
import rick.Ui;
import rick.exceptions.RickEmptyTaskException;
import rick.exceptions.RickInvalidDateException;
import rick.exceptions.TaskListFullException;
import rick.task.DeadlineTask;


/**
 * Represents the command that creates a Deadline task.
 *
 * @author SeeuSim
 *     AY2223-S2 CS2103T
 */
public class DeadlineCommand extends Command {
    private final String slug;

    /**
     * Constructs this Command with a slug. Slugs should be provided in
     * this format: "{task} /by d/M/yy HHmm"
     *
     * @param slug The provided slug.
     */
    public DeadlineCommand(String slug) {
        this.slug = slug;
    }

    /**
     * Constructs this Command with an empty slug..
     */
    public DeadlineCommand() {
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
        if (slug.length() == 0) {
            return ui.error(
                    new RickEmptyTaskException(
                            RickEmptyTaskException.TaskType.TYPE_Deadline
                    )
            );
        }

        String[] tokens = slug.split(" /by ", 2);
        if (tokens.length != 2) {
            return ui.guide(
                    "Usage: deadline {task} /by {deadline}"
            );
        }

        try {
            DeadlineTask dl = new DeadlineTask(
                    tokens[0],
                    RickUtils.parseDateTime(tokens[1]));
            return ts.add(dl);
        } catch (DateTimeParseException e) {
            return ui.error(
                    new RickInvalidDateException()
            );
        } catch (TaskListFullException e) {
            return ui.error(e);
        }
    }
}
