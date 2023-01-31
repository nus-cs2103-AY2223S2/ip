package duke.tasklist;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public int getSize() {
        return list.size();
    }


    public Task getTask(int i) throws DukeException {
        try {
            return list.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unable to get duke.task.");
        }
    }

    public void addTask(Task t) {
        list.add(t);
    }

    public Task removeTask(int i) throws DukeException {
        try {
            Task t = list.remove(i);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unable to remove duke.task.");
        }
    }
}
