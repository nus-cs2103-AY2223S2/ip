package duke.command;

import duke.exception.IndexOutOfBoundException;
import duke.exception.MissingArgumentException;
import duke.storage.TaskList;

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

    @Override
    public String execute(TaskList tasks) throws IndexOutOfBoundException {

        String[] req = this.request.split("mark ");

        // check missing index
        if (req.length < 2) {
            throw new MissingArgumentException("Missing index!");
        }

        Integer idx = Integer.parseInt(req[1]) - 1;

        // check valid index
        if (idx >= tasks.numOfTask()) {
            throw new IndexOutOfBoundException();
        }

        tasks.getTask(idx).markComplete();

        return "Nice! I have marked this task as done \n" + tasks.getTask(idx);
    }
}
