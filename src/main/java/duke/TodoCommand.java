package duke;

import java.io.IOException;

public class TodoCommand extends Command {
    private String description;

    /**
     * Constructor for a Todo command.
     *
     * @param description Description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks.getTasks());
        ui.showAddTask(todo, tasks.getTasks());
    }
}
