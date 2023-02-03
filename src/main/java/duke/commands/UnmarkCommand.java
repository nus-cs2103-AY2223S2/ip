package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * The class for the Unmark command which extends Command class.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * UnmarkCommand constructor.
     *
     * @param input The user's input.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.length() <= 7) {
                throw new DukeException("OOPS!!! You are missing the number of the task to be unmarked.");
            }
            int index = Integer.parseInt(input.substring(7));
            Task task = tasks.get(index - 1);
            task.unmark();
            storage.saveTaskList(tasks);
            return "OK, I've marked this task as not done yet:\n  " + task;
        } catch (DukeException de) {
            return de.getMessage();
        } catch (NumberFormatException nfe) {
            return "OOPS!!! Unmark has to be followed by an int.";
        } catch (IndexOutOfBoundsException i) {
            return "OOPS!!! There are insufficient tasks.";
        }
    }
}
