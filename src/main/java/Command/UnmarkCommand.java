package Command;

import DukeException.IndexOutOfBoundException;
import Storage.TaskList;

public class UnmarkCommand extends Command {
    private String request;

    /**
     * Constructor to create unmark command
     * @param request user's request
     */
    public UnmarkCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) {
        String[] req = this.request.split(" ");
        Integer idx = Integer.parseInt(req[1]) - 1;
        if (idx >= tasks.numOfTask()) {
            throw new IndexOutOfBoundException();
        }
        tasks.getTask(idx).unmarkComplete();
        return "Aww! One more task on the list \n" + tasks.getTask(idx);
    }
}
