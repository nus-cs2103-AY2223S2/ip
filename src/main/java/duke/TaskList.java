package duke;

import java.util.ArrayList;

import task.Task;

public class TaskList extends ArrayList<Task>{

    public TaskList(ArrayList<Task> list) {
        super(list);
    }

    public TaskList() {
        super();
    }
}