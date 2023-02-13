package james.command;

import james.exception.JamesException;
import james.task.Task;
import james.task.TaskList;
import james.jamesbot.Storage;
import james.jamesbot.Ui;

/**
 * Marks a task in the task list.
 */
public class AddMarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": marks a task at the specified index as complete.\n"
            + "(e.g mark 1)";

    public static final String MESSAGE_DETAILED_USAGE = "to mark a task as complete, use the following format:\n"
            + "mark [index of task]";

    private String userCommand;

    /**
     * Constructs a MarkCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public AddMarkCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the MarkCommand which marks the task at the
     * specified index.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out JamesBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws JamesException If task index is out of bounds;
     *                        If task has already been marked;
     *                        If command does not have index.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean hasNoIndex = userCommand.toLowerCase().trim().endsWith(COMMAND_WORD);
        if (hasNoIndex) {
            throw new JamesException("Index is missing\n"
                    + MESSAGE_DETAILED_USAGE);
        }

        String indexStr = userCommand.substring(COMMAND_WORD.length()).trim();
        int index = Integer.parseInt(indexStr) - 1;
        boolean isInvalidTask = index >= tasks.size() || index < 0;
        boolean hasBeenMarked = tasks.get(index).getIsDone();


        if (isInvalidTask) {
            throw new JamesException("Task" + String.valueOf(index + 1) + " does not exist"
                    + "\nPlease check that you have keyed in the right index");
        }

        if (hasBeenMarked) {
            throw new JamesException("Task" + String.valueOf(index + 1) + "has been marked"
                    + "\nplease try marking another task");
        }

        Task markedTask = tasks.get(index);
        markedTask.setIsDone(true);
        storage.save(tasks.taskListToStoreString());
        return ui.displayMarkedTask(markedTask);
    }

    /**
     * Returns whether MarkCommand exits the program.
     *
     * @return false as MarkCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
