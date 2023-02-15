package duke.task;
import duke.Storage;
import duke.command.Command;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents the list of tasks submitted by the user.
 */
public class TaskList {

    private int taskCount = 0;
    private Storage storage = new Storage("tasks.ser");
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskList(Storage store) {
        this.storage = store;
    }

    public void loadTasks (ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = this.tasks.size();
    }

    /**
     * Adds a new task in to the task list, and increments the task count by 1.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.taskCount += 1;
        this.storage.saveTasks(this.tasks);
    }

    /**
     * Print out the list of tasks in the TaskList object.
     */
    //Credits: adapted from CHATGPT
    public String listTasks() {
        int i = 1;
        String result = "";
        for (Task tasks : tasks) {
            result += (i + "." + tasks + "\n");
            i++;
        }
        return result;
    }

    public TaskList getTaskByTime(LocalDateTime time) {
        Storage temp = new Storage("temp.ser");
        TaskList newTaskList = new TaskList(temp);
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
     * Marks the task at the indicated index in the task list as the indicated state 'done'.
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
    public Task deleteTask(int index) {
        Task deletedTask = this.tasks.get(index);
        this.tasks.remove(index);
        this.taskCount -= 1;
        this.storage.saveTasks(this.tasks);
        return deletedTask;
    }

    /**
     * Finds the tasks with content same as the input string, and return a new task list containing found tasks.
     * @param taskContent The content of the task to search for.
     * @return New TaskList object containing the found tasks
     */
    public TaskList findTask(String taskContent) {
        Storage temp = new Storage("temp.ser");
        TaskList foundTasks = new TaskList(temp);
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
