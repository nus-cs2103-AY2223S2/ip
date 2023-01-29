package duke.data;
import java.util.ArrayList;

import duke.data.task.Task;


public class TaskList extends ArrayList<Task> {

    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    public TaskList() {
        super();
    }

    public String tasksToStr() {
        String result = "";
        for (int i = 0; i < size(); i++) {
            result += get(i).storageStr();
            result += "\n";
        }
        return result;
    }
}
