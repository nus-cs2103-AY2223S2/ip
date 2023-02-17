import java.util.ArrayList;

/**
 * TaskList class encapsulates the information of the tasks keyed in the chatbot by the user.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns true if the task argument is in the task list and false otherwise.
     *
     * @param task a Task object encapsulate the task we are checking.
     * @return Returns true if the task argument is in the task list and false otherwise.
     */
    public boolean contains(Task task) {
        return this.tasks.contains(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Read the tasks' information stored in the hard disk file.
     *
     * @param tasks an ArrayList of Task Object encapsulating the tasks list from hard disk file.
     */
    public void readTasksFile(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add the task argument into the task list.
     *
     * @param newTask a Task object encapsulating the new task being added.
     */
    public void addTask(Task newTask) {
        int size = tasks.size();
        tasks.add(newTask);
        Storage.writeFile(tasks);
        Ui.getAddTaskMessage(newTask, size);
    }

    /**
     * Mark the task specified in the input of user to task list.
     *
     * @param command User input specifying which task to mark.
     */
    public void mark(String command) {
        int index = Integer.valueOf(command.substring(5)) - 1;
        Task target = tasks.get(index);
        target.mark();
        Storage.writeFile(tasks);
        Ui.getMarkTaskMessage(target);
    }

    /**
     * Unmark the task specified in the input of user to task list.
     *
     * @param command User input specifying which task to unmark.
     */
    public void unmark(String command) {
        int index = Integer.valueOf(command.substring(7)) - 1;
        Task target = tasks.get(index);
        target.unmark();
        Storage.writeFile(tasks);
        Ui.getUnmarkTaskMessage(target);
    }

    /**
     * Delete the task specified in the input of user from the task list
     *
     * @param command User input specifying which task to delete.
     */
    public void delete(String command) {
        int index = Integer.valueOf(command.substring(7)) - 1;
        int size = tasks.size();
        Task target = tasks.get(index);
        Ui.getDeleteTaskMessage(target, size);
        tasks.remove(index);
        Storage.writeFile(tasks);
    }
}
