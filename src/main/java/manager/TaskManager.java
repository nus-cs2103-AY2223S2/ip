package manager;

import java.util.ArrayList;

import task.Task;

/** Manager of tasks. */
public class TaskManager {
    private final ArrayList<Task> taskList = new ArrayList<>();


    public void add(Task task) {
        taskList.add(task);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(String.format(
                "%d. %s\n",
                i+1,
                taskList.get(i).toString()
            ));
        }
        return builder.toString();
    }
}
