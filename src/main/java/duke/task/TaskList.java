package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * Contains the task list and the operations that can be performed on the tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public int getSize() {
        return taskList.size();
    }
    public Task getTask(int taskIndex) throws DukeException {
        try {
            return taskList.get(taskIndex);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Unable to get task.");
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public String deleteTask(int taskIndex) throws DukeException {
        try {
            return this.taskList.remove(taskIndex).toString();
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("duke.Task index given is invalid :( Unable to delete.");
        }
    }

    public String markTask(int taskIndex) throws DukeException {
        Task task = this.getTask(taskIndex);
        task.mark();
        return task.toString();
    }

    public String unmarkTask(int taskIndex) throws DukeException {
        Task task = this.getTask(taskIndex);
        task.unmark();
        return task.toString();
    }
}
