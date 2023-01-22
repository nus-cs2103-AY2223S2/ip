import java.io.IOException;
import java.util.InputMismatchException;

public class CommandMark extends Command{
    @Override
    public void handle() throws DukeException {
        try {
            String markString = Duke.userScan.nextLine().strip();
            // ERROR: mark format is anything other than [ mark <insert integer> ]
            if (markString.length()==0) {
                throw new DukeException(Duke.ui.formatCommandError("mark",
                        "mark <insert INTEGER>"));
            }
            int markInput = Integer.parseInt(markString) - 1;
            Duke.taskList.get(markInput).MarkDone();
            Duke.ui.print("Okay, the following task is marked as done!\n" +
                    (markInput+1 + ". ") +
                    Duke.taskList.get(markInput).toString());
            Duke.dukeSave.saveTaskList(Duke.taskList);
        }
        catch (IOException err) {
            throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
        }
        // ERROR: mark is NOT paired with an integer (e.g. unmark two, unmark 2.3)
        catch (NumberFormatException | InputMismatchException err) {
            throw new DukeException(Duke.ui.formatLogicError("mark can only be used with an INTEGER. " +
                    "(e.g. 1, 2...)"));
        }
        // ERROR: mark target does not exist (e.g. task number is out of bounds)
        catch (IndexOutOfBoundsException err) {
            throw new DukeException(Duke.ui.formatLogicError("you can only mark task numbers that exist.\n" +
                    "You have " +
                    Duke.taskList.size() +
                    " task(s) in your list."));
        }
    }
}
