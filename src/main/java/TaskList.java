import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> list;
    protected int items;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public String addTask(Task task) {
        list.add(task);
        items++;
        return "Got it. I've added this task:" + "\n" + " [" + task.getTaskType() + "][ ] " + task.toString() + "\n"
                + "Now you have " + items + " tasks in the list.";
    }

    public String printTaskList() {
        StringBuilder tasklist = new StringBuilder();
        tasklist.append("Here are the tasks in your list:" + "\n");
        for(int i = 0; i < items; i++) {
            tasklist.append(i + 1).append(".").append("[").append(list.get(i).getTaskType()).append("][")
                    .append(list.get(i).getStatusIcon()).append("] ").append(list.get(i).toString()).append("\n");
        }
        return tasklist.toString();
    }
}
