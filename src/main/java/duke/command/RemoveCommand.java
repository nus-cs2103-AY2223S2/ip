package duke.command;

import duke.exception.IndexOutOfBoundException;
import duke.exception.MissingArgumentException;
import duke.storage.TaskList;

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

    @Override
    public String execute(TaskList tasks) throws IndexOutOfBoundException {

        String[] req = request.trim().split("delete ");

        // check missing index
        if (req.length < 2) {
            throw new MissingArgumentException("Missing index for deletion!");
        }

        Integer idx = Integer.parseInt(req[1]) - 1;

        // check valid index
        if (idx >= tasks.numOfTask()) {
            throw new IndexOutOfBoundException();
        }

        String deletedTask = tasks.getTask(idx).toString();
        tasks.deleteTask(idx);

        return "Noted. I've removed this task:\n" + deletedTask
                + "\nNow you have " + tasks.numOfTask() + " in the list.";
    }
}
