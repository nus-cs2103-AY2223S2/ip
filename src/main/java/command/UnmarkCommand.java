package command;

import exception.IndexOutOfBoundException;
import exception.MissingArgumentException;
import storage.TaskList;

/**
 * Class use to handle command: unmark task.
 * Allows user to unmark specific task in the task list.
 */
public class UnmarkCommand extends Command {
    private String request;

    /**
     * Constructor to create unmark command
     * @param request user's request to unmark task.
     */
    public UnmarkCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) throws IndexOutOfBoundException {
        String[] req = this.request.split("unmark ");
        if (req.length < 2) {
            throw new MissingArgumentException("Missing index!");
        }
        Integer idx = Integer.parseInt(req[1]) - 1;
        if (idx >= tasks.numOfTask()) {
            throw new IndexOutOfBoundException();
        }
        tasks.getTask(idx).unmarkComplete();
        return "Aww! One more task on the list \n" + tasks.getTask(idx);
    }
}
