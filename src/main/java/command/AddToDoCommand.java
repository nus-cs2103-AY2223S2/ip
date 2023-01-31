package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import task.ToDo;
import userinteraction.Ui;

public class AddToDoCommand extends AddTaskCommand {
    public AddToDoCommand(String[] inputArr) {
        super(inputArr);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo toDo = ToDo.generate(inputArr);
        taskList.addTask(toDo);
        storage.writeData(taskList);
        ui.printAddTaskMsg(taskList, toDo);
    }
}
