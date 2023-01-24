package duke.task;

import duke.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    /**
     * This method marks the task at the specified task
     * number as done.
     *
     * @param taskNumber the task order in the storage.
     */
    public Task markTask(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.mark();
        return task;
    }

    public Task unmarkTask(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.unmark();
        return task;
    }

    /**
     * This method deletes a specified task based on its order.
     *
     * @param taskNumber specifies the task to be deleted.
     * @throws DukeException if task does not exist.
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        int size = taskList.size();

        if (size == 0 || taskNumber > size) {
            throw  new DukeException("duke.task.Task number does not exist.");
        }

        Task task = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        return task;
    }
}
