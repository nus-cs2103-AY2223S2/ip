package chad.commands;

import chad.backend.Parser;
import chad.backend.TaskList;
import chad.tasks.Task;
import chad.tasks.Todo;

/**
 * Command to create a Todo.
 */
public class MakeTodo extends Make {

    /**
     * Constructor for a command to make a new Todo.
     * @param description Name of the Todo.
     * @param tasklist The list to add this Todo to.
     */
    public MakeTodo(String description, TaskList tasklist, Parser parser) {
        super(description, tasklist, parser);
    }

    @Override
    public String execute() {
        Task t = new Todo(description);
        //  Guard Clause:
        Task duplicate = findDuplicates();
        if (duplicate != null) {
            return duplicateFound(t, duplicate);
        }

        super.tasklist.add(t);
        return "Added this new Todo: \n" + t;
    }


}
