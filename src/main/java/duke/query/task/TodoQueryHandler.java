package duke.query.task;

import duke.exception.DukeException;
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
        String desc = getNotBlankParam(query, getErrorMessage("description"));
        Task newTask = tt.addTodo(desc);
        tt.saveAllTasks();
        return "Added " + newTask;
    }

    @Override
    public String getQueryDescription() {
        return "todo \n- Adds a new todo.";
    }

    @Override
    public String getQuerySyntax() {
        return String.format("todo <description>");
    }
}
