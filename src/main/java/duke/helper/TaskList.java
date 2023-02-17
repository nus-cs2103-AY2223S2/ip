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
     *
     * @return output message of the list command
     */
    public String outputList() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return output.toString();
    }

    /**
     * Deletes the task from the tasklist
     *
     * @param taskId Task number to be deleted
     * @return output message of the deleting a task
     * @throws DukeException If task number is invalid
     */
    public String deleteTask(String taskId) throws DukeException {
        String deleteCommand = "delete";
        Task task = getTask(taskId, deleteCommand);
        tasks.remove(task);
        return ui.outputDeleteMsg(task, tasks.size());
    }

    /**
     * Mark or unmark a task
     *
     * @param isDone whether the task is done
     * @param taskId id of the task
     * @return output message of the changing the mark status of a task
     * @throws DukeException If task number is invalid
     */
    public String changeMarkStatus(boolean isDone, String taskId) throws DukeException {
        String markCommand = "mark";
        Task taskToMark = getTask(taskId, markCommand);
        taskToMark.setIsDone(isDone);
        return ui.outputMarkMsg(isDone, taskToMark);
    }

    /**
     * Obtains the task according to the task number
     *
     * @param taskNumber task number of the task to be returned
     * @param type type of the task
     * @return the task obtained using its task number
     * @throws InvalidTaskNumberException Throws when task number is invalid
     * @throws NotANumberException Throws when task number is not a number
     */
    public Task getTask(String taskNumber, String type) throws InvalidTaskNumberException, NotANumberException {
        try {
            int taskNo = Integer.parseInt(taskNumber) - 1;
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
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
