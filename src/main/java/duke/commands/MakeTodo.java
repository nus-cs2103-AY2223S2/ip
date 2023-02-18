package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Command to create a Todo.
 */
public class MakeTodo extends Command {
    private String description;
    private TaskList tasklist;

    /**
     * Constructor for a command to make a new Todo.
     * @param description Name of the Todo.
     * @param tasklist The list to add this Todo to.
     */
    public MakeTodo(String description, TaskList tasklist) {
        this.description = description;
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        Task t = new Todo(description);
        tasklist.add(t);
        return "Added this new Todo: \n" + t;
    }
}
