package UserCommands;

import Features.DukeException;
import Features.TaskList;
import Features.Ui;
import Tasks.Task;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandDelete extends Command{
    @Override
    public TaskList handle(Scanner userScan, TaskList taskList) throws DukeException {
        Ui ui = new Ui();
        try {
            String deleteString = userScan.nextLine().strip();
            // ERROR: delete format is anything other than [ delete <insert integer> ]
            if (deleteString.length()==0) {
                throw new DukeException(ui.formatCommandError("delete",
                        "delete <insert INTEGER>"));
            }
            int deleteInput = Integer.parseInt(deleteString) - 1;
            Task deletedTask = taskList.get(deleteInput);
            taskList.remove(deleteInput);
            ui.print("Okay, i've deleted the following task!\n" +
                    (deleteInput + 1 + ". ") +
                    deletedTask.toString());
            return taskList;
        }

        // ERROR: delete is NOT paired with an integer (e.g. delete two, delete 2.3)
        catch (NumberFormatException | InputMismatchException err) {
            throw new DukeException(ui.formatLogicError("delete can only be used with an INTEGER. " +
                    "(e.g. 1, 2...)"));
        }
        // ERROR: delete target does not exist (e.g. task number is out of bounds)
        catch (IndexOutOfBoundsException err) {
            throw new DukeException(ui.formatLogicError("you can only delete task numbers that exist." +
                    "\nYou have " +
                    taskList.size() +
                    " task(s) in your list."));
        }
    }
}
