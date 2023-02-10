package duke;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    public TaskList() {
        super();
    }

    public String toStorageData() {
        String data = "";
        for (Task task : this) {
            data += task.getFileDescription() + "\n";
        }
        return data;
    }
}
