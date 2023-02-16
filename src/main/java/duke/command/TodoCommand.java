package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Class for Todo command.
 */
public class TodoCommand extends Command {
    private String input;

    /**
     * Constructor for command "Todo".
     *
     * @param input The user's input.
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes a Todo command.
     *
     * @param tasks TaskList object containing the list of tasks
     * @param ui The Ui object to display messages.
     * @param storage The Storage object to save the task after execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String taskName = input.substring(5);
        Todo todo = new Todo(taskName);
        tasks.add(todo);
        storage.saveTasks(tasks);
        return "Got it. I've added this task: \n" + todo
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    };
}
