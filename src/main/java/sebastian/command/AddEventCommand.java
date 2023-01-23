package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;
import sebastian.sebastianExceptions.CannotWriteDataException;
import sebastian.sebastianExceptions.EventFormatMismatchException;
import sebastian.sebastianExceptions.LackOfArgumentException;

public class AddEventCommand extends AddTaskCommand {

    private final String instruction;

    public AddEventCommand(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException,
            EventFormatMismatchException,
            CannotWriteDataException
    {
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
