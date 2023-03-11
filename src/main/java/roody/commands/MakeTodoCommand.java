package roody.commands;

import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.tasks.Task;
import roody.tasks.Todo;

/**
 * Represents a command that makes a todo.
 */
public class MakeTodoCommand extends Command {
    private String description;

    /**
     * Creates a todo task and adds it to the list.
     * @param description Description of todo.
     */
    public MakeTodoCommand(String description) {
        this.description = description;
    }
    @Override
    public String execute(ArrayList<Task> taskList, Ui ui, Storage storage) {
        Task task = new Todo(description);
        taskList.add(task);
        return ui.showAddTask(task, taskList.size());
    }
}
