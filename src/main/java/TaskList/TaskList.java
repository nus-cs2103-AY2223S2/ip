package TaskList;

import Task.Task;
import java.util.ArrayList;
import Ui.Ui;

public class TaskList {
    private static ArrayList<Task> listToStore;

    public TaskList(ArrayList<Task> list) {
        this.listToStore = list;
    }

    public TaskList() {
        this.listToStore = new ArrayList<>();
    }

    public Task get(int index) {
        return this.listToStore.get(index);
    }

    public ArrayList<Task> getList() {
        return this.listToStore;
    }

    public int size() {
        return this.listToStore.size();
    }

    public void addTask(Task task) {
        this.listToStore.add(task);
        // Call ui added.
    }

    public void removeTask(int index) {
        this.listToStore.remove(index);
        // Call ui removed
        Ui.showRemovedMessage(this.get(index));
    }

    public void markDone(int index) {
        this.listToStore.get(index).mark_done();
        // Call ui marked
        Ui.showMarkedMessage(this.get(index));
    }

    public void markUndone(int index) {
        this.listToStore.get(index).mark_undone();
        // Call UI unmarked
        Ui.showUnmarkedMessage(this.get(index));
    }

}
