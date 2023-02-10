package duke;

import java.io.IOException;

public class TodoCommand extends Command {
    private Todo todo;

    /**
     * Constructor for a Todo command.
     *
     * @param description Description of the todo task.
     */
    public TodoCommand(String description) {
        todo = new Todo(description);
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws IOException {
        tasks.add(todo);
        storage.save(tasks.getTasks());
        return response.showAddTask(todo, tasks.getTasks());
    }
}
