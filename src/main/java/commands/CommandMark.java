package commands;

import java.util.InputMismatchException;

import features.DukeException;
import features.Storage;
import features.TaskList;
import features.Ui;

/**
 * Handles 'mark' command.
 */
public class CommandMark extends Command {
    /**
     * Marks a specified Task at the position in the taskList specified by the user and returns
     * a String form of the action.
     * @param userInput The user's String input in array form.
     * @return The mark confirmation message.
     * @throws DukeException Thrown if an error occurs.
     */
    @Override
    public String handle(String[] userInput) throws DukeException {
        Ui ui = new Ui();
        TaskList taskList = new Storage().loadTaskList();
        try {
            // ERROR: mark format is anything other than [ mark <insert integer> ]
            if (userInput.length != 2) {
                throw new DukeException(ui.formatCommandError("mark",
                        "mark <insert INTEGER>"));
            }
            String markString = userInput[1].strip();
            int markInput = Integer.parseInt(markString) - 1;
            assert (markInput >= 0);
            taskList.get(markInput).markDone();
            autoSave(taskList);
            return ("Okay, the following task is marked as done!\n"
                    + (markInput + 1 + ". ")
                    + taskList.get(markInput).toString());
        } catch (NumberFormatException | InputMismatchException err) {
            throw new DukeException(ui.formatLogicError("mark can only be used with an INTEGER. "
                    + "(e.g. 1, 2...)"));
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException(ui.formatLogicError("you can only mark task numbers that exist.\n"
                    + "You have "
                    + taskList.size()
                    + " task(s) in your list."));
        }
    }
}
