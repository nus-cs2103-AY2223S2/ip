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
    public Task getTask(int idx) throws DukeException {
        try {
            return taskList.get(idx);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Unable to get task.");
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public String deleteTask(int idx) throws DukeException {
        try {
            return this.taskList.remove(idx).toString();
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("duke.Task index given is invalid :( Unable to delete.");
        }
    }

    public String markTask(int idx) throws DukeException {
        Task task = this.getTask(idx);
        task.mark();
        return task.toString();
    }

    public String unmarkTask(int idx) throws DukeException {
        Task task = this.getTask(idx);
        task.unmark();
        return task.toString();
    }

    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().indexOf(keyword) != -1) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
