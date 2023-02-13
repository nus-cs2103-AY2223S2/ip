package sebastian.command;

import sebastian.exceptions.CannotWriteDataException;
import sebastian.exceptions.LackOfArgumentException;
import sebastian.exceptions.TaskNotExistException;
import sebastian.exceptions.UpdateFormatMismatchException;
import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

/**
 * Class used to handle an update command
 */
public class UpdateCommand extends Command {
    private String instruction;

    public UpdateCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Update the details of a task
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @return a string representing the result of command execution
     * @throws LackOfArgumentException when user did not specify a task to be updated
     * @throws UpdateFormatMismatchException when user attempted to update a task with a wrong format
     * @throws TaskNotExistException when user attempts to update a non-exist task
     * @throws CannotWriteDataException when fail to write task list to the hard disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LackOfArgumentException,
            UpdateFormatMismatchException, TaskNotExistException, CannotWriteDataException {
        String[] insArr = instruction.split(" ");
        if (insArr.length == 1) {
            throw new LackOfArgumentException("Please specify the index of the task to be updated and the new task");
        } else if (insArr.length > 1) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                String commandHeader = "update " + String.valueOf(taskIndex);
                int commandHeaderLength = commandHeader.length();
                String updateDetail = instruction.substring(commandHeaderLength).trim();
                String res = "As you wish. I have updated task " + taskIndex + "\n"
                        + taskList.updateTask(taskIndex, updateDetail);
                storage.writeToDisk(taskList);
                return ui.getFormattedString(res);
            } catch (NumberFormatException e) {
                throw new UpdateFormatMismatchException();
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        } else {
            throw new Error("Internal Error");
        }
    }
}
