package james.command;

import james.exception.JamesException;
import james.jamesbot.Storage;
import james.jamesbot.Ui;
import james.task.Task;
import james.task.TaskList;

/**
 * Unmarks a task in the task list.
 */
public class AddUnmarkCommand extends Command {
    public static final String COMMAND = "unmark";

    public static final String MESSAGE = COMMAND + ": Unmarks a task at the specified index as incomplete.\n"
            + "(e.g unmark 1)";

    public static final String MESSAGE_FORMAT = "Please follow the format for unmarking a task:\n"
            + "unmark [index of task]";

    private String userInput;

    /**
     * Constructs an UnmarkCommand object.
     *
     * @param userInput The command the user typed.
     */
    public AddUnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the UnmarkCommand which unmarks the task at the
     * specified index.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out response from JamesBot.
     * @param storage The task list that is stored in the storage file.
     * @throws JamesException If task index is out of bounds;
     *                        If task has already been unmarked;
     *                        If command does not have index.
     */

    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean hasNoIndex = userInput.toLowerCase().trim().endsWith(COMMAND);
        if (hasNoIndex) {
            throw new JamesException("Index is missing\n"
                    + MESSAGE_FORMAT);
        }

        String indexStr = userInput.substring(COMMAND.length()).trim();
        int index = Integer.parseInt(indexStr) - 1;
        boolean isInvalidTask = index >= tasks.size() || index < 0;

        if (isInvalidTask) {
            throw new JamesException("Task " + (index + 1) + " does not exist"
                    + "\nPlease check that you have keyed in the right index");
        }

        boolean hasBeenUnmarked = !tasks.get(index).getIsDone();

        if (hasBeenUnmarked) {
            throw new JamesException("Task " + (index + 1) + " has been unmarked"
                    + "\nplease try unmarking another task");
        }

        Task unmarkedTask = tasks.get(index);
        unmarkedTask.setIsDone(false);
        storage.save(tasks.taskListToStoreString());
        return ui.displayUnmarkedTask(unmarkedTask);
    }

    /**
     * Returns whether UnmarkCommand exits the program.
     *
     * @return false as UnmarkCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
