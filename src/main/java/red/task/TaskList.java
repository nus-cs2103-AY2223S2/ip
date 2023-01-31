package red.task;

import java.util.ArrayList;

/**
 * This class is the class that keeps track of tasks added by the user.
 */

public class TaskList {

    /** An array to store the items in the queue. */
    private ArrayList<Task> items;


    /**
     * Constructor for a queue.
     *
     * @param size The maximum num of elements we can put in the queue.
     */
    public TaskList(int size) {
        this.items = new ArrayList<Task>(size);
    }

    /**
     * Add the Task e into the queue.
     *
     * @param e The item to put in the queue.
     * @return false if the queue is full; true if e is added successfully.
     */
    public boolean addTask(Task e) {
        items.add(e);
        System.out.println("New Task Added: " + e.getDescription());
        System.out.println("There are now " + this.items.size() + " task(s) awaiting completion\n");
        return true;
    }

    /**
     * Removes the Task with the specified index from the queue.
     *
     * @param index The index of the task to be removed from the queue.
     * @return the specified task that was removed from the queue.
     */
    public Task deleteTask(int index) {
        return this.items.remove(index - 1);
    }


    /**
     * Returns the size of the TaskList.
     *
     * @return integer size of the TaskList.
     */
    public int getTaskListSize() {
        return this.items.size();
    }

    /**
     * Returns the string representation of the queue.
     *
     * @return A string consisting of the string representation of
     * every object in the queue.
     */
    @Override
    public String toString() {
        String str = "This is your current task list:\n";
        int count = 0;
        while (count < this.items.size()) {
            int counter = count + 1;
            str += counter + ". " + this.items.get(count) + " \n";
            count++;
        }
        return str;
    }

    /**
     * Returns the Task in the TaskList that corresponds to the index provided.
     *
     * @param index The index of the Task in question.
     * @return task specified by index.
     */
    public Task indexOf(int index) {
        return items.get(index);
    }
}

