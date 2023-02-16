package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class for Delete command.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Constructor for command "Delete".
     *
     * @param input The user's input.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes a Delete command.
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
                throw new DukeException("Please indicate a positive index to delete");
            }
            if (index > tasks.getSize()) {
                throw new DukeException("Please indicate an index less than the size of your list: "
                        + tasks.getSize());
            }
            Task task = tasks.get(index);
            tasks.delete(index);
            storage.saveTasks(tasks);
            return "Noted. I've removed this task: \n" + task
                    + "\nNow you have " + tasks.getSize() + " tasks in the list.";
        } catch (NumberFormatException e) {
            throw new DukeException("Please indicate an integer in your index");
        }

    };
}
