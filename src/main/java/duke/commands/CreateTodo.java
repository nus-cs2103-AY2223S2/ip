package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.UserInterface;

public class CreateTodo extends Command {
    private String description;

    public CreateTodo( String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList list, UserInterface ui) {
        Todo todo = new Todo(list.nextId(), description);
        list.add(todo);
        ui.showMessage("Got it. I've added this task: " + todo);
    }
}
