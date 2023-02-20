package duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import duke.commands.tasks.Task;

/**
 * This class serves to represent a to-do list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private HashMap<String, List<Integer>> duplicateTracker;
    private static final int START_INDEX = 0;
    private static final int INCREMENT = 1;

    /**
     * Creates a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.duplicateTracker = new HashMap<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param newTask The task to be added to the list.
     */
    public void add(Task newTask) {
        this.tasks.add(newTask);

        // gets the existing list in the duplicateTracker, or gets a new list
        // this list represents the indexes the newTask's description appears in the existing task list
        List<Integer> newList = this.duplicateTracker.getOrDefault(newTask.getDescription(),
                new ArrayList<>());
        newList.add(this.tasks.size());
        this.duplicateTracker.put(newTask.getDescription(), newList);
    }

    /**
     * Searches the task list to see if a task is already present. Searches according to task description.
     *
     * @param match The string representing the task description that is being searched for.
     * @return true if the task is already present. Otherwise, returns false.
     */
    public boolean contains(Task match) {
        return this.duplicateTracker.containsKey(match.getDescription());
    }

    /**
     * Gets the indexes of occurrences of a certain task. Searches according to description of task.
     *
     * @param match The task to be searched for.
     * @return A list of indexes representing where the task appears in the task list,
     * returns an empty list if the task is not found.
     */
    public List<Integer> getOccurrences(Task match) {
        return this.duplicateTracker.getOrDefault(match.getDescription(), new ArrayList<>());
    }

    /**
     * Removes a task from the list.
     *
     * @param index The index to be removed.
     */
    public void remove(int index) {
        assert index < this.size() : "Index out of bounds (too big)";
        assert index >= TaskList.START_INDEX : "Index out of bounds (too small)";
        this.tasks.remove(index);

        // reset duplicate tracker
        this.duplicateTracker.clear();
        for (int i = TaskList.START_INDEX; i < this.tasks.size(); i++) {
            List<Integer> newList = this.duplicateTracker.getOrDefault(this.get(i).getDescription(),
                    new ArrayList<>());
            newList.add(i + TaskList.INCREMENT);
            this.duplicateTracker.put(this.get(i).getDescription(), newList);
        }
    }

    /**
     * Shows the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task from the list.
     *
     * @param index The index of the task to be retrieved.
     * @return The retrieved task.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = TaskList.START_INDEX; i < tasks.size(); i++) {
            res += String.format("%d.%s\n", i + 1, tasks.get(i));
        }
        return res;
    }
}
