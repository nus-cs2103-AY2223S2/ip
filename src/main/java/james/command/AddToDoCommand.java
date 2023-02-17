package james.command;

import james.exception.JamesException;
import james.jamesbot.Storage;
import james.jamesbot.Ui;
import james.task.TaskList;
import james.task.ToDo;

/**
 * Adds a task of type ToDo to the task list.
 */
public class AddToDoCommand extends Command {
    public static final String COMMAND = "todo";

    public static final String MESSAGE = COMMAND + ": Adds a task of type todo.\n"
            + "(e.g todo return book)";

    public static final String MESSAGE_FORMAT = "Please follow the format for todo task:\n"
            + "'todo [task description]'\n"
            + "Example: 'todo return book'";

    private String userInput;

    /**
     * Constructs an AddToDoCommand object.
     *
     * @param userInput The command the user typed.
     */
    public AddToDoCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the AddToDoCommand which adds a task of type ToDo into a stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out response from JamesBot.
     * @param storage  The task list that is stored in the storage file.
     * @throws JamesException If task description is empty.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean isDescriptionEmpty = userInput.toLowerCase().replaceFirst(COMMAND, "").isBlank();
        if (isDescriptionEmpty) {
            throw new JamesException("Task description is empty \n"
                    + MESSAGE_FORMAT);
        }

        String description = userInput.substring(COMMAND.length()).trim();
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        storage.save(tasks.taskListToStoreString());
        return ui.displayAddTask(todo, tasks.size());
    }

    /**
     * Returns whether AddToDoCommand exits the program.
     *
     * @return false as AddToDoCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
