import java.util.ArrayList;

/**
 * Storage which stores a list of tasks to be kept track of
 */
public class Storage {
    protected ArrayList<Task> taskList;

    /**
     * Creates a storage object which stores a task list
     */
    public Storage() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets the current total number of tasks in the list
     * @return current number of tasks
     */
    public int getNumberOfTasks() {
        return this.taskList.size();
    }

    /**
     * Adds task to the list
     * @param task Task to be added
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        String statement = "Got it! I've added this task:\n" + task + "\nNow you have ";
        String t = this.getNumberOfTasks() == 1 ? " task" : " tasks";
        System.out.println(statement + this.getNumberOfTasks() + t + " in the list.");
    }

    /**
     * Deletes task from the list
     * @param i Index of task to be deleted
     */
    public void deleteTask(int i) {
        Task task = this.taskList.get(i);
        this.taskList.remove(i);
        String t = this.getNumberOfTasks() <= 1 ? " task" : " tasks";
        System.out.println("Got it! I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.getNumberOfTasks() + t + " in the list.");
    }

    /**
     * Marks task as done
     * @param i Index of task to be marked
     */
    public void markTask(int i) {
        Task task = this.taskList.get(i);
        if (task.getStatusIcon().equals("X")) {
            System.out.println("This task is already marked as done!");
        } else {
            task.markAsDone();
            System.out.println("Good job! I've marked this task as done:");
        }
        System.out.println(task);
    }

    /**
     * Marks task as not done
     * @param i Index of task to be unmarked
     */
    public void unmarkTask(int i) {
        Task task = this.taskList.get(i);
        if (task.getStatusIcon().equals(" ")) {
            System.out.println("This task is already marked as not done!");
        } else {
            task.unmarkFromDone();
            System.out.println("Okay, I've marked this task as not done yet:");
        }
        System.out.println(task);
    }

    /**
     * Prints out the list of current tasks
     */
    public void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i+1);
            sb.append(".");
            sb.append(this.taskList.get(i));
            System.out.println(sb);
        }
    }
}
