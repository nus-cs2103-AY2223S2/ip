package chad.command;

import chad.exception.IndexOutOfBoundException;
import chad.parser.InputValidator;
import chad.storage.TaskList;

/**
 * Class use to handle command: delete duke.task.
 * Allows user to delete specific task from the task list.
 */
public class RemoveCommand extends Command {
    private String request;

    /**
     * Constructor to remove task command according to user's request.
     * @param request user's request to remove the duke.task.
     */
    public RemoveCommand(String request) {
        this.request = request;
    }

    /**
     * Execute the <code>Remove</code> task.
     *
     * @param tasks the list to store new task.
     * @return response after deleted indicated task.
     * @throws IndexOutOfBoundException
     */
    @Override
    public String execute(TaskList tasks) throws IndexOutOfBoundException {
        String requestType = "delete";
        String idxString = InputValidator.normaliseIndexRequest(request, requestType);
        Integer idx = Integer.parseInt(idxString) - 1;

        if (idx >= tasks.numOfTask()) {
            throw new IndexOutOfBoundException();
        }

        String deletedTask = tasks.getTask(idx).toString();
        tasks.deleteTask(idx);
        String response = String.format("Noted. I've removed this task:\n %s \n"
                + "Now you have %d tasks in the list.", deletedTask, tasks.numOfTask());

        return response;
    }
}
