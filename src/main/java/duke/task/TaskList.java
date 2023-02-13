package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.storage.Storage;

/**
 * Represents a task list.
 */
public class TaskList {
    private List<Task> list = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param number The index of the task in the list, starting from 1.
     */
    public void markTaskAsDone(int number) {
        list.get(getIndexFromNumber(number)).setDone(true);
    }

    /**
     * Marks a task in the list as not done.
     *
     * @param number The index of the task in the list, starting from 1.
     */
    public void markTaskAsNotDone(int number) {
        list.get(getIndexFromNumber(number)).setDone(false);
    }

    /**
     * Removes a task from the list.
     *
     * @param number The index of the task in the list, starting from 1.
     */
    public void remove(int number) {
        list.remove(getIndexFromNumber(number));
    }

    /**
     * Returns the string representation of a task in the list.
     *
     * @param number The index of the task in the list, starting from 1.
     * @return The string representation of the task.
     */
    public String getTaskString(int number) {
        return list.get(getIndexFromNumber(number)).toString();
    }

    /**
     * Returns a task list that contains the tasks in this task list which matches
     *      the given keyword.
     * @param keyword A keyword, which can contain spaces.
     * @return A task list containing the matching tasks.
     */
    public TaskList getMatchingTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : list) {
            if (task.matchesKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        assert matchingTasks != null : "matchingTasks should not be null";
        return matchingTasks;
    }

    /**
     * Returns a task list that contains the tasks in this task list which are upcoming within the given days.
     * @param days Number of days from now within which a task is upcoming.
     * @return Returns a task list that contains the tasks in this task list which are upcoming within the given days.
     */
    public TaskList getUpcomingTasks(long days) {
        TaskList upcomingTasks = new TaskList();
        for (Task task : list) {
            if (task.isUpcoming(days)) {
                upcomingTasks.add(task);
            }
        }
        assert upcomingTasks != null : "upcomingTasks should not be null";
        return upcomingTasks;
    }

    /**
     * Gets the number of tasks in the task list.
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return list.size();
    }

    private int getIndexFromNumber(int number) {
        return number - 1;
    }

    /**
     * Returns the string representation of the list.
     *
     * @return Each task in the list in order, preceded by a number which
     *         is its index in the list starting from 1.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 1; i <= list.size(); i++) {
            res += i + ". " + list.get(i - 1);
            if (i < list.size()) {
                res += "\n";
            }
        }
        return res;
    }

    /**
     * Returns the string representation of all the tasks in the list,
     * formatted for saving.
     *
     * @param storage The storage object to be used in formatting.
     * @return The string representation of all the tasks in the list, formatted
     *         for saving.
     */
    public String toSaveString(Storage storage) {
        String res = "";
        for (Task task : list) {
            res += task.toSaveString(storage) + '\n';
        }
        return res;
    }
}
