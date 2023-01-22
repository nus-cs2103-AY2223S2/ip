import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task getTask(int taskNumber) {
        return this.taskList.get(taskNumber - 1);
    }

    /**
     * This method marks the task at the specified task
     * number as done.
     *
     * @param taskNumber the task order in the storage.
     */
    public Task markTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);
        task.mark();
        return task;
    }

    public Task unmarkTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);
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
        int size = this.taskList.size();
        if (size == 0 || taskNumber > size) {
            throw  new DukeException("Task number does not exist.");
        }

        Task task = taskList.get(taskNumber - 1);
        this.taskList.remove(taskNumber - 1);
        return task;
    }
}
