import java.util.ArrayList;

public class TaskTracker {
    protected ArrayList<Task> taskList = new ArrayList<>();

    public TaskTracker() {}

    // Add new tasks to the list
    // Outputs a String with the details of the task and the number of tasks in the list
    public void addTask(Task task) {
        taskList.add(task);
        System.out.println(String.format("     Got it. I've added this task:\n" +
                "       %s\n" + this.numTasks(), task));
    }

    public void listTasks() {
        int counter = 1;
        for (Task t : taskList) {
            System.out.println(counter + ". " + t.toString());
            counter++;
        }
    }

    public Task markTaskCompletion(int index, boolean completion) {
        Task task = taskList.get(index);
        task.setCompletion(completion);
        return task;
    }

    public String numTasks() {
        return String.format("     Now you have %d tasks in the list", taskList.size());
    }

}
