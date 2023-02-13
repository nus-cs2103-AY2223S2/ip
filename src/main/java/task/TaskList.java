package task;

import java.util.LinkedList;
import java.util.List;

import duke.DukeException;
import duke.Ui;

/**
 * List of all task.
 */
public class TaskList {

    private final List<Task> storedTasks;

    /**
     * Constructor for TaskList.
     *
     * @param storedTasks List of all tasks stored in file.
     */
    public TaskList(List<Task> storedTasks) {
        this.storedTasks = new LinkedList<>(storedTasks);
    }

    /**
     * Get a task in TaskList.
     *
     * @param index Index of task.
     * @return Task at specified index.
     * @throws DukeException If task is not in the list.
     */
    public Task getTaskAt(int index) throws DukeException {
        try {
            return this.storedTasks.get(this.offSetIndex(index));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.getIntegerOutOfBoundsMessage());
        }
    }

    /**
     * Remove a task from TaskList.
     *
     * @param index Index of task.
     * @return Task removed.
     * @throws DukeException If task is not in list.
     */
    public Task removeTaskAt(int index) throws DukeException {
        try {
            return this.storedTasks.remove(this.offSetIndex(index));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.getIntegerOutOfBoundsMessage());
        }
    }

    /**
     * Add a task into TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        storedTasks.add(task);
    }

    /**
     * Find all task that contains the given phrase.
     *
     * @param phrase Phrase use for finding.
     * @return String of all task found.
     */
    public String findTaskWith(String phrase) {
        int taskIndex = 1;
        StringBuilder s = new StringBuilder("  ");

        for (Task currentTask : this.storedTasks) {
            if (currentTask.getName().contains(phrase)) {
                s.append(taskIndex).append(".").append(currentTask).append("\n  ");
                taskIndex += 1;
            }
        }
        return s.toString();
    }

    @Override
    public String toString() {
        return this.getAllTaskInListWithNumbering();
    }

    private String getAllTaskInListWithNumbering() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= storedTasks.size(); i++) {
            s.append(i).append(". ").append(storedTasks.get(this.offSetIndex(i))).append("\n");
        }
        return s.toString();
    }

    /**
     * Format task list for file write.
     *
     * @return String of all task in file write format.
     */
    public String writeToFile() {
        StringBuilder s = new StringBuilder();
        for (Task task : this.storedTasks) {
            s.append(task.writeToFile());
            s.append("\n");
        }
        return s.toString();
    }

    private int offSetIndex(int indexStartingFromOne) {
        return indexStartingFromOne - 1;
    }

}
