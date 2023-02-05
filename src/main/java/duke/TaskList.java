package duke;

import java.util.ArrayList;

/**
 * Class for TaskList object.
 * Contains arraylist of tasks.
 * 
 * @author Bryan Tan
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList object.
     * 
     * @return TaskList object
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }
    
    public int size() {
        return this.list.size();
    }

    /**
     * Adds specified task into the list.
     * 
     * @param t Task to be added.
     */
    public void add(Task t) {
        this.list.add(t);
    }

    public Task get(int i) {
        return this.list.get(i);
    }

    /**
     * Removes specified task from the list.
     * 
     * @param i Task to be removed.
     */
    public void remove(int i) {
        this.list.remove(i);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
