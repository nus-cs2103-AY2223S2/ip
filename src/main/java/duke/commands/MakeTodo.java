package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Task;
import duke.tasks.Todo;

public class MakeTodo extends Command {
    private String description;
    private TaskList tasklist;

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
