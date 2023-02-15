package duke.helper;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.NotANumberException;
import duke.task.Task;

/**
 * TaskList class that handles all the tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructor of the TaskList class
     *
     * @param tasks tasks to be stored
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Adds the task to the task list
     *
     * @param task task to be added
     */
    public void addToTasks(Task task) {
        assert task != null : "Task should not be empty!";
        tasks.add(task);
    }

    /**
     * Prints all the tasks stored in the list
     */
    public String outputList() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1) + "." + tasks.get(i) + "\n";
        }
        return output;
    }

    /**
     * Deletes the task from the tasklist
     *
     * @param taskNo Task number to be deleted
     * @return task that has been deleted
     */
    public String deleteTask(String taskNo) throws DukeException{
        Task task = getTask(taskNo, "delete");
        tasks.remove(task);
        return ui.showDelete(task, tasks.size());
    }

    /**
     * Mark or unmark a task
     *
     * @param isDone whether the task is done
     * @param taskId id of the task
     */
    public String markTask(boolean isDone, String taskId) throws DukeException {
            Task taskToMark = getTask(taskId, "mark");
            taskToMark.setIsDone(isDone);
            return ui.showMark(isDone, taskToMark);
    }

    public Task getTask(String taskNum, String type) throws DukeException {
        try {
            int taskNo = Integer.parseInt(taskNum) - 1;
            assert taskNo >= 0 : "Task number should be 1 or more";
            return tasks.get(taskNo);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        } catch (NumberFormatException e) {
            throw new NotANumberException(type);
        }
    }


    /**
     * Outputs the tasklist stored
     *
     * @return tasklist stored
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
