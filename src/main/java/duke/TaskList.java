package duke;
import java.util.ArrayList;

/**
 * Encapsulates a list of Tasks.
 */
class TaskList {
    /** An ArrayList of Tasks.*/
    ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    /**
     * Constructs a new TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists all the tasks stored.
     */
    public void listTasks() {
        String topDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~\n" + "Duke's Response: \n"
                    + "Here are the tasks in your list: \n";
        String botDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~";
        System.out.println(topDivider);
        for (int i = 0; i < this.tasks.size(); i++) {
            String output = this.tasks.get(i).provideDetails();
            System.out.println((i + 1) + "." + output);
        }
        System.out.println(botDivider);
    }


    /**
     * Stores a new task.
     * @param task The task to be stored.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks a task as completed.
     * @param number The number representing the task to be updated.
     */
    public void mark(int number) {
        this.tasks.get(number - 1).mark();
    }

    /**
     * Marks a task as uncompleted.
     * @param number The number representing the task to be updated.
     */
    public void unmark(int number) {
        this.tasks.get(number - 1).unmark();
    }

    /**
     * Deletes a task.
     * @param number The number representing the task to be updated.
     */
    public void deleteTask(int number) {
        this.tasks.remove(number - 1);
    }

    /**
     * Gets the number of tasks.
     * @return Number of tasks.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Gets a task.
     * @param number An integer describing the task.
     * @return A task.
     */
    public Task getTask(int number) {
        return this.tasks.get(number - 1);
    }
}

