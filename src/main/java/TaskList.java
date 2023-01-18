import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }


    public List<Task> getContent() {
        return this.taskList;
    }
}
