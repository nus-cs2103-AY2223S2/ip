import java.util.ArrayList;
import java.util.List;
public class TaskList {
    protected List<Task> tasks;
    protected int num_tasks;

    TaskList() {
        tasks = new ArrayList<>();
        num_tasks = 0;
    }

    public void markTask(int task_index) throws DukeException {
        if (task_index > num_tasks || task_index < 1) {
            throw new DukeException("There is no such task available\n");
        }

        Task ref = this.tasks.get(task_index - 1);
        ref.mark();
    }

    public void unmarkTask(int task_index) throws DukeException {

        if (task_index > num_tasks || task_index < 1) {
            throw new DukeException("There is no such task available\n");
        }
        Task ref = this.tasks.get(task_index - 1);
        ref.unmark();
    }

    public void add(Task task) {
        tasks.add(task);
        num_tasks++;
        System.out.println("Got it fam! I've added this task:\n " + task.getDescription());
        System.out.println("You currently have " + num_tasks + " tasks in this list!\n");
    }

    public void deleteTask(int index) throws DukeException {
        if (index > num_tasks || index < 1) {
            throw new DukeException("There is no such task available\n");
        }

        Task removed = tasks.remove(index - 1);
        num_tasks--;
        System.out.println("Noted!I have deleted the task for you:\n " + removed.getDescription() + "\nyou currently have " + num_tasks + " tasks in this list!\n");
    }

    public void printTasks() {
        if (num_tasks == 0) {
            System.out.println("You currently have no tasks mate!!");
        }
        for (int i = 0; i < num_tasks; i++) {
            Task ref = tasks.get(i);
            System.out.println((i + 1) + "." + ref.getStatusIcon() + " " + ref.getDescription());
        }
        System.out.println("");
    }
}
