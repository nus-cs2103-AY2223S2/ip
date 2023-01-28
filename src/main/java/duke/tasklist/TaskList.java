package duke.tasklist;

import duke.task.Task;
import java.util.ArrayList;

//TODO: consider changing to TaskManager class; handles everything related to tasks
/**
 * This class encapsulates everything the duke.task.Task arraylist needs to do. Also
 * handles saving tasks to duke.Duke.txt.
 */
public class TaskList {

    /**
     * The arraylist storing all of duke.Duke's tasks.
     */
    private ArrayList<Task> taskList;

    /**
     * Constructor for duke.tasklist.TaskList. Takes care of file access of duke.Duke.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedList) {
        this.taskList = loadedList;
    }

    /**
     * Getter for a task given its index.
     *
     * @param index Index of duke.task.Task instance of interest.
     *
     * @return duke.task.Task instance of given index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns string representation of a task given its index.
     *
     * @param index Index of duke.task.Task instance of interest.
     *
     * @return String representation of given task.
     */
    public String taskToString(int index) {
        Task curTask = taskList.get(index);
        return curTask.toString();
    }

    /**
     * Appends a task to arraylist.
     *
     * @param t duke.task.Task to be appended.
     */
    public void append(Task t) {
        taskList.add(t);
    }

    /**
     * Prints out all tasks for the "list" command.
     */
    public void list() {
        int index = 1;
        for (Task t : taskList) {
            String curIndex = Integer.toString(index);
            System.out.println("\t" + curIndex + ". " + t.toString());
            index++;
        }
    }

    /**
     * Marks given task as done for the "mark" command.
     *
     * @param index Index of task to be marked.
     */
    public void markTask(int index) {
        Task curTask = taskList.get(index);
        curTask.markAsDone();
    }

    /**
     * Unmarks given task as done for the "unmark" command.
     *
     * @param index Index of task to be unmarked.
     */
    public void unmarkTask(int index) {
        Task curTask = taskList.get(index);
        curTask.markAsNotDone();
    }

    /**
     * Returns total number of tasks.
     *
     * @return Length of arraylist.
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * Removes a task from the arraylist.
     *
     * @param index Index of task to be removed.
     *
     * @return Removed task.
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
    public void findInTaskList(String keyword) {
        ArrayList<Task> foundList = new ArrayList<>();
        for (int i = 0; i < this.getLength(); i++) {
            Task curTask = this.getTask(i);
            String cur = curTask.toString();
            if (cur.contains(keyword)) {
                System.out.println("\t" + (i + 1) + ". " + curTask);
            }
        }
    }
    public void clearAllTasks() {
        while (taskList.size() != 0) {
            delete(0);
        }
    }

}
