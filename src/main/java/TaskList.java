import java.util.ArrayList;

public class TaskList<T> extends ArrayList<T> {

    public TaskList() {
        super();
    }

    public void addTask(T task) {
        super.add(task);
    }

    public void removeTask(T task) {
        super.remove(task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (T task : this) {
            sb.append(i+".").append(task).append("\n\t");
            i++;
        }
        return sb.toString();
    }
}
