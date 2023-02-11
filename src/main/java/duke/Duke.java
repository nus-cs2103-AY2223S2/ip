package duke;

import java.util.ArrayList;
import java.util.List;
import duke.task.Task;
import duke.task.TaskList;

public class Duke {
    private final TaskList tasks;


    public Duke(List<Task> tasks) {
        this.tasks = TaskList.fromIterable(tasks);
    }

    public Duke() {
        this(new ArrayList<>());
    }

    public final TaskList getTaskList() { 
        return tasks;
    }
}