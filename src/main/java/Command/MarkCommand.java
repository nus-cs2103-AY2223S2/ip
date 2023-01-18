package Command;

import DukeException.IndexOutOfBoundException;
import Storage.TaskList;
import Task.Task;

public class MarkCommand extends Command {
    private String request;

    /**
     * Constructor to create mark command according to user's request
     * @param request user's request
     */
    public MarkCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) throws IndexOutOfBoundException {
        String[] req = this.request.split(" ");
        Integer idx = Integer.parseInt(req[1]) - 1;
        if (idx >= tasks.numOfTask()) {
            throw new IndexOutOfBoundException();
        }
        tasks.getTask(idx).markComplete();
        return "Nice! I have marked this task as done \n" + tasks.getTask(idx);
    }
}
