package panav.command;

import panav.exception.DukeException;
import panav.exception.InvalidNumberException;
import panav.storage.Storage;
import panav.task.Task;
import panav.task.TaskList;
import panav.ui.Ui;

/**
 * Class to encapsulate a 'delete' command, extends from Command.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private final String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Specifies the behaviour of 'delete' command when called to execute. It reads from user
     * input the task index and removes that task from the list of tasks.
     * @param tasks the list of tasks.
     * @param ui ui to interact with user.
     * @param storage storage to read/write text in file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int lengthTasks = tasks.getLength();
            // ArrayList of tasks has to be non-empty to be able to mark/unmark a task.
            assert (lengthTasks > 0);
            int deleteIndex = readNumber(fullCommand, lengthTasks);
            Task removed = tasks.removeTask(deleteIndex - 1);
            String text = "Noted. I've removed this task:\n";
            text += removed.toString() + "\n";
            text += "Now you have " + tasks.getLength() + " tasks in the list.\n";
            return text;
        } catch (DukeException e) {
            return e.getMessage();
        }

    }

    /**
     * Returns the index number for commands which manipulate the list.
     *
     * @param command The command which is manipulating list.
     * @param taskListLength The number of elements in the list.
     * @return Index number in command.
     * @throws InvalidNumberException If the index doesn't exist.
     */
    public static int readNumber(String command, int taskListLength) throws InvalidNumberException {
        int len = command.length();
        char charNum = command.charAt(len - 1);
        String stringNum = String.valueOf(charNum);
        int number = Integer.parseInt(stringNum);
        if (number > taskListLength || number < 1) {
            throw new InvalidNumberException("Oops!! There is no such index number in your list");
        } else {
            return number;
        }
    }
    @Override
    public String toString() {
        return DeleteCommand.COMMAND_WORD;
    }
}
