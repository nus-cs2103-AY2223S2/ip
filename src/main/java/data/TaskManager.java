package data;
import errors.DukeInvalidCommandException;
import ui.Response;
import task.Task;
import java.util.ArrayList;

/**
 * Storage medium to manage tasks created by the user
 * @author Nicholas Lee
 */

public class TaskManager {

    private final ArrayList<Task> taskList = new ArrayList<>();

    public TaskManager() {}

    /**
     * Gets the list of the users tasks
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Used to mark a task as complete.
     *
     * @param index the index of the task to mark as complete
     * @throws DukeInvalidCommandException if the task is not found
     */
    public void markTaskComplete(int index) throws DukeInvalidCommandException {
        Task taskToMark;
        try {
            taskToMark = this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.TASK_NOT_FOUND.toString());
        }
        taskToMark.changeStatus(true);
    }

    /**
     * Used to mark a task incomplete.
     *
     * @param index the index of the task to mark as complete
     * @throws DukeInvalidCommandException if the task is not found
     */
    public void markTaskIncomplete(int index) throws DukeInvalidCommandException {
        Task taskToMark;
        try {
            taskToMark = this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.TASK_NOT_FOUND.toString());
        }
        taskToMark.changeStatus(false);
    }



    /**
     * This method is used to add a task from the task list.
     *
     * @param newTask A Task object
     */
    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }


    /**
     * This method is used to delete a task from the task list.
     *
     * @param index the index of the task to delete
     * @throws DukeInvalidCommandException if the task is not found
     */
    public void deleteTask(int index) throws DukeInvalidCommandException {
        try {
            this.taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.TASK_NOT_FOUND.toString());
        }
    }













}
