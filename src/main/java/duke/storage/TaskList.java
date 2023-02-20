package duke.storage;

import duke.task.Task;
import duke.parser.Parser;

import java.util.ArrayList;

/**
 * Represents list of tasks. A <code>TaskList</code> object corresponds to
 * the list of tasks that the user has recorded
 *
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TaskList {
    private final ArrayList<Task> list;
    private final Parser parser = new Parser();

    public TaskList() {
        this.list = new ArrayList<>();
    }
    
    public String find(String word) {
        String output = "";
        for (Task task: list) {
            if (task.getDescription().contains(word)) {
                output += (task + "\n");
            }
        }
        return output;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Prints tasks in the task list.
     *
     */
    public String list() {
        String output = "";
        for (int i = 1; i <= list.size(); i++) {
            output += (i + ": " + list.get(i - 1) + "\n");
        }
        return output;
    }
    /**
     * Adds a task to the task list.
     *
     * @param newTask Task to be added.
     */
    public void add(Task newTask) {
        list.add(newTask);
    }

    /**
     * Converts a string into a task and adds it to the task list.
     *
     * @param input String to be converted to task, then added to task list.
     */
    public void add(String input) {
        Task newTask = parser.parseTask(input);
        list.add(newTask);
    }
    /**
     * Deletes the task from task list.
     *
     * @param num Index of the task to be deleted.
     */
    public void delete(int num) {
        list.remove(num - 1);
    }

    /**
     * Marks the task in task list as done.
     *
     * @param num Index of the task to be marked as done.
     */
    public void mark(int num) {
        list.get(num - 1).mark();
    }

    /**
     * Marks the task in task list as undone.
     *
     * @param num Index of the task to be marked as undone.
     */
    public void unmark(int num) {
        list.get(num - 1).unmark();
    }

    /**
     * Returns the number of tasks in task list.
     *
     * @return Size of task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the task with the specified index in task list.
     *
     * @param num Index of the task to be returned.
     * @return Task at index num.
     */
    public Task get(int num) {
        return list.get(num - 1);
    }

    /**
     * Returns the last task in task list.
     *
     * @return Task at end of task list.
     */
    public Task getLast() {
        return list.get(list.size() - 1);
    }

    public boolean isDuplicate(String task) {
        Task newTask = parser.parseTask(task);
        for (Task t: list) {
            if (t.equals(newTask)) {
                return true;
            }
        }
        return false;
    }
}