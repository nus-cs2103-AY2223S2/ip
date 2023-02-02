package duke.task;

import duke.exception.DukeException;
import duke.exception.ERROR;

import java.util.ArrayList;
import java.util.Collection;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public TaskList(Collection<? extends Task> tasks) {
        super(tasks);
    }

    public void addTask(Task task) {
        super.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        checkValidIndex(index);
        Task task = super.get(index - 1);
        super.remove(index - 1);
        return task;
    }

    public Task markTask(int index) throws DukeException {
        checkValidIndex(index);
        Task task = super.get(index - 1);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int index) throws DukeException {
        checkValidIndex(index);
        Task task = super.get(index - 1);
        task.markAsUndone();
        return task;
    }

    private boolean checkValidIndex(int index) throws DukeException {
        if (index == 0 || index > super.size()) {
            throw new DukeException(String.format(ERROR.INVALID_INDEX.getMessage(), super.size()));
        }
        return true;
    }
}
