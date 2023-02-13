package chad.command;

import chad.exception.IndexOutOfBoundException;
import chad.parser.InputValidator;
import chad.storage.TaskList;

/**
 * Class use to handle command: mark duke.task as done.
 * Allows user to mark specific in the duke.task list as done.
 */
public class MarkCommand extends Command {
    private String request;

    /**
     * Constructor to create mark task command according to user's request.
     * @param request user's request to mark the task.
     */
    public MarkCommand(String request) {
        this.request = request;
    }

    /**
     * Execute the <code>Mark</code> task.
     *
     * @param tasks the list to store new task.
     * @return Response after marking the indicated task as done.
     * @throws IndexOutOfBoundException
     */
    @Override
    public String execute(TaskList tasks) throws IndexOutOfBoundException {
        String requestType = "mark";
        String indexString = InputValidator.normaliseIndexRequest(request, requestType);
        Integer idx = Integer.parseInt(indexString) - 1;

        /* Checks for valid index, i.e., within the range of task list */
        if (idx >= tasks.numOfTask()) {
            throw new IndexOutOfBoundException();
        }

        tasks.getTask(idx).markComplete();
        String response = String.format("Nice! I have marked this task as done \n %s", tasks.getTask(idx));

        return response;
    }
}
