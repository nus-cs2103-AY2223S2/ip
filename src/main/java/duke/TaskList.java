package duke;

import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> list;

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
    public static void unmarkTask(ArrayList<Task> array, String[] splitInput) {
        array.get((Integer.parseInt(splitInput[1])-1)).markAsUnDone();
        Ui.unmarkTask(array, splitInput);
    }

    /**
     * Marks a task.
     *
     * @param array The array of tasks.
     * @param splitInput The user's input for marking a task.
     */
    public static void markTask(ArrayList<Task> array, String[] splitInput) {
        array.get((Integer.parseInt(splitInput[1])-1)).markAsDone();
        Ui.markTask(array, splitInput);
    }

    /**
     * Deletes a task.
     *
     * @param array The array of tasks.
     * @param splitInput The user's input for deleting a task.
     */
    public static void deleteTask(ArrayList<Task> array, String[] splitInput) {
        array.remove((Integer.parseInt(splitInput[1])-1));
        Ui.removeTask(array, splitInput);
    }

    /**
     * Displays list of tasks
     *
     * @param array The array of tasks.
     */
    public static void displayList(ArrayList<Task> array) {
        Ui.displayList(array);
    }

    public static void searchTask(ArrayList<Task> array, String[] splitInput) {
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
            System.out.println("No tasks found with the keyword " + searchTerm + ".");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println((i + 1) + "." + searchResults.get(i));
            }
        }
    }

}
