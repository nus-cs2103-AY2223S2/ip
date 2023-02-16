package task;

import java.util.ArrayList;

import duke.Storage;

/**
 * The `Tasklist` class manages a list of `Task` objects and their storage.
 */
public class Tasklist {
    private ArrayList<Task> list;
    private Storage backend;

    /**
     * Default constructor for the Tasklist class. Initializes a new empty ArrayList of Task and a new Storage object with the file path "./data/duke.txt".
     */
    public Tasklist() {
        this.list = new ArrayList<>();
        this.backend = new Storage("./data/duke.txt");
    }

    /**
     * Constructor for the Tasklist class. Initializes a new Tasklist with the specified list and backend.
     *
     * @param list the list of tasks to initialize the Tasklist with
     * @param backend the storage backend to initialize the Tasklist with
     */
    public Tasklist(ArrayList<Task> list, Storage backend) {
        this.list = list;
        this.backend = backend;
    }

    /**
     * Adds a task to the task list and saves the updated task list to the backend.
     *
     * @param task the task to add to the task list
     */
    public void add(Task task) {
        this.list.add(task);
        this.backend.save(this);
    }

    /**
     * Marks a task in the task list as done and saves the updated task list to the backend.
     *
     * @param index the index of the task to mark as done
     */
    public void markTaskAsDone(int index) {
        this.list.get(index).markDone();
        this.backend.save(this);
    }

    /**
     * Unmarks a task in the task list and saves the updated task list to the backend.
     *
     * @param index the index of the task to unmark
     */
    public void unmarkTask(int index) {
        this.list.get(index).unmark();
        this.backend.save(this);
    }

    /**
     * Deletes a task from the task list and saves the updated task list to the backend.
     *
     * @param index the index of the task to delete
     */
    public void deleteTask(int index) {
        this.list.remove(index);
        backend.save(this);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return an integer representing the number of tasks in the list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        int zeroBasedIndex = index - 1;
        return this.list.get(zeroBasedIndex);
    }

    /**
     * Prints the tasks in the list to the console.
     */
    public void inString() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + this.list.get(i).toString());
        }
    }

    public Tasklist find(String keyword) {
        Tasklist matchedList = new Tasklist();
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            if (task.contains(keyword)) {
                matchedList.add(task);
            }
        }
        return matchedList;
    }
/**
 *
i nt containsKeywordIndex = 1;
        for (int i = 0; i < this.list.size(); i++) {
            if (list.get(i).contains(keyword)) {
                System.out.println(containsKeywordIndex + "." + this.list.get(i).toString());
                containsKeywordIndex += 1;
            }
        }
 */

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public String toString() {
        String result;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= list.size(); i++) {
            stringBuilder.append(this.getTask(i));
            if (i != list.size()) {
                stringBuilder.append("\n");
            }
        }
        result = stringBuilder.toString();
        return result;
    }

}
