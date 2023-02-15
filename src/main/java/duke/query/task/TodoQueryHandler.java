package duke.query.task;

import duke.DukeException;
import duke.query.InvalidCommandParamException;
import duke.query.Query;
import duke.task.Task;
import duke.task.TaskTracker;

/**
 * The TodoQueryHandler class handles user queries for adding new todos.
 */
public class TodoQueryHandler extends TaskQueryHandler {
    public TodoQueryHandler(TaskTracker tt) {
        super(tt);
    }

    /**
     * Processes a query for adding a todo.
     *
     * @param query a user input string
     * @return response from adding a todo
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        String desc = query.getParam();
        if (desc == null || desc.isBlank()) {
            throw new InvalidCommandParamException("Please provide a description for your todo!");
        }
        Task newTask = tt.addTodo(desc);
        tt.saveAllTasks();
        return "Added task " + newTask;
    }
}
