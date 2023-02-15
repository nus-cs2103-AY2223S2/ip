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
        this.items = new ArrayList<>(size);
    }

    /**
     * Add the Task e into the queue.
     *
     * @param e The item to put in the queue.
     * @return false if the queue is full; true if e is added successfully.
     */
    public boolean addTask(Task e) {
        for(int i = 0; i < this.items.size() ;i++) {
            Task currentTask = this.indexOf(i);
            if(currentTask.equals(e)) {
                return false;
            }
        }
        items.add(e);
        return true;
    }

    public static void main(String[] args) {
        ToDoTask arg = new ToDoTask("eat");
        ToDoTask argg = new ToDoTask("eat");
        ToDoTask arggg = new ToDoTask("eatt");
        TaskList tlist = new TaskList(100);
        tlist.addTask(arg);
        System.out.println(tlist.addTask(argg));
        System.out.println(tlist.addTask(arggg));
        System.out.println(tlist);

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
     * Returns the string representation of the task(s) that contain the specified keyword.
     *
     * @return A string consisting of the string representation of
     * specific object in the queue with the specified keyword.
     */
    public String find(String descriptor) {
        String str = " Here are the matching tasks in your list:\n";
        int count = 0;
        while (count < this.items.size()) {
            int counter = count + 1;
            if(this.items.get(count).getDescription().contains(descriptor)) {
                str += counter + ". " + this.items.get(count) + " \n";
            }
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

