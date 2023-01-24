import DukeExceptions.DukeEmptyTaskException;
import DukeExceptions.DukeInvalidDateException;
import DukeExceptions.DukeStoreFullException;

import java.time.format.DateTimeParseException;

/**
 * The command to create a Deadline task.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DeadlineCommand extends Command {
    private final String slug;
    public DeadlineCommand(String slug) {
        this.slug = slug;
    }

    public DeadlineCommand() {
        this.slug = "";
    }

    @Override
    public void execute(TaskList ts, Ui ui) {
        if (slug.length() == 0) {
            ui.error(new DukeEmptyTaskException(DukeEmptyTaskException.TaskType.Deadline));
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
            TaskDeadline dl = new TaskDeadline(
                    tokens[0],
                    DukeUtils.parseDateTime(tokens[1]));
            ts.add(dl);
        } catch (DateTimeParseException e) {
            ui.error(
                    new DukeInvalidDateException()
            );
        } catch (DukeStoreFullException e) {
            ui.error(e);
        }
    }
}
