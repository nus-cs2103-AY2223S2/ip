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
    private String fullCommand;

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
            int deleteIndex = readNumber(fullCommand, tasks.getLength());
            Task removed = tasks.removeTask(deleteIndex - 1);
//            ui.showLine();
//            System.out.println("Noted. I've removed this task:");
//            System.out.println(removed);
//            System.out.println("Now you have " + tasks.getLength() + " tasks in the list.");
//            ui.showLine();
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
        return DeleteCommand.COMMAND_WORD;
    }
}
