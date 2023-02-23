package duke.main;

import java.util.ArrayList;
import java.util.List;

import duke.task.*;

/**
 * Keeps track of and updates the list of tasks.
 */
public class TaskList {

    private List<Task> allTasks;

    TaskList() {
        this.allTasks = new ArrayList<Task>();
    }

    TaskList(List<Task> allTasks) {
        this.allTasks = allTasks;
    }

    /**
     * Updates relevant task's status as done.
     *
     * @param oldTask Task to be marked as done.
     * @param taskIndex Index of task to be marked as done.
     * @return New task that is marked as done.
     */
    public Task markTaskAsDone(Task oldTask, int taskIndex) {
        if (oldTask.getTaskType().equals("[T]")) {
            Todo todo = new Todo(oldTask.getTaskNumber(),
                    true, oldTask.getTask(),
                    this.allTasks.size());
            this.allTasks.set(taskIndex, todo);
            assert taskIndex == this.allTasks.indexOf(oldTask);
            oldTask = todo;
        } else if (oldTask.getTaskType().equals("[D]")) {
            Deadline deadline = new Deadline(oldTask.getTaskNumber(),
                    true, oldTask.getTask(),
                    oldTask.getDeadline(), this.allTasks.size());
            this.allTasks.set(taskIndex, deadline);
            assert taskIndex == this.allTasks.indexOf(oldTask);
            oldTask = deadline;
        } else if (oldTask.getTaskType().equals("[E]")) {
            Event event = new Event(oldTask.getTaskNumber(),
                    true, oldTask.getTask(),
                    oldTask.getEventStartTime(),
                    oldTask.getEventEndTime(), this.allTasks.size());
            this.allTasks.set(taskIndex, event);
            assert taskIndex == this.allTasks.indexOf(oldTask);
            oldTask = event;
        } else {
            assert false: "No such class type";
        }
        return oldTask;
    }

    /**
     * Updates relevant task's status as undone.
     *
     * @param oldTask Task to be unmarked as undone.
     * @param taskIndex Index of task to be unmarked as undone.
     * @return New task that is unmarked as undone.
     */
    public Task unmarkTaskAsUndone(Task oldTask, int taskIndex) {
        if (oldTask.getTaskType().equals("[T]")) {
            Todo todo = new Todo(oldTask.getTaskNumber(),
                    false, oldTask.getTask(),
                    this.allTasks.size());
            this.allTasks.set(taskIndex, todo);
            assert taskIndex == this.allTasks.indexOf(oldTask);
        } else if (oldTask.getTaskType().equals("[D]")) {
            Deadline deadline = new Deadline(oldTask.getTaskNumber(),
                    false, oldTask.getTask(),
                    oldTask.getDeadline(), this.allTasks.size());
            this.allTasks.set(taskIndex, deadline);
            assert taskIndex == this.allTasks.indexOf(oldTask);
        } else if (oldTask.getTaskType().equals("[E]")) {
            Event event = new Event(oldTask.getTaskNumber(),
                    false, oldTask.getTask(),
                    oldTask.getEventStartTime(),
                    oldTask.getEventEndTime(), this.allTasks.size());
            this.allTasks.set(taskIndex, event);
            assert taskIndex == this.allTasks.indexOf(oldTask);
        } else {
            assert false: "No such class type";
        }
        return oldTask;
    }

    /**
     * Deletes task from task list.
     *
     * @param taskIndex Index of task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        this.allTasks.remove(taskIndex);
    }

    /**
     * Adds task to task list.
     *
     * @param task Task to be added to task list.
     */
    public void addTask(Task task) {
        this.allTasks.add(task);
    }

    /**
     * Shows all tasks.
     *
     * @return List of existing tasks.
     */
    public List<Task> getAllTasks() {
        return this.allTasks;
    }

    /**
     * Gets a specific task based on its index.
     *
     * @param index Index of task to be retrieved.
     * @return Task that is retrieved.
     */
    public Task getTask(int index) {
        return this.allTasks.get(index);
    }

    /**
     * Gets total number of tasks.
     *
     * @return Total number of tasks.
     */
    public int getNumberOfTask() {
        return this.allTasks.size();
    }

}
