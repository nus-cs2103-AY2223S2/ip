import java.util.ArrayList;
import java.util.List;
public class TaskList {
    protected List<Task> tasks;
    protected int num_tasks;

    TaskList() {
        tasks = new ArrayList<>();
        num_tasks = 0;
    }

    public void markTask(int task_index) {
        Task ref = this.tasks.get(task_index - 1);
        ref.mark();
    }

    public void unmarkTask(int task_index) {
        Task ref = this.tasks.get(task_index - 1);
        ref.unmark();
    }

    public void add(Task task) {
        tasks.add(task);
        num_tasks++;
        System.out.println("added " + task.description + "\n");
    }

    public void printTasks() {
        for (int i = 0; i < num_tasks; i++) {
            Task ref = tasks.get(i);
            System.out.println((i + 1) + ". " + ref.getStatusIcon() + " " + ref.getDescription());
        }
        System.out.println("");
    }
}
