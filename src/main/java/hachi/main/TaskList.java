package hachi.main;

import hachi.tasks.Task;
import java.util.ArrayList;

/**
 * Represents a list ot saved tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * TaskList constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a Task of the specified index.
     *
     * @param i Index of the Task.
     * @return Task of specified index.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Adds a Task into the TaskList.
     *
     * @param task New Task object to be added to the TaskList.
     */
    public void add(Task task) throws HachiExceptions {
        assert task != null;
        checkDuplicate(task);
        this.tasks.add(task);
    }

    public void checkDuplicate(Task task) throws HachiExceptions {
        for (int i = 0; i < size(); i++) {
            if (tasks.get(i).equals(task)) {
                throw new HachiExceptions("You have already added this task to your list!");
            }
        }
    }

    public String getStoredString() {
        String ls = "";
        for (int i = 0; i < tasks.size(); i++) {
            ls += "\n       " + tasks.get(i);
        }
        return ls;
    }


    /**
     * Removes a Task from the TaskList.
     *
     * @param i Index of the Task to be removed.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Size of the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }


    /**
     * Return the string representation of the TaskList.
     *
     * @return string representation of the TaskList.
     */
    public String toString() {
        String list = "Here are your tasks: \n";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                list += i + 1 + ". " + tasks.get(i);
            } else {
                list += i + 1 + ". " + tasks.get(i) + "\n";
            }
        }
        return list;
    }
}
