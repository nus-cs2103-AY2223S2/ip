package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;
import sebastian.sebastianExceptions.CannotWriteDataException;
import sebastian.sebastianExceptions.LackOfArgumentException;

public class AddTodoCommand extends AddTaskCommand{
    private final String instruction;

    public AddTodoCommand(String instruction) {
        this.instruction = instruction;
    }

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
