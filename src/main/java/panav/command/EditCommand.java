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

    /**
     * Constructor to initialise attributes.
     * @param fullCommand
     * @param isMark
     */
    public EditCommand(String fullCommand, boolean isMark) {
        this.fullCommand = fullCommand;
        this.isMark = isMark;
    }
    public final String COMMAND_WORD = this.isMark ? "mark" : "unmark";

    /**
     * Specifies the behaviour of 'mark' or 'unmark' command when called to execute. Reads from the user
     * input the task index, and either marks or unmarks the task in the list.
     * @param tasks the list of tasks.
     * @param ui ui to interact with user.
     * @param storage storage to read/write text in file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        try {
            int num = readNumber(fullCommand, tasks.getLength());
            if (isMark) {
                tasks.markTask(num - 1);
            } else {
                tasks.unmarkTask(num - 1);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        ui.showLine();

    }

    /**
     * Returns the index number for commands which manipulate the list.
     *
     * @param command The command which is manipulating list.
     * @param len The number of elements in the list.
     * @return Index number in command.
     * @throws InvalidNumberException If the index doesn't exist.
     */
    public static int readNumber(String command, int len) throws InvalidNumberException {
        int number = Integer.parseInt(String.valueOf(command.charAt(command.length() - 1)));
        if (number > len || number < 1) {
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
