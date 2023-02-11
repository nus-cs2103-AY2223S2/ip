package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.addAll(taskList);
    }
}
