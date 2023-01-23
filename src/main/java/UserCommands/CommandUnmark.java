package UserCommands;

import Features.DukeException;
import Features.TaskList;
import Features.Ui;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handles 'unmark' command.
 */
public class CommandUnmark extends Command{
    @Override
    public TaskList handle(Scanner userScan, TaskList taskList) throws DukeException {
        Ui ui = new Ui();
        try {
            String unmarkString = userScan.nextLine().strip();
            // ERROR: unmark format is anything other than [ unmark <insert integer> ]
            if (unmarkString.length()==0) {
                throw new DukeException(ui.formatCommandError("unmark",
                        "unmark <insert INTEGER>"));
            }
            int unmarkInput = Integer.parseInt(unmarkString) - 1;
            taskList.get(unmarkInput).MarkNotDone();
            ui.print("Okay, the following task is marked as NOT done!\n" +
                    (unmarkInput+1 + ". ") +
                    taskList.get(unmarkInput).toString());
            return taskList;
        }

        // ERROR: unmark is NOT paired with an integer (e.g. unmark two, unmark 2.3)
        catch (NumberFormatException | InputMismatchException err) {
            throw new DukeException(ui.formatLogicError("unmark can only be used with an INTEGER. " +
                    "(e.g. 1, 2...)"));
        }
        // ERROR: unmark target does not exist (e.g. task number is out of bounds)
        catch (IndexOutOfBoundsException err) {
            throw new DukeException(ui.formatLogicError("you can only unmark task numbers that exist." +
                    "\nYou have " +
                    taskList.size() +
                    " task(s) in your list."));
        }
    }
}
