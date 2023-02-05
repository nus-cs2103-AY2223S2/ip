package duke.task;
import duke.Storage;
import duke.command.Commands;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents the list of tasks submitted by the user.
 */
public class TaskList {

    private int taskCount = 0;
    private Storage storage = null;
    private ArrayList<Tasks> tasks = new ArrayList<Tasks>();

    /**
     * Construct a task list object from the local storage of previous instance.
     * Set the taskCount to reflect the correct number of tasks.
     * @param storage The storage object of the task list.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.loadTasks();
        this.taskCount = this.tasks.size();
    }

    /**
     * Adds a new task in to the task list, and increments the task count by 1.
     */
    public void addTask(Tasks task) {
        this.tasks.add(task);
        this.taskCount += 1;
    }

    /**
     * Print out the list of tasks in the TaskList object.
     */
    //Credits: adapted from CHATGPT
    public void listTasks() {
        int i = 1;
        for (Tasks tasks : tasks) {
            System.out.println( i + "." + tasks);
            i++;
        }
    }

    public TaskList getTaskByTime(LocalDateTime time) {
        TaskList newTaskList = new TaskList(null);
        for (int i = 0; i < this.getTaskCount(); i++) {
            if (this.tasks.get(i).getTime() == time) {
                newTaskList.addTask(this.tasks.get(i));
            }
        }
        return newTaskList;
    }

    /**
     * Returns the current task count.
     * @return int the current task count.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Mark the task at the indicated index in the task list as the indicated state 'done'.
     * @param index The index of the task to be marked.
     * @param done  The state that the task needs to be marked as.
     */
    public void markTask(int index, boolean done){
        this.tasks.get(index).markTask(done);
        this.storage.saveTasks(this.tasks);
    }

    /**
     * Deletes the task at the indicated index in the task list, and decrement the task count.
     * @param index the index of the task to be deleted.
     * @return Message indicating the number of tasks left.
     */
    public String deleteTask(int index) {
        String returnString = this.tasks.get(index).toString();
        this.tasks.remove(index);
        this.taskCount -= 1;
        System.out.println("Now you have " + this.taskCount + " tasks in the list.");
        this.storage.saveTasks(this.tasks);
        return returnString;
    }

    /**
     * Find the tasks with content same as the input string, and return a new task list containing found tasks.
     * @param taskContent The content of the task to search for.
     * @return New TaskList object containing the found tasks
     */
    public TaskList findTask(String taskContent) {
        TaskList foundTasks = new TaskList(new Storage("temp"));
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.getTaskContent(i).contains(taskContent)) {
                foundTasks.addTask(this.tasks.get(i));
            }
        }
        return foundTasks;
    }

    /**
     * Prints out the string representation of the task state, whether done or not done.
     * @param index The index of the task to get state icon from.
     * @return The string representation of the task status.
     */
    public String getTaskIcon(int index) {
        return this.tasks.get(index).getStatusIcon();
    }

    /**
     * Returns the content of the task at the given index in the task list.
     * @param index The index of the task to retrieve the content from.
     * @return The content of the task at the given index.
     */
    public String getTaskContent(int index) {
        return this.tasks.get(index).getTaskContent();
    }
}
