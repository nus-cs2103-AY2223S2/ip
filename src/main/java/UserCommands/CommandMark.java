package UserCommands;

import java.util.InputMismatchException;
import java.util.Scanner;

import Features.DukeException;
import Features.TaskList;
import Features.Ui;

public class CommandMark extends Command {
    @Override
    public TaskList handle(Scanner userScan, TaskList taskList) throws DukeException {
        Ui ui = new Ui();
        try {
            String markString = userScan.nextLine().strip();
            // ERROR: mark format is anything other than [ mark <insert integer> ]
            if (markString.length() == 0) {
                throw new DukeException(ui.formatCommandError("mark",
                        "mark <insert INTEGER>"));
            }
            int markInput = Integer.parseInt(markString) - 1;
            taskList.get(markInput).markDone();
            ui.print("Okay, the following task is marked as done!\n"
                    + (markInput + 1 + ". ")
                    + taskList.get(markInput).toString());
            return taskList;
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
