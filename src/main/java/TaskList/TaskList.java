package TaskList;

import Task.Task;
import java.util.ArrayList;
import Ui.Ui;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public int size() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        // Call ui added.
    }

    public void removeTask(int index) {
        this.taskList.remove(index);
        // Call ui removed
        Ui.showRemovedMessage(this.get(index));
    }

    public void markDone(int index) {
        this.taskList.get(index).mark_done();
        // Call ui marked
        Ui.showMarkedMessage(this.get(index));
    }

    public void markUndone(int index) {
        this.taskList.get(index).mark_undone();
        // Call UI unmarked
        Ui.showUnmarkedMessage(this.get(index));
    }

}
