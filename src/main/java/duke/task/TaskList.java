package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String generateTaskDetails() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            str += t.getDetailsToSave();
            if (i != tasks.size() - 1) {
                str += '\n';
            }
        }
        return str;
    }

    public Task get(int num) {
        return tasks.get(num);
    }

    public int size() {
        return tasks.size();
    }

    public Task mark(int num) throws DukeException {
        Task t = tasks.get(num);
        if (t.isDone()) {
            throw new DukeInvalidArgumentException("Huh? You've already done this task!");
        } else {
            t.mark();
        }
        return t;
    }

    public Task unmark(int num) throws DukeException {
        Task t = tasks.get(num);
        if (!t.isDone()) {
            throw new DukeInvalidArgumentException("Huh? You haven't even done this task!");
        } else {
            t.unmark();
        }
        return t;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int num) {
        Task t = tasks.get(num - 1);
        tasks.remove(num - 1);
        return t;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
