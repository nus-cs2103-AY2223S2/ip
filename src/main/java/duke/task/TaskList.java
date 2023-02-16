package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Represents the list of tasks in Duke. This task list will be saved into and loaded from a storage text file.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the ArrayList of tasks.
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     * @param index The number of the task to delete.
     * @throws DukeException If the number specified is invalid (> Number of tasks in task list or <= 0).
     */
    public void deleteTask(int index) throws DukeException {
        try {
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("That task does not exist!");
        }
    }

    /**
     * Unmarks a task in the task list.
     * @param index The number of the task to unmark.
     * @throws DukeException If the number specified is invalid (> Number of tasks in task list or <= 0).
     */
    public void unmarkTask(int index) throws DukeException {
        try {
            Task task = tasks.get(index - 1);
            task.markUndone();
            assert task.getStatusIcon().equals(" "): "Task should be unmarked";
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("That task does not exist!");
        }
    }

    /**
     * Marks a task in the task list.
     * @param index The number of the task to mark.
     * @throws DukeException If the number specified is invalid (> Number of tasks in task list or <= 0).
     */
    public void markTask(int index) throws DukeException {
        try {
            Task task = tasks.get(index - 1);
            task.markDone();
            assert task.getStatusIcon().equals("X"): "Task should be marked";
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("That task does not exist!");
        }
    }

    public ArrayList<Task> findTasks(String input) {
        String inputToLowerCase = input.toLowerCase().trim();
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().toLowerCase().contains(inputToLowerCase)) {
                foundTasks.add(task);
            } else if (task instanceof Deadline) {
                if (((Deadline) task).duedate.contains(inputToLowerCase) || task.getDuedateString().contains(inputToLowerCase)) {
                    foundTasks.add(task);
                }
            } else if (task instanceof Event) {
                if (task.getDuedateString().contains(inputToLowerCase) || ((Event) task).startTime.contains(inputToLowerCase) || ((Event) task).endTime.contains(inputToLowerCase)) {
                    foundTasks.add(task);
                }
            }
        }
                //if event or deadline, check dates
        return foundTasks;
    }
    public int getNumTasks() {
        return tasks.size();
    }

    public String formatTaskToString(Task task) {
        String taskString = tasks.indexOf(task) + 1 + ". [" + task.getSymbol() + "] "
                + "[" + task.getStatusIcon() + "] " + task.getDescription();
        if (!(task instanceof Todo)) {
            taskString +=  " (" + task.getDuedateString() + ")";
        }
        return taskString;
    }

}
