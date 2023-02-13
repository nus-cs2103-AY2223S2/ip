package sebastian.command;

import sebastian.exceptions.CannotWriteDataException;
import sebastian.exceptions.DeadlineFormatMismatchException;
import sebastian.exceptions.LackOfArgumentException;
import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;


/**
 * Class used to handle a command to add a deadline
 */
public class AddDeadlineCommand extends AddTaskCommand {

    private final String instruction;


    public AddDeadlineCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Add a deadline to the task list
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @return a string representing the result of task execution
     * @throws LackOfArgumentException when user did not indicate a deadline to be added
     * @throws DeadlineFormatMismatchException when user attempts to add a deadline with a wrong format
     * @throws CannotWriteDataException when fail to write task list to the hard disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException, DeadlineFormatMismatchException, CannotWriteDataException {
        String[] insArr = instruction.split(" ");
        if (insArr.length == 1) {
            throw new LackOfArgumentException("Please specify a description and an end time for your deadline");
        } else if (insArr.length > 1) {
            String deadline = instruction.substring(9);
            String[] task = deadline.split("/by");
            if (task.length == 2 && !task[0].equals("")) {
                int originalSize = taskList.getTotalTasks();
                String res = this.addTask(taskList.addDeadline(false, task[0].trim(), task[1].trim()),
                       taskList.getTotalTasks());
                assert taskList.getTotalTasks() == originalSize + 1;
                storage.writeToDisk(taskList);
                return ui.getFormattedString(res);
            } else if (task.length == 2 && task[0].equals("")) {
                throw new LackOfArgumentException("Please specify a description for your deadline");
            } else {
                throw new DeadlineFormatMismatchException();
            }
        } else {
            throw new Error("Internal Error");
        }
    }
}
