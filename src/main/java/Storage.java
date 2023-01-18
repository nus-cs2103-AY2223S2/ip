import java.util.ArrayList;

public class Storage {
    protected ArrayList<Task> taskList;

    public Storage() {
        this.taskList = new ArrayList<>();
    }

    public int getNumberOfTasks() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("Added: " + task);
    }

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

    public void unmarkTask(int i) {
        Task task = this.taskList.get(i);
        if (task.getStatusIcon().equals(" ")) {
            System.out.println("This task is already marked as not done!");
        } else {
            task.unmarkFromDone();
            System.out.println("Oh no, I've marked this task as not done yet:");
        }
        System.out.println(task);
    }

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
