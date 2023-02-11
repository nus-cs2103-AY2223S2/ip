package duke;

import duke.task.Task;

import java.util.ArrayList;
public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.addAll(taskList);
    }
}
