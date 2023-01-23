package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;
import sebastian.sebastianExceptions.CannotWriteDataException;
import sebastian.sebastianExceptions.InstructionFormatMismatchException;
import sebastian.sebastianExceptions.LackOfArgumentException;
import sebastian.sebastianExceptions.TaskNotExistException;

public class DeleteCommand extends Command{

    private final String instruction;

    public DeleteCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Remove a task from the task list
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @throws LackOfArgumentException when user did not specify the task to be deleted
     * @throws InstructionFormatMismatchException when user command is given in the wrong format
     * @throws TaskNotExistException when user attempts to delete a non-exist task
     * @throws CannotWriteDataException when fail to write task list to the hard disk
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException,
            InstructionFormatMismatchException,
            TaskNotExistException,
            CannotWriteDataException
    {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        }
        else if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                String res = "Noted. I have deleted this task: " + "\n" +
                        taskList.deleteTaskAtIndex(taskIndex) + "\n" +
                        "Now your have " + taskList.getTotalTasks() + " tasks in the list";
                ui.printFormattedString(res);
                storage.writeToDisk(taskList);
            } catch (NumberFormatException e) {
                throw new InstructionFormatMismatchException("delete");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        }
        else {
            throw new InstructionFormatMismatchException("delete");
        }
    }
}
