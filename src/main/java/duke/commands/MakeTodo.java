package duke.commands;

import duke.backend.Parser;
import duke.backend.TaskList;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

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

        tasklist.add(t);
        return "Added this new Todo: \n" + t;
    }


}
