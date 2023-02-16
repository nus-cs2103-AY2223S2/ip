package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class for Mark command.
 */
public class MarkCommand extends Command {
    private String input;

    /**
     * Constructor for command "Mark".
     *
     * @param input The user's input.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes a Mark command by putting an "x" next to the task.
     *
     * @param tasks TaskList object containing the list of tasks
     * @param ui The Ui object to display messages.
     * @param storage The Storage object to save the task after execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] commandString = input.split(" ");
        int index = Integer.parseInt(commandString[1]) - 1;
        Task task = tasks.get(index);
        task.mark();
        storage.saveTasks(tasks);
        return "Good job finishing this lightweight task:\n" + task;
    };
}
