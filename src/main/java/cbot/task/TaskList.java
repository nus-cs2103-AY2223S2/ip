package cbot.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

/**
 * Stores and manages a list of tasks.
 *
 * @see Task
 */
public class TaskList {
    //private static final int GAPS = 3;
    private static final String GAP = "   ";

    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a (special) list of the given tasks.
     *
     * @param tasks A (not-so-special) list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getCount() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     * @return A confirmation message.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return String.format("\"%s\" added!",
                task.toString());
    }

    /**
     * Returns an enumerated list of the tasks stored.
     *
     * @return A numbered list of tasks.
     */
    public ArrayList<String> listTasks() {
        return listFilter(t -> true);
    }

    /**
     * Returns a list of the tasks stored filtered by the given clause.
     * Tasks are numbered relative to their position entire list, not the filtered list.
     *
     * @param pred The clause a task must satisfy to be included.
     * @return The filtered list of tasks.
     */
    public ArrayList<String> listFilter(Predicate<Task> pred) {
        ArrayList<String> arr = new ArrayList<>();

        for (int i = 0; i < getCount(); i++) {
            if (pred.test(this.tasks.get(i))) {
                arr.add(String.format("%3d. %s",
                        i + 1, this.tasks.get(i)));
            }
        }

        return arr;
    }

    /**
     * Returns true if the input number is larger than the number of tasks, or less than one.
     *
     * @param num The input number to test.
     * @return Whether the number is more than the number of tasks, or less than one.
     */
    public boolean notInRange(int num) {
        return (num <= 0 || num > getCount());
    }

    /**
     * Returns a different message depending on whether the input number is
     * less than one, more than the number of tasks or else within the acceptable range.
     *
     * @param num The input number to test.
     * @return The corresponding message.
     * @see #notInRange(int)
     */
    public String rangeError(int num) {
        if (num <= 0) {
            return "wadahek pls";
        } else if (num > getCount()) {
            return "Hm, you don't have that many tasks!";
        } else {
            return "All's good! That index is in range :D";
        }
    }

    /**
     * Marks the task at the given position as done.
     *
     * @param num The 1-based index of the task.
     * @return An encouraging confirmation message.
     * @see cbot.task.Task#yesDo()
     */
    public String mark(int num) {
        int index = num - 1;

        if (tasks.get(index).yesDo()) {
            return "Woohoo! You've completed:\n" + GAP
                    + tasks.get(index).toString();
        } else {
            return "You've already done:\n" + GAP
                    + tasks.get(index).toString();
        }
    }

    /**
     * Marks the task at the given position as not done.
     *
     * @param num The 1-based index of the task.
     * @return A consoling confirmation message.
     * @see cbot.task.Task#noDo()
     */
    public String unmark(int num) {
        int index = num - 1;

        if (tasks.get(index).noDo()) {
            return "Aw, okay :( I've unmarked:\n" + GAP
                    + tasks.get(index).toString();
        } else {
            return "Hm, you haven't yet done:\n" + GAP
                    + tasks.get(index).toString();
        }
    }

    /**
     * Removes the task at the given position from the list.
     *
     * @param num The 1-based index of the task.
     * @return A confirmation message.
     */
    public String delTask(int num) {
        return "Got it! Deleted:\n" + GAP
                + tasks.remove(num - 1).toString();
    }

    /**
     * Sorts the list of tasks. From earliest to latest, then lexicographically.
     *
     * @see cbot.task.Task#compareTo(Task)
     */
    public void sort() {
        Collections.sort(tasks);
    }

    /**
     * Returns a string representation of the entire TaskList, for the convenience of file-saving.
     *
     * @return A string representation of the TaskList, for saving.
     * @see cbot.task.Task#makeFileFriendly()
     * @see cbot.io.FileStuff#saveFile(TaskList)
     */
    public String makeFileFriendly() {
        StringBuilder sb = new StringBuilder();

        for (Task t : this.tasks) {
            sb.append(t.makeFileFriendly());
            sb.append("\n");
        }

        if (!tasks.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
