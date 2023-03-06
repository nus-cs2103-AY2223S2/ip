package duke.task;

import duke.DukeException;

import java.util.ArrayList;

/**
 * Represents the TaskList that is used to store the tasks using 1-based indexing
 */
public class TaskList {
    private static final String NO_ITEMS_MESSAGE = "You do not have any items in your list!";
    private final ArrayList<Task> tasks;

    /**
     * Returns a new TaskList without any tasks
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Returns a new TaskList containing the Tasks in tasks
     *
     * @param tasks ArrayList of Task(s)
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    private String getIndexOutOfBoundsMessage() {
        return String.format("Please enter a number from 1 to %d", this.size());
    }

    /**
     * Returns the Task at the index
     *
     * @param index int used to get the Task at index
     * @return Task at the index
     * @throws DukeException if index is not in range of size of TaskList
     */
    public Task get(int index) throws DukeException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(getIndexOutOfBoundsMessage());
        }
    }

    /**
     * Returns the size of the list
     *
     * @return size int size of list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns true if the TaskList has no items and false otherwise
     *
     * @return isEmpty boolean
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds the task given into the TaskList
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the Task at the index
     *
     * @param index int used to delete the Task at index
     * @return String of the deleted Task toString()
     * @throws DukeException if index is not in range of size of TaskList
     */
    public String delete(int index) throws DukeException {
        if (size() == 0) {
            throw new DukeException(NO_ITEMS_MESSAGE);
        }
        try {
            String taskDescription = this.get(index).toString();
            this.tasks.remove(index-1);
            return taskDescription;
        } catch (IndexOutOfBoundsException badNumber) {
            throw new DukeException(getIndexOutOfBoundsMessage());
        }
    }

    /**
     * Returns the ArrayList which stores the tasks
     *
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a new TaskList with Tasks that contains the words provided
     *
     * @param words String words to be found in Tasks
     * @return TaskList with only Tasks that contain the words
     */
    public TaskList find(String words) {
        ArrayList<Task> tasksWithWord = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTask().contains(words)) {
                tasksWithWord.add(task);
            }
        }
        return new TaskList(tasksWithWord);
    }
}
