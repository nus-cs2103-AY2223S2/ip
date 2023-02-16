package duke;

import java.util.ArrayList;

/**
 * The TaskList class provides functionality for managing a list of tasks.
 * It includes methods for adding, deleting, and searching for tasks, as well as marking tasks as done or undone.
 * @author @tricixg
 * @version 1.0
 */
public class TaskList {
    private static ArrayList<Task> list;

    public TaskList(ArrayList<Task> t) {
        this.list = t;
    }
    public TaskList() {
        list = new ArrayList<>();
    }

    public void add(Task task) {
        list.add(task);
    }

    public void delete(int i) {
        list.remove(i);
    }

    public Task get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }

    public void markAsDone(int i) {
        list.get(i).markAsDone();
    }

    public void markAsUnDone(int i) {
        list.get(i).markAsUnDone();
    }

    public static ArrayList<Task> getList() {
        return list;
    }

    /**
     * Unmarks a task.
     *
     * @param array The array of tasks.
     * @param splitInput The user's input for unmarking a task.
     */
    public static String unmarkTask(ArrayList<Task> array, String[] splitInput) {
        array.get((Integer.parseInt(splitInput[1]) - 1)).markAsUnDone();
        return Ui.unmarkTask(array, splitInput);
    }

    /**
     * Marks a task.
     *
     * @param array The array of tasks.
     * @param splitInput The user's input for marking a task.
     */
    public static String markTask(ArrayList<Task> array, String[] splitInput) {
        array.get((Integer.parseInt(splitInput[1]) - 1)).markAsDone();
        return Ui.markTask(array, splitInput);
    }

    /**
     * Deletes a task.
     *
     * @param array The array of tasks.
     * @param splitInput The user's input for deleting a task.
     */
    public static String deleteTask(ArrayList<Task> array, String[] splitInput) {
        array.remove((Integer.parseInt(splitInput[1]) - 1));
        return Ui.removeTask(array, splitInput);
    }

    /**
     * Displays list of tasks
     *
     * @param array The array of tasks.
     */
    public static String displayList(ArrayList<Task> array) {
        return Ui.displayList(array);
    }

    /**
     * Searches for a task.
     *
     * @param array The array of tasks.
     * @param splitInput The user's input for searching for a task.
     */
    public static String searchTask(ArrayList<Task> array, String[] splitInput) {
        String searchTerm = splitInput[1].toLowerCase();
        ArrayList<Task> searchResults = new ArrayList<Task>();
        int resultCount = 0;

        for (Task t : array) {
            if (t.getDescription().toLowerCase().contains(searchTerm)) {
                searchResults.add(t);
                resultCount++;
            }
        }

        if (resultCount == 0) {
            return "No tasks found with the keyword " + searchTerm + ".";
        } else {
            String list = "";
            list += "Here are the matching tasks in your list:" + "\n";
            for (int i = 0; i < searchResults.size(); i++) {
                list += (i + 1) + "." + searchResults.get(i) + "\n";
            }
            return list;
        }
    }

}
