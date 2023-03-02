package chad.command;

import chad.exception.IndexOutOfBoundException;
import chad.parser.InputValidator;
import chad.storage.TaskList;

/**
 * Class use to handle command: unmark duke.task.
 * Allows user to unmark specific task in the task list.
 */
public class UnmarkCommand extends Command {
    private String request;

    /**
     * Constructor to create unmark command
     * @param request user's request to unmark duke.task.
     */
    public UnmarkCommand(String request) {
        this.request = request;
    }

    /**
     * Execute the <code>Unmark</code> task.
     *
     * @param tasks the list to store new task.
     * @return Response after unmarking the indicated task.
     * @throws IndexOutOfBoundException
     */
    @Override
    public String execute(TaskList tasks) throws IndexOutOfBoundException {
        String requestType = "unmark";
        String indexString = InputValidator.normaliseIndexRequest(request, requestType);
        Integer idx = Integer.parseInt(indexString) - 1;

        /* Checks for valid index, i.e., within the range of task list */
        if (idx >= tasks.numOfTask()) {
            throw new IndexOutOfBoundException();
        }

        tasks.getTask(idx).unmarkComplete();
        String response = String.format("Aww! One more task on the list \n %s", tasks.getTask(idx));

        return response;
    }
}
