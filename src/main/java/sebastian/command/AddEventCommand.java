package sebastian.command;

import sebastian.exceptions.CannotWriteDataException;
import sebastian.exceptions.EventFormatMismatchException;
import sebastian.exceptions.LackOfArgumentException;
import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

/**
 * Class used to handle a command to add an event
 */
public class AddEventCommand extends AddTaskCommand {

    private final String instruction;

    public AddEventCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Add an event to the task list
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @return a string representing the result of task execution
     * @throws LackOfArgumentException when user did not indicate a deadline to be added
     * @throws EventFormatMismatchException when user attempts to add an event with a wrong format
     * @throws CannotWriteDataException when fail to write task list to the hard disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException, EventFormatMismatchException, CannotWriteDataException {
        String[] insArr = instruction.split(" ");
        if (insArr.length == 1) {
            throw new LackOfArgumentException();
        } else {
            String event = instruction.substring(6);
            String[] task = event.split("/from|/to");
            if (task.length != 3) {
                throw new EventFormatMismatchException();
            } else if (task[0].equals("")) {
                throw new LackOfArgumentException();
            } else {
                int originalSize = taskList.getTotalTasks();
                String res = this.addTask(taskList.addEvent(0, task[0].trim(), task[1].trim(), task[2].trim()),
                        taskList.getTotalTasks());
                assert taskList.getTotalTasks() == originalSize + 1;
                storage.writeToDisk(taskList);
                return ui.getFormattedString(res);
            }
        }
    }

}
