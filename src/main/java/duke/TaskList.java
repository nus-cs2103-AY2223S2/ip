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

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid index");
        }
        Task deletedTask = tasks.get(index - 1);
        this.tasks.remove(index - 1);
        return deletedTask;
    }

    public Task markTaskDone(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid index");
        }
        Task markedTask = tasks.get(index - 1);
        this.tasks.get(index - 1).markDone();
        return markedTask;
    }

    public Task markTaskUndone(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid index");
        }
        Task unmarkedTask = tasks.get(index - 1);
        this.tasks.get(index - 1).markUndone();
        return unmarkedTask;
    }

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
            if (i == tasks.size() - 1) {
                tasksStr = tasksStr + (i + 1) + ". " + tasks.get(i);
            } else {
                tasksStr = tasksStr + (i + 1) + ". " + tasks.get(i) + "\n";
            }
        }
        return tasksStr;
    }
}
