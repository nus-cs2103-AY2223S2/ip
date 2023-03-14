package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * The class for the Mark command which extends Command class.
 */
public class MarkCommand extends Command {
    private String input;

    /**
     * MarkCommand constructor.
     *
     * @param input The user's input.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            if (input.length() <= 5) {
                throw new DukeException(Ui.missingIndex());
            }

            int index = Integer.parseInt(input.substring(5));
            if (index > tasks.size() || index <= 0) {
                throw new DukeException(Ui.insufficientTasksMessage());
            }
            Task task = tasks.get(index - 1);
            task.mark();
            storage.saveTaskList(tasks);
            return Ui.markTaskConfirmation(task);
        } catch (NumberFormatException nfe) {
            throw new DukeException(Ui.missingIndex());
        }
    }
}
