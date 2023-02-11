package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;
import tasks.Task;
import tasks.Todo;

public class ToDoCommand implements Command {
    private String input;

    public ToDoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(getDescription());
        taskList.getTaskList().add(todo);
        storage.store(taskList.getTaskList());
        ui.newToDoMessage(todo);
    }

    public String getDescription() {
        return input.split(" ", 2)[1];
    }
}
