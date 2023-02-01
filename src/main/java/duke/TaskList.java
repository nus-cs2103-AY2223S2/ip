package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> existing) {
        this.taskList = existing;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int idx) throws IndexOutOfBoundsException {
        return taskList.get(idx);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int idx) {
        if (idx < 0 || idx >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }

        return taskList.remove(idx);
        // printInBanner("Don't need this trash anymore yo~\n" + task + getTasklistSize());
    }

    public int getSize() {
        return this.taskList.size();
        // return "\nNow you have " + taskList.size() + " items on your list.";
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
