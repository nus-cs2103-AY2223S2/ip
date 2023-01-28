package command;

import dukeException.MissingArgumentException;
import storage.TaskList;
import task.Todo;

/**
 * Class use to handle command: add todo.
 * Allows user to add todo task into the task list.
 */
public class AddTodoCommand extends Command {
    private String request;

    /**
     * Constructor to create add todo task according to user's request
     * @param request user's request to be processed into todo task.
     */
    public AddTodoCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException {
        String[] task = request.trim().split("todo");
        if (task.length == 0) {
            throw new MissingArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo newTodo = tasks.addTodo(task[1]);
        return "Great! I've added this task for you \n" + newTodo
                + "\nYou have " + tasks.numOfTask() + " tasks in the list";
    }
}
