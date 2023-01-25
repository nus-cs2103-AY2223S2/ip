import java.util.ArrayList;

public class Record {
    private ArrayList<Task> list;
    public Record() {
        this.list = new ArrayList<>();
    }
    public void add(Task task) {
        this.list.add(task);
    }
    public void mark(int index) {
        this.list.get(index).mark();
    }
    public void unmark(int index) {
        this.list.get(index).unmark();
    }
    public String taskToString(int index) {
        return this.list.get(index).toString();
    }
    public String latestTaskToString() {
        return this.list.get(list.size()-1).toString();
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append("\n    ");
            }
            sb.append((i+1) + ". " + this.taskToString(i));
        }
        return sb.toString();
    }
}