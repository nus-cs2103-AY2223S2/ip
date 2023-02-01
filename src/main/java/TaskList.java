import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getArrayList() {
        return this.tasks;
    }
    public void listTasks() {
        int numTasks = tasks.size();
        System.out.println("Here are the tasks in your list:");
        if (numTasks == 0) {
            System.out.println("You do not have any tasks for now!");
        } else {
            for (int i = 0; i < numTasks; i++) {
                System.out.println(String.format("%d. %s",i+1,tasks.get(i)));
            }
        }
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n " + tasks.get(taskIndex).toString());
    }

    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).markUndone();
        System.out.println("OK, I've marked this task as not done yet:\n " + tasks.get(taskIndex).toString());
    }

    public void deleteTask(int taskIndex) {
        Task taskRemoved = tasks.remove(taskIndex);
        System.out.println("Noted. I've removed this task: \n" + taskRemoved +
                String.format("\n Now you have %d tasks in the list.",tasks.size()));
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.println(newTask);
        System.out.println(String.format("Now you have %d task(s) in the list.", tasks.size()));

    }

}
