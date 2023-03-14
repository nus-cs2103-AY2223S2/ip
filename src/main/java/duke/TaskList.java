package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;


/**
 * The TaskList that stores Task objects.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    /**
     * TaskList constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the Task object at the specified index of the ArrayList.
     *
     * @param i An integer representing the index
     * @return Task
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Adds a Task object into the ArrayList.
     *
     * @param t The Task object to be added into the ArrayList.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Returns the size of the ArrayList.
     *
     * @return int representing the size.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Removes the Task object at the specified index of the ArrayList.
     *
     * @param i An integer representing the index
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Filters the tasks in the list by their description.
     * @param input The user input.
     * @return ArrayList of the filtered tasks.
     */
    public ArrayList<Task> filter(String input) {
        ArrayList<Task> filtered = this.tasks.stream()
                .filter(task -> task.contains(input))
                .collect(Collectors.toCollection(ArrayList::new));
        return filtered;
    }

    /**
     * Filters the tasks in the list by their dates.
     * @param input The user input.
     * @return ArrayList of the filtered tasks.
     */
    public ArrayList<Task> filterDate(String input) {
        ArrayList<Task> filtered = this.tasks.stream()
                .filter(task -> isDeadlineHasWord(task, input) || isEventHasWord(task, input))
                .collect(Collectors.toCollection(ArrayList::new));
        return filtered;
    }

    /**
     * Checks if the task is an Event and contains the word in their date.
     * @param t The Task.
     * @param keyword The input string.
     * @return Boolean
     */
    private boolean isEventHasWord(Task t, String keyword) {
        return (t instanceof Event) && ((Event) t).dateContains(keyword);
    }

    /**
     * Checks if the task is an Deadline and contains the word in their date.
     * @param t The Task.
     * @param keyword The input string.
     * @return Boolean
     */
    private boolean isDeadlineHasWord(Task t, String keyword) {
        return (t instanceof Deadline) && ((Deadline) t).dateContains(keyword);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "" + this.tasks;
    }

}
