package james.command;

import james.exception.JamesException;
import james.task.ToDo;
import james.task.TaskList;
import james.jamesbot.Storage;
import james.jamesbot.Ui;

/**
 * Adds a task of type ToDo to the task list.
 */
public class AddToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": adds a task of type todo.\n"
            + "(e.g todo return book)";

    public static final String MESSAGE_DETAILED_USAGE = "add a todo task using the following format:\n"
            + "'todo [task description]'\n"
            + "here is an example, 'todo return book'";

    private String userCommand;

    /**
     * Constructs an AddToDoCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public AddToDoCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the AddToDoCommand which adds a task of type ToDo into a stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out JamesBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws JamesException If task description is empty.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean isTaskDescriptionEmpty = userCommand.toLowerCase().replaceFirst(COMMAND_WORD, "").isBlank();
        if (isTaskDescriptionEmpty) {
            throw new JamesException("Task description is empty \n"
                    + MESSAGE_DETAILED_USAGE);
        }

        String description = userCommand.substring(COMMAND_WORD.length()).trim();
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
