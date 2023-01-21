package data;

import java.util.ArrayList;

import tasks.*;

public class MyData {
    private final ArrayList<Task> data = new ArrayList<Task>();

    public Task getData(int index) {
        return this.data.get(index);
    }

    public void deleteData(int index) { this.data.remove(index); }

    public void setData(Task command) {
        data.add(command);
    }

    public void markDone(int index) {
        this.data.get(index).markDone();
    }

    public void markUndone(int index) {
        this.data.get(index).markUndone();
    }

    public int len() {
        return this.data.size();
    }
}
