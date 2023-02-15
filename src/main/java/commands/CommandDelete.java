package commands;

import java.util.InputMismatchException;

import features.DukeException;
import features.Storage;
import features.TaskList;
import features.Ui;
import tasks.Task;

/**
 * Handles 'delete' command.
 */
public class CommandDelete extends Command {
    /**
     * Deletes a specified Task at the position in the taskList specified by the user and returns
     * a String form of the action.
     * @param userInput The user's String input in array form.
     * @return The deletion confirmation message.
     * @throws DukeException Thrown if an error occurs.
     */
    @Override
    public String handle(String[] userInput) throws DukeException {
        Ui ui = new Ui();
        TaskList taskList = new Storage().loadTaskList();
        try {
            // ERROR: delete format is anything other than [ delete <insert integer> ]
            if (userInput.length != 2) {
                throw new DukeException(ui.formatCommandError("delete",
                        "delete <insert INTEGER>"));
            }
            String deleteString = userInput[1].strip();
            assert (deleteString.length() != 0);
            int deleteInput = Integer.parseInt(deleteString) - 1;
            Task deletedTask = taskList.get(deleteInput);
            taskList.remove(deleteInput);
            autoSave(taskList);
            return ("Okay, i've deleted the following task!\n" + (deleteInput + 1 + ". ")
                    + deletedTask.toString());
        } catch (NumberFormatException | InputMismatchException err) {
            throw new DukeException(ui.formatLogicError("delete can only be used with an INTEGER. "
                    + "(e.g. 1, 2...)"));
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException(ui.formatLogicError("you can only delete task numbers that exist."
                    + "\nYou have "
                    + taskList.size()
                    + " task(s) in your list."));
        }
    }
}
