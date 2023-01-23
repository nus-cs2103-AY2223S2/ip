package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;
import sebastian.sebastianExceptions.CannotWriteDataException;
import sebastian.sebastianExceptions.DeadlineFormatMismatchException;
import sebastian.sebastianExceptions.LackOfArgumentException;

public class AddDeadlineCommand extends AddTaskCommand{

    private final String instruction;

    public AddDeadlineCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Add a deadline to the task list
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @throws LackOfArgumentException when user did not indicate a deadline to be added
     * @throws DeadlineFormatMismatchException when user attempts to add a deadline with a wrong format
     * @throws CannotWriteDataException when fail to write task list to the hard disk
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException, DeadlineFormatMismatchException, CannotWriteDataException {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        } else {
            String deadline = instruction.substring(9);
            String[] task = deadline.split("/by");
            if(task.length != 2) {
                throw new DeadlineFormatMismatchException();
            } else if(task[0].equals("")) {
                throw new LackOfArgumentException();
            }
            else {
               String res = this.addTask(taskList.addDeadline(0, task[0].trim(), task[1].trim()), taskList.getTotalTasks());
               ui.printFormattedString(res);
               storage.writeToDisk(taskList);
            }
        }
    }
}
