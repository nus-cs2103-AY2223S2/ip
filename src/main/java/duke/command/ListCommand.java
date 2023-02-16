package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class for List command.
 */
public class ListCommand extends Command {
    private String input;

    /**
     * Constructor for command "List".
     *
     * @param input The user's input.
     */
    public ListCommand(String input) {
        this.input = input;
    }

    /**
     * Executes a list command by listing all the tasks.
     *
     * @param tasks TaskList object containing the list of tasks
     * @param ui The Ui object to display messages.
     * @param storage The Storage object to save the task after execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder listOfTasks = new StringBuilder("Here's your tasks to become massive: \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return listOfTasks.toString();
    };
}
