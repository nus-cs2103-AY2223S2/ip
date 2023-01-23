import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    public String outputList() {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < this.tasks.size(); index++) {
            result.append((index == 0 ? "" : "\n") + (index + 1) + ". " + this.tasks.get(index).toString());
        }
        return result.toString();
    }

    public String countTasks() {
        return "Now you have " + this.tasks.size() + " task" + (this.tasks.size() == 1 ? "" : "s") + " in the list.";
    }

    public Task removeTask(String command) {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        return this.tasks.remove(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

}
