package panav.command;

import panav.exception.DukeException;
import panav.exception.InvalidNumberException;
import panav.storage.Storage;
import panav.task.TaskList;
import panav.ui.Ui;

/**
 * Class to encapsulate a 'mark' or 'unmark' command, extends from Command.
 */
public class EditCommand extends Command {

    private String fullCommand;
    private boolean isMark;
    private boolean flag;

    /**
     * Constructor to initialise attributes.
     * @param fullCommand
     * @param isMark
     */
    public EditCommand(String fullCommand, boolean isMark) {
        this.fullCommand = fullCommand;
        this.isMark = isMark;
        this.flag = isMark;
    }

    public final String COMMAND_WORD = isMark ? "mark" : "unmark";

    /**
     * Specifies the behaviour of 'mark' or 'unmark' command when called to execute. Reads from the user
     * input the task index, and either marks or unmarks the task in the list.
     * @param tasks the list of tasks.
     * @param ui ui to interact with user.
     * @param storage storage to read/write text in file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String text = "";
        try {
            int lengthTasks = tasks.getLength();
            // ArrayList of tasks has to be non-empty to be able to mark/unmark a task.
            assert (lengthTasks > 0);
            int editIndex = readNumber(fullCommand, lengthTasks);
            if (isMark) {
                text = tasks.markTask(editIndex - 1);
            } else {
                text = tasks.unmarkTask(editIndex - 1);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return text;

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
        return this.COMMAND_WORD;
    }

}
