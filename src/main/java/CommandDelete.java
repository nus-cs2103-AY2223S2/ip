import java.io.IOException;
import java.util.InputMismatchException;

public class CommandDelete extends Command{
    @Override
    public void handle() throws DukeException {
        try {
            String deleteString = Duke.userScan.nextLine().strip();
            // ERROR: delete format is anything other than [ delete <insert integer> ]
            if (deleteString.length()==0) {
                throw new DukeException(Duke.ui.formatCommandError("delete",
                        "delete <insert INTEGER>"));
            }
            int deleteInput = Integer.parseInt(deleteString) - 1;
            Task deletedTask = Duke.taskList.get(deleteInput);
            Duke.taskList.remove(deleteInput);
            Duke.ui.print("Okay, i've deleted the following task!\n" +
                    (deleteInput + 1 + ". ") +
                    deletedTask.toString());
            Duke.dukeSave.saveTaskList(Duke.taskList);
        }
        catch (IOException err) {
            throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
        }
        // ERROR: delete is NOT paired with an integer (e.g. delete two, delete 2.3)
        catch (NumberFormatException | InputMismatchException err) {
            throw new DukeException(Duke.ui.formatLogicError("delete can only be used with an INTEGER. " +
                    "(e.g. 1, 2...)"));
        }
        // ERROR: delete target does not exist (e.g. task number is out of bounds)
        catch (IndexOutOfBoundsException err) {
            throw new DukeException(Duke.ui.formatLogicError("you can only delete task numbers that exist." +
                    "\nYou have " +
                    Duke.taskList.size() +
                    " task(s) in your list."));
        }
    }
}
