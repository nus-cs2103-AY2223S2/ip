package panav.command;

import panav.exception.DukeException;
import panav.exception.InvalidNumberException;
import panav.storage.Storage;
import panav.task.Task;
import panav.task.TaskList;
import panav.ui.Ui;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int deleteIndex = readNumber(fullCommand, tasks.getLength());
            Task removed = tasks.removeTask(deleteIndex - 1);
            ui.showLine();
            System.out.println("Noted. I've removed this task:");
            System.out.println(removed);
            System.out.println("Now you have " + tasks.getLength() + " tasks in the list.");
            ui.showLine();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
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
