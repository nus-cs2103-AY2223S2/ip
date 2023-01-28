package command;

import dukeException.IndexOutOfBoundException;
import dukeException.MissingArgumentException;
import storage.TaskList;

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
        if (req.length < 2) {
            throw new MissingArgumentException("Missing index!");
        }
        Integer idx = Integer.parseInt(req[1]) - 1;
        if (idx >= tasks.numOfTask()) {
            throw new IndexOutOfBoundException();
        }
        tasks.getTask(idx).markComplete();
        return "Nice! I have marked this task as done \n" + tasks.getTask(idx);
    }
}
