import java.util.ArrayList;

public class Record {
    private ArrayList<Task> list;
    public Record() {
        this.list = new ArrayList<>();
    }
    public void add(String description) {
        Task t = new Task(description);
        this.list.add(t);
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