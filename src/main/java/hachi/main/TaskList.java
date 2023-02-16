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

    /**
     * Checks if a task from a new input has already been added to the TaskList.
     *
     * @param task New Task to be added.
     * @throws HachiExceptions
     */
    public void checkDuplicate(Task task) throws HachiExceptions {
        for (int i = 0; i < size(); i++) {
            if (tasks.get(i).equals(task)) {
                throw new HachiExceptions("You have already added this task to your list!");
            }
        }
    }

    /**
     * Provides the string representation of TaskList to be written to the hard disk.
     *
     * @return String representation of TaskList.
     */
    public String getTaskList() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += tasks.get(i).saveTask() + "\n";
        }
        return result;
    }

    /**
     * Prints out the tasks in the TaskList.
     *
     * @return All tasks in the TaskList.
     */
    public String list() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            result += "  " + index + ". " + tasks.get(i).toString();
        }
        System.out.print(result);
        return result;
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
     * @return String representation of the TaskList.
     */
    public String toString() {
        String list = " ";
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
