package duke.query;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskTracker;

public class TodoQueryHandler extends TaskQueryHandler {
    public TodoQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        String[] parsed = QueryParser.parseQuery(query, new String[]{});
        if (parsed[1] == null || parsed[1].isBlank()) {
            throw new InvalidCommandParamException("Please provide a description for your todo!");
        }
        Task newTask = tt.addTodo(parsed[1]);
        tt.saveAllTasks();
        return "Added task " + newTask;
    }
}
