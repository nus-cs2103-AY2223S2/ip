package chad.command;

import chad.exception.MissingArgumentException;
import chad.parser.InputValidator;
import chad.storage.TaskList;
import chad.task.Todo;

/**
 * Class use to handle command: add todo.
 * Allows user to add todo task into the task list.
 */
public class AddTodoCommand extends Command {
    private String request;

    /**
     * Constructor to create add todo task according to user's request.
     *
     * @param request user's request to be processed into todo task.
     */
    public AddTodoCommand(String request) {
        this.request = request;
    }

    /**
     * Execute the <code>Todo</code> task.
     *
     * @param tasks the list to store new task.
     * @return Response after added the task into task list.
     * @throws MissingArgumentException
     */
    @Override
    public String execute(TaskList tasks) throws MissingArgumentException {
        String description = InputValidator.normaliseTodoRequest(request);

        Todo duplicateChecker = new Todo(description);

        if (InputValidator.checkDuplicates(tasks, duplicateChecker)) {
            return String.format("You have already added this into your task list.\n Duplicated Task: %s",
                    duplicateChecker.toString());
        }

        Todo newTodo = tasks.addTodo(description);
        String response = String.format("Great! I've added this task for you\n %s \n"
                + "You have %d tasks in the list.", newTodo, tasks.numOfTask());

        return response;
    }
}
