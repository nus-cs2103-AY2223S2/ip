import java.io.IOException;
import java.util.InputMismatchException;

public class CommandUnmark extends Command{
    @Override
    public void handle() throws DukeException {
        try {
            String unmarkString = Duke.userScan.nextLine().strip();
            // ERROR: unmark format is anything other than [ unmark <insert integer> ]
            if (unmarkString.length()==0) {
                throw new DukeException(Duke.ui.formatCommandError("unmark",
                        "unmark <insert INTEGER>"));
            }
            int unmarkInput = Integer.parseInt(unmarkString) - 1;
            Duke.taskList.get(unmarkInput).MarkNotDone();
            Duke.ui.print("Okay, the following task is marked as NOT done!\n" +
                    (unmarkInput+1 + ". ") +
                    Duke.taskList.get(unmarkInput).toString());
            Duke.dukeSave.saveTaskList(Duke.taskList);
        }
        catch (IOException err) {
            throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
        }
        // ERROR: unmark is NOT paired with an integer (e.g. unmark two, unmark 2.3)
        catch (NumberFormatException | InputMismatchException err) {
            throw new DukeException(Duke.ui.formatLogicError("unmark can only be used with an INTEGER. " +
                    "(e.g. 1, 2...)"));
        }
        // ERROR: unmark target does not exist (e.g. task number is out of bounds)
        catch (IndexOutOfBoundsException err) {
            throw new DukeException(Duke.ui.formatLogicError("you can only unmark task numbers that exist." +
                    "\nYou have " +
                    Duke.taskList.size() +
                    " task(s) in your list."));
        }
    }
}
