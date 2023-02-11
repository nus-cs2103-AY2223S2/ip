package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;

/**
 * A list that stores Tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Generates the details of all tasks in the list to save in data/tasks.txt.
     * @return Details of all tasks in the list.
     */
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

    /**
     * Marks a task as done.
     * @param num The task number.
     * @return The task that has been marked as done.
     * @throws DukeException
     */
    public Task mark(int num) throws DukeException {
        Task t = tasks.get(num - 1);
        if (t.isDone()) {
            throw new DukeInvalidArgumentException("Huh? You've already done this task!");
        } else {
            t.mark();
        }
        return t;
    }

    /**
     * Marks a task as undone.
     * @param num The task number.
     * @return The task that has been marked as undone.
     * @throws DukeException
     */
    public Task unmark(int num) throws DukeException {
        Task t = tasks.get(num - 1);
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

    /**
     * Deletes a task.
     * @param num The task number.
     * @return The task that has been deleted.
     */
    public Task delete(int num) {
        Task t = tasks.get(num - 1);
        tasks.remove(num - 1);
        return t;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Edits a task description.
     * @param num The task number.
     * @param newDesc The new description.
     * @return The task whose description has been edited.
     */
    public Task edit(int num, String newDesc) {
        Task t = tasks.get(num - 1);
        t.edit(newDesc);
        return t;
    }
}
