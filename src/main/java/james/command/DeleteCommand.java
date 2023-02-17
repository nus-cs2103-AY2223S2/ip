package james.command;

import james.exception.JamesException;
import james.jamesbot.Storage;
import james.jamesbot.Ui;
import james.task.Task;
import james.task.TaskList;


/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";

    public static final String MESSAGE = COMMAND + ": Deletes a task at the specified index.\n"
            + "(e.g delete 1)";

    public static final String MESSAGE_FORMAT = "Please follow the format for deleting a task:\n"
            + "delete [index of task]";

    private String userInput;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param userInput The command the user typed.
     */
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }
    /**
     * Executes the DeleteCommand which deletes a task of the specified index
     * from the stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out response from JamesBot.
     * @param storage The task list that is stored in the storage file.
     * @throws JamesException If task index is out of bounds;
     *                        If task index is not specified.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean hasNoIndex = userInput.toLowerCase().trim().endsWith(COMMAND);
        if (hasNoIndex) {
            throw new JamesException("Index is missing!\n"
                    + MESSAGE_FORMAT);
        }

        String indexStr = userInput.substring(COMMAND.length()).trim();
        int index = Integer.parseInt(indexStr) - 1;
        boolean isInvalidTask = index >= tasks.size() || index < 0;
        if (isInvalidTask) {
            throw new JamesException("Task " + (index + 1) + " does not exist"
                    + "\nCheck that a valid task number has been typed in");
        }

        Task task = tasks.remove(index);
        storage.save(tasks.taskListToStoreString());
        return ui.displayDeletedTask(task, tasks.size());
    }

    /**
     * Returns whether DeleteCommand exits the program.
     *
     * @return false as DeleteCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}


