import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> actions) {
        this.tasks = actions;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String printTasks() {
        int len = tasks.size();
        String reply = "";
        for (int i = 0; i < len; i++) {
            reply = reply + (i + 1) + ". " + tasks.get(i).status() + "\n";
        }
        return reply;
    }
}
