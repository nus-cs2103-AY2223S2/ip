package tasks;

import java.util.ArrayList;

/**
 * Container for the tasks
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Prints all the contents in the TaskList
     */
    public void print() {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
    }

    public String concat() {
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            res = res + (i + 1) + ". " + task.toString() + "\n" ;
        }
        return res;
    }

    /**
     * Function to add task to the list
     *
     * @param task the task
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Getter to get a task in the specific index
     *
     * @param idx the index of task
     * @return the task pointed ny the index
     */
    public Task get(int idx) {
        return list.get(idx);
    }

    /**
     * Function to delete a specific task on an index
     *
     * @param idx the index
     */
    public void delete(int idx) {
        list.remove(idx);
    }

    /**
     * Function to get the size of current TaskList
     *
     * @return the number of Tasks contained in the TaskList
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Function to replace a task in a specific index with another task
     *
     * @param idx  the index
     * @param task the new task that will replace the old task
     */
    public void set(int idx, Task task) {
        list.set(idx, task);
    }

    /**
     * Find the tasks in hte list that matched with a certain keyword
     *
     * @param key the keyword
     */
    public void find(String key) {
        int idx = 1;
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.toString().contains(key)) {
                System.out.println("\t" + (idx) + ". " + task.toString());
                idx++;
            }
        }
    }

    public String printFind(String key) {
        String res = "";
        int idx = 1;
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.toString().contains(key)) {
                res = res + (idx) + ". " + task.toString() + "\n";
                idx++;
            }
        }
        return res;
    }
}
