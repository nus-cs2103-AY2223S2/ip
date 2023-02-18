package reborn.data;

import java.util.ArrayList;

import reborn.data.task.Task;

/**
 * Represents a task list.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructor for task list.
     *
     * @param tasks Tasks to create TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Constructor for empty task list.
     */
    public TaskList() {
        super();
    }

    /**
     * Produces string representation of tasks for saving.
     *
     * @return String to be stored in Storage.
     */
    public String tasksToStr() {
        String result = "";
        for (int i = 0; i < size(); i++) {
            result += get(i).storageStr();
            result += System.lineSeparator();
        }
        return result;
    }

    /**
     * Filters TaskList for tasks containing a string.
     *
     * @param findString String to check with.
     * @return ArrayList of tasks that contain the string.
     */
    public ArrayList<Task> filterTasks(String findString) {
        ArrayList<Task> result = new ArrayList<Task>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).contains(findString)) {
                result.add(this.get(i));
            }
        }
        assert result.size() < this.size() : "filtered tasks should be less than total tasks";
        return result;
    }
}

