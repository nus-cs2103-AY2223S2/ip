package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.UserInterface;

/**
 * Command to create a new Todo.
 *
 * @author Samarth Verma
 */
public class CreateTodo extends Command {

    private String description;

    /**
     * Creates a new CreateTodo command.
     *
     * @param description
     */
    public CreateTodo(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList list, UserInterface ui, Storage storage) throws Exception {
        Todo todo = new Todo(list.nextId(), description);
        list.add(todo);
        ui.showMessage("Got it. I've added this task: " + todo);

        storage.save(list);
    }
}
