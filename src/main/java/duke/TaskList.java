package duke;
import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the current list of tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the current list of tasks.
     *
     * @param index Index of task to be deleted.
     * @return The deleted task.
     * @throws DukeException If the index is invalid.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid index");
        }
        Task deletedTask = tasks.get(index - 1);
        this.tasks.remove(index - 1);
        return deletedTask;
    }

    /**
     * Marks a task from the current list of tasks as done.
     *
     * @param index Index of task to be marked as done.
     * @return The marked task.
     * @throws DukeException If the index is invalid.
     */
    public Task markTaskDone(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid index");
        }
        Task markedTask = tasks.get(index - 1);
        this.tasks.get(index - 1).markDone();
        return markedTask;
    }

    /**
     * Marks a task from the current list of tasks as undone.
     *
     * @param index Index of task to be marked as undone.
     * @return The unmarked task.
     * @throws DukeException If the index is invalid.
     */
    public Task markTaskUndone(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid index");
        }
        Task unmarkedTask = tasks.get(index - 1);
        this.tasks.get(index - 1).markUndone();
        return unmarkedTask;
    }

    /**
     * Returns the number of tasks in the list.
     * @return Number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    public String createTaskSaveData() {
        String saveData = "";
        for (int i = 0; i < tasks.size(); i++) {
            saveData = saveData + tasks.get(i).save();
        }
        return saveData;
    }

    @Override
    public String toString() {
        String tasksStr = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksStr = tasksStr + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return tasksStr.trim();
    }

    public TaskList findKeywordTasks(String keyword) {
        TaskList keywordTasks = new TaskList();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.hasKeyword(keyword)) {
                keywordTasks.addTask(task);
            }
        }
        return keywordTasks;
    }
}
