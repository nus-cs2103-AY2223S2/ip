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
    private final HashMap<String, HashSet<Task>> map = new HashMap<>();
    private final Parser parser = new Parser();

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addFind(String description, Task task) {
        String[] words = description.split(" ");
        for (String word: words) {
            if (!map.containsKey(word)) {
                map.put(word, new HashSet<>());
            }
            map.get(word).add(task);
        }
    }

    public void removeFind(Task task) {
        for (Map.Entry<String, HashSet<Task>> set : map.entrySet()) {
            set.getValue().remove(task);
        }
    }
    
    public String find(String word) {
        String output = "";
        if (!map.containsKey(word)) {
            return "";
        }
        for (Task task: map.get(word)) {
            output += (task.toString() + "\n");
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
        addFind(newTask.getDescription(), newTask);
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