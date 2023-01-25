package duke;

import java.util.ArrayList;

/**
 * Represents the list of tasks
 */
public class TaskList {
    private ArrayList<Task> toDoList;

    public TaskList() {
        this.toDoList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> toDoList) {
        this.toDoList = toDoList;
    }

    /**
     * returns the number of tasks
     * 
     * @return size of TaskList
     */
    public int size() {
        return this.toDoList.size();
    }

    /**
     * returns the task at a specific index in TaskList
     * 
     * @param index index of task to retrieve
     * @return Task at the index
     */
    public Task get(int index) {
        return this.toDoList.get(index);
    }

    /**
     * adds task to TaskList
     * 
     * @param task task to add to TaskList
     */
    public void add(Task task) {
        this.toDoList.add(task);
    }

    /**
     * remove the task at a specific index in TaskList
     * 
     * @param index index of task to remove
     */
    public void remove(int index) {
        this.toDoList.remove(index);
    }

    public ArrayList<Task> search(String searchString) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        
        for (int i = 0; i < this.toDoList.size(); i++) {
            if(toDoList.get(i).contains(searchString)){
                foundTasks.add(toDoList.get(i));
            }
        }

        return foundTasks;
    }
}
