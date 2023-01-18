import java.util.ArrayList;

public class Record {
    private ArrayList<Task> list;
    public Record() {
        this.list = new ArrayList<>();
    }
    public void add(Task task) {
        this.list.add(task);
    }
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += (i+1) + ". " + list.get(i).toString() + "\n    ";
        }
        return s;
    }
}