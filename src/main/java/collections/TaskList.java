package collections;
import exceptions.SundayException;
import task.Task;
import utilities.Storage;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }
    public void add(Task task) {
        this.list.add(task);
    }
    public Task mark(int index) {
        this.list.get(index).mark();
        return this.list.get(index);
    }
    public Task unmark(int index) {
        this.list.get(index).unmark();
        return this.list.get(index);
    }
    public Task delete(int index) {
        return this.list.remove(index);
    }
    public Task getTask(int index) {
        return this.list.get(index);
    }
    public int getUncompletedSize() {
        int count = 0;
        for (int i = 0; i < this.list.size(); i++) {
            if (!this.list.get(i).isComplete()) {
                count++;
            }
        }
        return count;
    }
    public boolean save() throws SundayException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append("\n");
            }
            sb.append(this.list.get(i).save());
        }
        return Storage.writeToDataFile(sb.toString());
    }
    public boolean load() throws SundayException{
        if (Storage.createDataFile()) {
            return true;
        }
        Storage.readFromDataFile();
        return false;
    }
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append("\n");
            }
            sb.append(this.getTask(i));
        }
        return sb.toString();
    }
}
