package sebastian.command;

import sebastian.exceptions.CannotWriteDataException;
import sebastian.exceptions.InstructionFormatMismatchException;
import sebastian.exceptions.LackOfArgumentException;
import sebastian.exceptions.TaskNotExistException;
import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

/**
 * Class used to handle a command to mark a task as done
 */
public class MarkCommand extends Command {

    private final String instruction;

    public MarkCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Mark a task as done
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @return a string representing the result of command execution
     * @throws LackOfArgumentException when user did not specify a task to be marked
     * @throws InstructionFormatMismatchException when user command is given in the wrong format
     * @throws TaskNotExistException when user attempted to mark a non-exist task
     * @throws CannotWriteDataException when fail to write task list to the hard disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException, InstructionFormatMismatchException,
            TaskNotExistException, CannotWriteDataException {
        String[] insArr = instruction.split(" ");
        if (insArr.length == 1) {
            throw new LackOfArgumentException("Please specify the index of the task to be marked");
        } else if (insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                String res = "As you wish. I have marked this task as completed: " + "\n"
                        + taskList.markTaskAtIndex(taskIndex) + "\n"
                        + "You can proceed with other tasks now";
                assert taskIndex > 0 && taskIndex <= taskList.getTotalTasks();
                storage.writeToDisk(taskList);
                return ui.getFormattedString(res);
            } catch (NumberFormatException e) {
                throw new InstructionFormatMismatchException("mark");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        } else if (insArr.length > 2) {
            throw new InstructionFormatMismatchException("mark");
        } else {
            throw new Error("Internal Error");
        }
    }
}
