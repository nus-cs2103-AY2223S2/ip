package duke;

import java.util.ArrayList;

/**
 * A list of tasks
 */
public class TasksList {
    /**
     * Array list of tasks
     */
    private ArrayList<Tasks> list;
    
    /**
     * Returns a TasksList object of a certain size
     * @param size number of objects that can be inside the TasksList
     */
    public TasksList(int size) {
        list = new ArrayList<Tasks>(size);
    }

    /**
     * Adds a task into the list of tasks
     * @param task The task that is to be added
     */
    public void addTask(Tasks task) {
        list.add(task);
    }

    /**
     * Returns the number of tasks in the list
     * @return Size of list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns the task that is in the list of a certain index
     * @param index index of the arraylist
     * @return Task that is in the list at the certain index
     */
    public Tasks getTask(int index) {
        return list.get(index);
    }

    /**
     * Removes this task from the list of a certain index
     * @param index index of the arraylist
     */
    public Tasks removeTask(int index) {
        return list.remove(index);
    }
}
