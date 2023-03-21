package cbot.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stores and manages a list of tasks.
 *
 * @see Task
 */
public class TaskList {
    public static final String GAP = "   ";

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
     * Returns the task at the given position.
     *
     * @param num The 1-based index of the task.
     * @return The requested task.
     */
    public Task getTask(int num) {
        // 1-based counting
        return this.tasks.get(num - 1);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     * @return A confirmation message.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return String.format("\"%s\" added!", task);
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
        return Stream
                .iterate(1, i -> i + 1).limit(getCount())
                .filter(i -> pred.test(getTask(i)))
                .map(i -> String.format("%3d. %s",
                        i, getTask(i)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns true if the input number is larger than the number of tasks, or less than one.
     *
     * @param num The input number to test.
     * @return Whether the number is more than the number of tasks, or less than one.
     */
    public boolean notInRange(int num) {
        boolean lessThanOne = (num <= 0);
        boolean moreThanLen = (num > getCount());

        return (lessThanOne || moreThanLen);
    }

    /**
     * Returns a different message depending on whether the input number is
     * less than one, more than the number of tasks or else within the acceptable range.
     *
     * @param num The input number to test.
     * @return The corresponding message.
     * @see #notInRange(int)
     */
    public String getRangeErrorMsg(int num) {
        boolean lessThanOne = (num <= 0);
        boolean moreThanLen = (num > getCount());

        if (lessThanOne) {
            return num + "?? wadahek pls";
        } else if (moreThanLen) {
            return num + "? Err... you don't have that many tasks";
        } else {
            return "All's good! That index is in range :D";
        }
    }

    /**
     * Marks the task at the given position as done.
     *
     * @param num The 1-based index of the task.
     * @return An encouraging confirmation message.
     * @see Task#mark()
     */
    public String mark(int num) {
        assert !notInRange(num) : "Invalid index to mark";

        boolean wasChanged = getTask(num).mark();

        if (!wasChanged) {
            return "You've already done:\n"
                    + GAP + getTask(num);
        }

        return "Woohoo! You've completed:\n"
                + GAP + getTask(num);
    }

    /**
     * Marks the task at the given position as not done.
     *
     * @param num The 1-based index of the task.
     * @return A consoling confirmation message.
     * @see Task#unmark()
     */
    public String unmark(int num) {
        assert !notInRange(num) : "Invalid index to unmark";

        boolean wasChanged = getTask(num).unmark();

        if (!wasChanged) {
            return "Hm, you haven't yet done:\n"
                    + GAP + getTask(num);
        }

        return "Aw, okay :( I've unmarked:\n"
                + GAP + getTask(num);
    }

    /**
     * Removes the task at the given position from the list.
     *
     * @param num The 1-based index of the task.
     * @return A confirmation message.
     */
    public String delTask(int num) {
        assert !notInRange(num) : "Invalid index to delete";

        Task removedTask = tasks.remove(num - 1);
        return "Got it! Deleted:\n"
                + GAP + removedTask;
    }

    /**
     * Edits the description of the task at the given position.
     *
     * @param num The 1-based index of the task.
     * @param newDesc The new task description.
     * @return A confirmation message.
     * @see Task#editDesc(String)
     */
    public String editTaskDesc(int num, String newDesc) {
        assert !notInRange(num) : "Invalid index to edit";
        assert newDesc.length() > 0 : "New description is empty";

        Task editedTask = getTask(num);
        editedTask.editDesc(newDesc);
        return "Done! It's been changed to:\n"
                + GAP + editedTask;
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
     * @see cbot.util.FileStuff#saveFile(TaskList)
     */
    public String makeFileFriendly() {
        return tasks.stream()
                .map(Task::makeFileFriendly)
                .collect(Collectors.joining("\n"));
    }
}
