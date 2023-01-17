import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public void addTask(Task task) {
        super.add(task);
    }

    public void removeTask(Task task) {
        super.remove(task);
    }

    public void mark(int i) {
        super.get(i-1).mark();
    }

    public void unMark(int i) {
        super.get(i-1).unMark();
    }

    @Override
    public Task get(int i) {
        return super.get(i-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : this) {
            sb.append(i+".").append(task).append("\n\t");
            i++;
        }
        return sb.toString();
    }
}
