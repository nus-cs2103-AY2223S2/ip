package commands;

import java.util.InputMismatchException;

import features.DukeException;
import features.Storage;
import features.TaskList;
import features.Ui;

/**
 * Handles 'unmark' command.
 */
public class CommandUnmark extends Command {
    /**
     * Unmarks a specified Task at the position in the taskList specified by the user and returns
     * a String form of the action.
     * @param userInput The user's String input in array form.
     * @return The unmark confirmation message.
     * @throws DukeException Thrown if an error occurs.
     */
    @Override
    public String handle(String[] userInput) throws DukeException {
        Ui ui = new Ui();
        TaskList taskList = new Storage().loadTaskList();
        try {
            // ERROR: unmark format is anything other than [ unmark <insert integer> ]
            if (userInput.length != 2) {
                throw new DukeException(ui.formatCommandError("unmark",
                        "unmark <insert INTEGER>"));
            }
            String unmarkString = userInput[1].strip();
            int unmarkInput = Integer.parseInt(unmarkString) - 1;
            assert (unmarkInput >= 0);
            taskList.get(unmarkInput).markNotDone();
            autoSave(taskList);
            return ("Okay, the following task is marked as NOT done!\n"
                    + (unmarkInput + 1 + ". ")
                    + taskList.get(unmarkInput).toString());
        } catch (NumberFormatException | InputMismatchException err) {
            throw new DukeException(ui.formatLogicError("unmark can only be used with an INTEGER. "
                    + "(e.g. 1, 2...)"));
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException(ui.formatLogicError("you can only unmark task numbers that exist.\n"
                    + "You have "
                    + taskList.size()
                    + " task(s) in your list."));
        }
    }
}
