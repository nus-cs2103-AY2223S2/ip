import java.util.ArrayList;

public class TaskTracker {
    protected ArrayList<Task> taskList = new ArrayList<>();

    public TaskTracker() {}

    // Add new taskList to the list
    // Outputs a String with the details of the task and the number of taskList in the list
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

    public void markTaskCompletion(int index, boolean completion) {
        Task task = taskList.get(index);
        task.setCompletion(completion);
    }

    public void deleteTask(int index) {
        Task task = taskList.remove(index);
        System.out.println(String.format("     Noted. I've removed this task:\n       %s\n%s",
                task, numTasks()));
    }

    public String numTasks() {
        return String.format("     Now you have %d taskList in the list", taskList.size());
    }
}
