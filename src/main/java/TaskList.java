import java.util.ArrayList;

/**
 * Storage which stores a list of tasks to be kept track of
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates a storage object which stores a task list
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task getTaskAtIndex(int index) {
        return taskList.get(index);
    }

    /**
     * Gets the current total number of tasks in the list
     * @return current number of tasks
     */
    public int getNumberOfTasks() {
        return taskList.size();
    }

    /**
     * Adds task to the list
     * @param task Task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes task from the list
     * @param i Index of task to be deleted
     */
    public void deleteTask(int i) {
        taskList.remove(i);
    }

    /**
     * Marks task as done
     * @param i Index of task to be marked
     */
    public void markTask(int i) {
        Task task = getTaskAtIndex(i);
        if (task.getStatusIcon().equals(" ")) {
            task.markAsDone();
        }
    }

    /**
     * Marks task as not done
     * @param i Index of task to be unmarked
     */
    public void unmarkTask(int i) {
        Task task = getTaskAtIndex(i);
        if (task.getStatusIcon().equals("X")) {
            task.unmarkFromDone();
        }
    }
}
