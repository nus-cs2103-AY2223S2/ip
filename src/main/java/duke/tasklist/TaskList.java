package duke.tasklist;

import duke.dukeexception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A TaskList stores all tasks temporarily for easy access of the tasks
 * while the program is running. It behaves like an ArrayList of Tasks but
 * supports more functions such as updating tasks and formatting itself to be
 * saved to the local hard disk.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * @return all the tasks row by row
     */
    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "You do not have any tasks yet";
        }
        /**
         * loop through all tasks in the arraylist and print out each task
         */
        StringBuilder sb = new StringBuilder();
        sb.append("All Tasks:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append("    " + i + ". " + tasks.get(i - 1) + "\n");
        }
        return sb.toString();
    }

    /**
     * a method that marks a particular task as "Done"
     * @param taskNumber the number of the Task to be marked
     * @return the updated Task
     * @throws DukeException if the user wants to mark a Task that does not exist
     */
    public Task mark(int taskNumber) throws DukeException {
        try {
            tasks.get(taskNumber - 1).mark();
        } catch (Exception e) {
            throw new DukeException("duke.task.Task does not exist, current number of tasks: " + tasks.size());
        }
        return tasks.get(taskNumber - 1);
    }

    /**
     * Similar to mark, unmark marks a Task as "not done".
     * @param taskNumber the number of the Task to be unmarked
     * @return the updated Task
     * @throws DukeException if the user wants to unmark a Task that does not exist
     */
    public Task unmark(int taskNumber) throws DukeException {
        try {
            tasks.get(taskNumber - 1).unmark();
        } catch (Exception e) {
            throw new DukeException("duke.task.Task does not exist, current number of tasks: " + tasks.size());
        }
        return tasks.get(taskNumber - 1);
    }

    public String find(String searchLine) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(searchLine)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() == 0) {
            return "No related tasks found";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Found related tasks:\n");
        for (int i = 1; i <= foundTasks.size(); i++) {
            sb.append("    " + i + ". " + foundTasks.get(i - 1) + "\n");
        }
        return sb.toString();
    }
    /**
     * a method that removes a Task from the list
     * @param taskNumber the number of the Task to be removed
     * @return the removed Task
     * @throws DukeException if the user wants to remove a task that does not exist
     */
    public Task remove(int taskNumber) throws DukeException {
        try {
            return tasks.remove(taskNumber - 1);
        } catch (Exception e) {
            throw new DukeException("duke.task.Task does not exist, current number of tasks: " + tasks.size());
        }
    }

    /**
     * method that helps write the Tasks to the local hard drive
     * @return a String to be written to the local file containing all Tasks.
     */
    public String getWriteString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toStoreFormatString() + System.lineSeparator());
        }
        return sb.toString();
    }
}
