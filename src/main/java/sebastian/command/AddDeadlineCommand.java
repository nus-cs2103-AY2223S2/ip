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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException,
            DeadlineFormatMismatchException,
            CannotWriteDataException
    {
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
