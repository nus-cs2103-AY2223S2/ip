package duke.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int i) {
        this.tasks.remove(i);
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }
    /**
     * Finds tasks that contain the query in their description or name and returns
     * an array of string representation of the tasks.
     *
     * @param query the string to search for in the task description or name
     * @return an array of string representation of the tasks that contain the query
     */
    public String[] find(String query) {
        List<Task> res = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.contains(query)) {
                res.add(t);
            }
        }

        String[] list = new String[res.size()];
        Task t;
        for (int i = 0; i < res.size(); i++) {
            t = res.get(i);
            list[i] = String.format("%s", t);
        }
        return list;
    }

    public String[] toStringArray() {
        String[] list = new String[this.tasks.size()];
        Task tsk;
        for (int i = 0; i < this.tasks.size(); i++) {
            tsk = this.tasks.get(i);
            list[i] = String.format("%s", tsk);
        }
        return list;
    }

}
