import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int index) {
        return  this.taskList.get(index);
    }

    /**
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * @param index the index of the task to be marked as done.
     */
    public Task markTaskDone(int index) {
        this.taskList.get(index).markAsDone();
        return this.taskList.get(index);
    }

    /**
     * @param index the index of the task to be marked as undone.
     */
    public Task markTaskUndone(int index) {
        this.taskList.get(index).markAsUndone();
        return this.taskList.get(index);
    }

    /**
     * @param index the index of the task to be deleted.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index >= this.taskList.size()) {
            throw new DukeException("Task does not exist!");
        }
        Task deletedTask = this.taskList.get(index);
        this.taskList.remove(index);
        return deletedTask;
    }

    /**
     * @return the string representation of the task list in the format to be stored in the hard disk.
     */
    public String toStorageString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.taskList) {
            sb.append(task.toStorageString()).append("\n");
        }
        return sb.toString();
    }


    /**
     * @return the size of the task list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * @return True if the task list is empty.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    @Override
    public String toString() {
        if (this.taskList.isEmpty()) {
            return "You have no tasks in your list.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.taskList.size(); i++) {
            sb.append(i + 1).append(". ").append(this.taskList.get(i)).append("\n");
        }
        return sb.toString();
    }
}
