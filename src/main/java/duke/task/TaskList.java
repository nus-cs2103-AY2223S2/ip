package duke.task;

import java.util.ArrayList;

/**
 * Contains the list of tasks and operations to add/delete/mark/unmark the tasks in the list.
 */
public class TaskList {
    protected ArrayList<Task> list;

    /** In the event that task list failed to load */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void setList(ArrayList<Task> newList) {
        this.list = newList;
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task delete(int index) {
        return list.remove(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Marks specified task as done
     * @param index required to find specified task
     */
    public Task mark(int index) {
        Task currentTask = list.get(index);
        currentTask.markAsDone();
        return currentTask;
    }

    /**
     * Marks specified task as undone
     * @param index required to find specified task
     */
    public Task unmark(int index) {
        Task currentTask = list.get(index);
        currentTask.markAsUndone();
        return currentTask;
    }

    /**
     * Finds tasks that match a specific keyword by iterating through the list.
     * @param keyword derived from user input
     * @return ArrayList containing all matching tasks
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundList = new ArrayList<>();
        for (Task t: list) {
            if (t.contains(keyword)) {
                foundList.add(t);
            }
        }
        return foundList;
    }

    public int getSize() {
        return list.size();
    }
}
