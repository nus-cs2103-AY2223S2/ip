package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

/**
 * This class encapsulates everything the <code>Task</code> arraylist needs to do.
 */
public class TaskList {
    /**
     * The <code>ArrayList</code> storing all of <code>Duke</code>'s <code>Task</code>s.
     */
    private ArrayList<Task> taskList;

    /**
     * Constructor for <code>TaskList</code>.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    /**
     * Alternative constructor for <code>TaskList</code>.
     */
    public TaskList(ArrayList<Task> loadedList) {
        this.taskList = loadedList;
    }
    /**
     * Getter for a <code>Task</code> given its index.
     *
     * @param index Index of <code>Task</code> instance of interest.
     *
     * @return <code>Task</code> instance of given index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }
    /**
     * Returns string representation of a <code>Task</code> given its index.
     *
     * @param index Index of <code>Task</code> instance of interest.
     *
     * @return String representation of given <code>Task</code>.
     */
    public String taskToString(int index) {
        Task curTask = taskList.get(index);
        return curTask.toString();
    }
    /**
     * Appends a task to arraylist.
     *
     * @param t <code>Task</code> to be appended.
     */
    public void append(Task t) {
        taskList.add(t);
    }
    /**
     * Prints out all <code>Task</code>s for the "list" command.
     */
    public ArrayList<Task> list() {
        ArrayList<Task> copy = new ArrayList<>();
        for (Task t : taskList) {
            copy.add(t);
        }
        return copy;
    }
    /**
     * Marks given <code>Task</code> as done for the "mark" command.
     *
     * @param index Index of task to be marked.
     */
    public void markTask(int index) {
        Task curTask = taskList.get(index);
        curTask.markAsDone();
    }
    /**
     * Unmarks given <code>Task</code> as done for the "unmark" command.
     *
     * @param index Index of task to be unmarked.
     */
    public void unmarkTask(int index) {
        Task curTask = taskList.get(index);
        curTask.markAsNotDone();
    }
    /**
     * Returns total number of <code>Task</code>s.
     *
     * @return Length of arraylist.
     */
    public int getLength() {
        return taskList.size();
    }
    /**
     * Removes a <code>Task</code> from the arraylist.
     *
     * @param index Index of task to be removed.
     *
     * @return Removed <code>Task</code>.
     */
    public Task delete(int index) {
        Task removed = taskList.get(index);
        taskList.remove(index);
        return removed;
    }
    /**
     * Prints all <code>Task</code>s in <code>TaskList</code> that contains
     * <code>keyword</code>.
     * @param keyword Keyword to check all <code>Task</code>s for.
     */
    public ArrayList<Task> findInTaskList(String keyword) {
        ArrayList<Task> foundList = new ArrayList<>();
        for (int i = 0; i < this.getLength(); i++) {
            Task curTask = this.getTask(i);
            String cur = curTask.toString();
            if (cur.contains(keyword)) {
                foundList.add(curTask);
            }
        }
        return foundList;
    }
    /**
     * Removes all <code>Task</code> from arraylist.
     */
    public void clearAllTasks() {
        while (taskList.size() != 0) {
            delete(0);
        }
    }
    public ArrayList<Task> getTasksToRemind() {
        ArrayList<Task> ret = new ArrayList<>();
        for (Task t : taskList) {
            if (t.hasReminderToday()) {
                ret.add(t);
            }
        }
        return ret;
    }
}
