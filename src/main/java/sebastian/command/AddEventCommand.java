package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;
import sebastian.sebastianExceptions.CannotWriteDataException;
import sebastian.sebastianExceptions.DeadlineFormatMismatchException;
import sebastian.sebastianExceptions.EventFormatMismatchException;
import sebastian.sebastianExceptions.LackOfArgumentException;

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
     * @throws LackOfArgumentException when user did not indicate a deadline to be added
     * @throws EventFormatMismatchException when user attempts to add an event with a wrong format
     * @throws CannotWriteDataException when fail to write task list to the hard disk
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
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
                String res = this.addTask(taskList.addEvent(0, task[0].trim(), task[1].trim(), task[2].trim()), taskList.getTotalTasks());
                ui.printFormattedString(res);
                storage.writeToDisk(taskList);
            }
        }
    }

}
