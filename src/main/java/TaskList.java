import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor to create new Task List
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Add new task to current task list
     * @param task new task to be added into the task list
     */
    public void addTask(String task) {
        this.tasks.add(new Task(task));
    }

    /**
     * Getter method to get the task list
     * @return task list
     */
    public String getTaskList() {
        StringBuilder taskList = new StringBuilder();

        for (int i = 1; i <= this.tasks.size(); i++) {
            taskList.append(i + ": " + this.tasks.get(i - 1).getTask() + "\n");
        }

        return taskList.toString();
    }
}
