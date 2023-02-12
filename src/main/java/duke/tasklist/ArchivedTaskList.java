package duke.tasklist;
import duke.task.Task;

import java.util.ArrayList;

public class ArchivedTaskList extends TaskList {

    public ArchivedTaskList(ArrayList<Task> arrayList) {
        super(arrayList);
    }

    public void addDeletedTask(Task task) {
        arrayList.add(task);
    }
}
