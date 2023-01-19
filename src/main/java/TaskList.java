import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "List empty, add tasks!";
        } else {
            StringBuilder response = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task curTask = tasks.get(i);
                response.append((i+ 1)).append(".").append(curTask.toString());
                if (i < tasks.size() - 1) {
                    response.append("\n");
                }
            }
            return response.toString();
        }
    }

    public int size() {
        return tasks.size();
    }

    public String getTaskString(int index) {
        return tasks.get(index).toString();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public String removeTask(int index) {
        StringBuilder response = new StringBuilder();
        response.append("Noted. I've removed this task:\n");
        response.append("  ").append(this.getTaskString(index)).append("\n");
        tasks.remove(index);
        response.append("Now you have ").append(this.size()).append(" tasks in the list.");
        return response.toString();
    }
}
