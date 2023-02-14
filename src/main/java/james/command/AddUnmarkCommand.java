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

    public static final String MESSAGE = COMMAND + ": unmarks a task at the specified index as incomplete.\n"
            + "(e.g unmark 1)";

    public static final String MESSAGE_FORMAT = "to mark a task as incomplete, use the following format:\n"
            + "unmark [index of task]";

    private String userCommand;

    /**
     * Constructs an UnmarkCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public AddUnmarkCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the UnmarkCommand which unmarks the task at the
     * specified index.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out JamesBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws JamesException If task index is out of bounds;
     *                      If task has already been unmarked;
     *                      If command does not have index.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean hasNoIndex = userCommand.toLowerCase().trim().endsWith(COMMAND);
        if (hasNoIndex) {
            throw new JamesException("Index is missing\n"
                    + MESSAGE_FORMAT);
        }

        String indexStr = userCommand.substring(COMMAND.length()).trim();
        int index = Integer.parseInt(indexStr) - 1;
        boolean isInvalidTask = index >= tasks.size() || index < 0;
        boolean hasBeenUnmarked = !tasks.get(index).getIsDone();
        if (isInvalidTask) {
            throw new JamesException("Task " + String.valueOf(index + 1) + " does not exist"
                    + "\nPlease check that you have keyed in the right index");
        }

        if (hasBeenUnmarked) {
            throw new JamesException("Task" + String.valueOf(index + 1) + "has been unmarked"
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
