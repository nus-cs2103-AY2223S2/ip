import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private static List<Task> taskLists;

    public TaskList() {
        taskLists = new ArrayList<>(100);
    }

    public void add(Task task) {
        taskLists.add(task);
    }

    public Task get(int index){
        return taskLists.get(index);
    }

    public void remove(Task task){
        taskLists.remove(task);
    }
}
