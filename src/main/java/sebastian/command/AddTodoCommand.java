package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;
import sebastian.sebastianExceptions.CannotWriteDataException;
import sebastian.sebastianExceptions.EventFormatMismatchException;
import sebastian.sebastianExceptions.LackOfArgumentException;

public class AddTodoCommand extends AddTaskCommand{
    private final String instruction;

    public AddTodoCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Add a to-do to the task list
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @throws LackOfArgumentException when user did not indicate a to-do to be added
     * @throws CannotWriteDataException when fail to write task list to the hard disk
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException,
            CannotWriteDataException
    {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        } else {
            String task = instruction.substring(5);
            String res = this.addTask(taskList.addTodo(0, task.trim()), taskList.getTotalTasks());
            ui.printFormattedString(res);
            storage.writeToDisk(taskList);
        }
    }
}
