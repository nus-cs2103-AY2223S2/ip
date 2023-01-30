package Duke;

import Duke.Tasks.Task;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Todo;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


}
