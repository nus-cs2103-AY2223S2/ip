package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import task.Todo;
import userinteraction.Ui;

public class AddTodoCommand extends AddTaskCommand {
    public AddTodoCommand(String input) {
        super(input);
    }

    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todo = Todo.generate(this.getInput());
        taskList.addTask(todo);
        storage.saveData(taskList);
        ui.printAddTaskMsg(taskList, todo);
    }
}