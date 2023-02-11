package sebastian.command;

import sebastian.exceptions.CannotWriteDataException;
import sebastian.exceptions.InstructionFormatMismatchException;
import sebastian.exceptions.LackOfArgumentException;
import sebastian.exceptions.TaskNotExistException;
import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

/**
 * Class used to handle a command to mark a task as not done
 */
public class UnmarkCommand extends Command {

    private String instruction;

    public UnmarkCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Mark a task as not done
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @return a string representing the result of command execution
     * @throws LackOfArgumentException when user did not specify a task to be unmarked
     * @throws InstructionFormatMismatchException when user command is given in the wrong format
     * @throws TaskNotExistException when user attempted to unmark a non-exist task
     * @throws CannotWriteDataException when fail to write task list to the hard disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException, InstructionFormatMismatchException,
            TaskNotExistException, CannotWriteDataException {
        String[] insArr = instruction.split(" ");
        if (insArr.length == 1) {
            throw new LackOfArgumentException("Please specify the index of the task to be unmarked");
        } else if (insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                String res = "As you wish.I have marked this task as uncompleted: " + "\n"
                        + taskList.unmarkTaskAtIndex(taskIndex) + "\n"
                        + "It's time to get started!";
                assert taskIndex > 0 && taskIndex <= taskList.getTotalTasks();
                storage.writeToDisk(taskList);
                return ui.getFormattedString(res);
            } catch (NumberFormatException e) {
                throw new InstructionFormatMismatchException("unmark");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        } else if (insArr.length > 2) {
            throw new InstructionFormatMismatchException("unmark");
        } else {
            throw new Error("Internal Error");
        }
    }
}
