import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    // taskList is 1 indexed
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task getTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber - 1 >= getLength()) {
            throw new TaskNotFoundException();
        }
        return taskList.get(taskNumber - 1);
    }

    public int getLength() {
        return taskList.size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber - 1 >= getLength()) {
            throw new TaskNotFoundException();
        }
        this.taskList.remove(taskNumber - 1);
    }

    public void setDone(int taskNumber, boolean done) throws TaskNotFoundException {
        if (taskNumber - 1 >= getLength()) {
            throw new TaskNotFoundException();
        }
        taskList.get(taskNumber - 1).setDone(done);
    }

    public String describeLength() {
        return "Now you have " + this.getLength() + " tasks in the list.";
    }

    @Override
    public String toString() {
        if (getLength() == 0) {
            return "No tasks found.";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < getLength(); i++) {
            result.append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
}
