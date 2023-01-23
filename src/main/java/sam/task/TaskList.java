package sam.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int id) {
        if (isValidId(id)) {
            return tasks.get(id - 1);
        }
        return null;
    }

    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    public Task removeTask(int id) {
        if (isValidId(id)) {
            return tasks.remove(id - 1);
        }
        return null;
    }

    public boolean setTaskDone(int id, boolean isDone) {
        if (isValidId(id)) {
            getTask(id).setDone(isDone);
            return true;
        }
        return false;
    }

    public int count() {
        return tasks.size();
    }

    public String[] generateList() {
        String[] list = new String[count()];
        for (int i = 0; i < count(); i++) {
            Task t = tasks.get(i);
            list[i] = String.format("%d: %s", i + 1, t);
        }
        return list;
    }

    private boolean isValidId(int id) {
        return !(id <= 0 || id > count());
    }
}
