import java.util.ArrayList;
public class TaskList extends ArrayList<Task> {

    public TaskList(ArrayList<Task> taskList) {
        this.addAll(taskList);
    }
}
