package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * The class for the Delete command which extends Command class.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * DeleteCommand constructor.
     *
     * @param input The user's input.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            if (input.length() <= 7) {
                throw new DukeException(Ui.missingIndex());
            }
            int index = Integer.parseInt(input.substring(7));
            if (index > tasks.size() || index <= 0) {
                throw new DukeException(Ui.insufficientTasksMessage());
            }
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            storage.saveTaskList(tasks);
            return Ui.confirmationMessage("deleted", tasks, task);
        } catch (NumberFormatException e) {
            throw new DukeException(Ui.missingIndex());
        }
    }
}
