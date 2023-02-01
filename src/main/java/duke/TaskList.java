package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    public static List<Task> getContent() {
        return tasks;
    }
}
