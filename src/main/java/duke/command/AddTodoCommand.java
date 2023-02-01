package duke.command;

import duke.exception.MissingArgumentException;
import duke.storage.TaskList;
import duke.task.Todo;

/**
 * Class use to handle command: add todo.
 * Allows user to add todo duke.task into the duke.task list.
 */
public class AddTodoCommand extends Command {
    private String request;

    /**
     * Constructor to create add todo duke.task according to user's request
     * @param request user's request to be processed into todo duke.task.
     */
    public AddTodoCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException {
        String[] task = request.strip().split("todo");
        if (task.length == 0) {
            throw new MissingArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo newTodo = tasks.addTodo(task[1].strip());
        return "Great! I've added this duke.task for you \n" + newTodo
                + "\nYou have " + tasks.numOfTask() + " tasks in the list";
    }
}
