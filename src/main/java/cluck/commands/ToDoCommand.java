package cluck.commands;

import cluck.messages.Messages;
import cluck.taskList.TaskList;
import cluck.tasks.Task;
import cluck.tasks.ToDo;

public class ToDoCommand implements Command {
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList) {
        Task task = new ToDo(description);
        taskList.addTask(task);
        return Messages.MESSAGE_TODO_ADDED;
    }
}
