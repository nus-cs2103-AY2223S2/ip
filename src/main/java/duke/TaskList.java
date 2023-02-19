package duke;

import java.util.ArrayList;

/**
 * Represents the list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> toDoList) {
        this.tasks = toDoList;
    }

    /**
     * returns the number of tasks
     * 
     * @return size of TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * returns the task at a specific index in TaskList
     * 
     * @param index index of task to retrieve
     * @return Task at the index
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index);
    }

    /**
     * adds task to TaskList
     * 
     * @param task task to add to TaskList
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * remove the task at a specific index in TaskList
     * 
     * @param index index of task to remove
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    public ArrayList<Task> search(String searchString) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();

        for (int i = 0; i < this.tasks.size(); i++) {
            if (tasks.get(i).shouldContains(searchString)) {
                foundTasks.add(tasks.get(i));
            }
        }

        return foundTasks;
    }
}
