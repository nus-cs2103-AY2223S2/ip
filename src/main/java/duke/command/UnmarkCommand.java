package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class for Unmark command.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * Constructor for command "Unmark".
     *
     * @param input The user's input.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes an unmark command.
     *
     * @param tasks TaskList object containing the list of tasks
     * @param ui The Ui object to display messages.
     * @param storage The Storage object to save the task after execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] commandString = input.split(" ");
            if (commandString.length < 2) {
                throw new DukeException("Please indicate an index to delete");
            }
            int index = Integer.parseInt(commandString[1]) - 1;
            if (index < 0) {
                throw new DukeException("Please indicate a positive index to unmark");
            }
            if (index > tasks.getSize()) {
                throw new DukeException("Please indicate an index less than the size of your list: "
                        + tasks.getSize());
            }
            Task task = tasks.get(index);
            task.unmark();
            storage.saveTasks(tasks);
            return "Remember to do this lightweight tasks:\n" + task;
        } catch (NumberFormatException e) {
            throw new DukeException("Please indicate an integer in your index");
        }
    };
}
