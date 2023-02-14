package windycall.handler;

import windycall.exception.WindyCallException;
import windycall.task.Task;
import windycall.task.Todo;

public class AddTodoHandler extends AddTaskHandler {

    @Override
    public String handle(String userCommand) {
        return "";
    }

    public Task handleAddTodo(String message) throws WindyCallException {
        if (message.length() == 4 || message.substring(4).trim().isEmpty()) {
            throw new WindyCallException("OOPS!!! The description of a todo cannot be empty!");
        }
        String description = message.substring(5);
        return new Todo(description, false);
    }
}
