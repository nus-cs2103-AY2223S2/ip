package duke;


import java.util.ArrayList;

import duke.task.Task;

public class TaskList extends ArrayList<Task>{

    public TaskList(ArrayList<Task> list) {
        super(list);
    }

    public TaskList() {
        super();
    }
}