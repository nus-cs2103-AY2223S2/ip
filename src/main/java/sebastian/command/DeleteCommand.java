package sebastian.command;

import sebastian.exceptions.CannotWriteDataException;
import sebastian.exceptions.InstructionFormatMismatchException;
import sebastian.exceptions.LackOfArgumentException;
import sebastian.exceptions.TaskNotExistException;
import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

/**
 * Class used to handle a command to delete a task
 */
public class DeleteCommand extends Command {

    private final String instruction;

    public DeleteCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Remove a task from the task list
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @return a string representing the result of task execution
     * @throws LackOfArgumentException when user did not specify the task to be deleted
     * @throws InstructionFormatMismatchException when user command is given in the wrong format
     * @throws TaskNotExistException when user attempts to delete a non-exist task
     * @throws CannotWriteDataException when fail to write task list to the hard disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException, InstructionFormatMismatchException,
            TaskNotExistException, CannotWriteDataException {
        String[] insArr = instruction.split(" ");
        if (insArr.length == 1) {
            throw new LackOfArgumentException("Please specify the index of the task to be deleted");
        } else if (insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                int originalSize = taskList.getTotalTasks();
                String res = "As you command. I have deleted this task: " + "\n"
                        + taskList.deleteTaskAtIndex(taskIndex) + "\n"
                        + "Now your have " + taskList.getTotalTasks() + " tasks in the list";
                assert taskList.getTotalTasks() == originalSize - 1;
                storage.writeToDisk(taskList);
                return ui.getFormattedString(res);
            } catch (NumberFormatException e) {
                throw new InstructionFormatMismatchException("delete");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        } else if (insArr.length > 2) {
            throw new InstructionFormatMismatchException("delete");
        } else {
            throw new Error("Internal Error");
        }
    }
}
